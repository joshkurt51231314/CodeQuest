package com.example.codequest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codequest.data.Question
import com.example.codequest.data.MoreQuestions
import com.example.codequest.data.QuestionBank
import com.example.codequest.data.withShuffledOptions
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

const val QUESTION_TIME_MS = 10_000
private const val TICK_MS = 100

data class QuizState(
    val questions: List<Question> = emptyList(),
    val index: Int = 0,
    val score: Int = 0,
    val correctCount: Int = 0,
    val lives: Int = 3,
    val selected: Int? = null,
    val timedOut: Boolean = false,
    val timeLeftMs: Int = QUESTION_TIME_MS,
    val hiddenOptions: Set<Int> = emptySet(),
    val hintUsed: Boolean = false,
    val finished: Boolean = false
) {
    val current: Question? get() = questions.getOrNull(index)
    val answered: Boolean get() = selected != null || timedOut
}

class QuizViewModel : ViewModel() {
    private val _state = MutableStateFlow(
        QuizState(questions = (QuestionBank.all + MoreQuestions.all).shuffled().take(10).map { it.withShuffledOptions() })
    )
    val state: StateFlow<QuizState> = _state.asStateFlow()

    private var timerJob: Job? = null

    init {
        startTimer()
    }

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            _state.update { it.copy(timeLeftMs = QUESTION_TIME_MS) }
            while (_state.value.timeLeftMs > 0) {
                delay(TICK_MS.toLong())
                _state.update { it.copy(timeLeftMs = (it.timeLeftMs - TICK_MS).coerceAtLeast(0)) }
            }
            timeUp()
        }
    }

    private fun timeUp() {
        val s = _state.value
        if (s.answered || s.finished) return
        _state.value = s.copy(timedOut = true, lives = s.lives - 1)
    }

    fun select(option: Int) {
        val s = _state.value
        val q = s.current ?: return
        if (s.answered || s.finished) return
        timerJob?.cancel()
        val correct = option == q.answerIndex
        _state.value = s.copy(
            selected = option,
            score = if (correct) s.score + 100 else s.score,
            correctCount = if (correct) s.correctCount + 1 else s.correctCount,
            lives = if (correct) s.lives else s.lives - 1
        )
    }

    fun useHint() {
        val s = _state.value
        val q = s.current ?: return
        if (s.hintUsed || s.answered) return
        val hide = q.options.indices.filter { it != q.answerIndex }.shuffled().take(2).toSet()
        _state.value = s.copy(hiddenOptions = hide, hintUsed = true, score = (s.score - 25).coerceAtLeast(0))
    }

    fun next() {
        val s = _state.value
        val isLast = s.index >= s.questions.size - 1
        if (isLast || s.lives <= 0) {
            timerJob?.cancel()
            _state.value = s.copy(finished = true)
        } else {
            _state.value = s.copy(
                index = s.index + 1,
                selected = null,
                timedOut = false,
                hiddenOptions = emptySet(),
                hintUsed = false,
                timeLeftMs = QUESTION_TIME_MS
            )
            startTimer()
        }
    }
}
