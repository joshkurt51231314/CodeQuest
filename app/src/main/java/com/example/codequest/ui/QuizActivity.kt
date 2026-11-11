package com.example.codequest.ui

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.codequest.QUESTION_TIME_MS
import com.example.codequest.QuizState
import com.example.codequest.QuizViewModel
import com.example.codequest.R
import com.example.codequest.databinding.ActivityQuizBinding
import kotlinx.coroutines.launch

class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding
    private val vm: QuizViewModel by viewModels()
    private var resultOpened = false

    private val answerViews by lazy {
        listOf(binding.tvAnswer1, binding.tvAnswer2, binding.tvAnswer3, binding.tvAnswer4)
    }
    private val lifeViews by lazy { listOf(binding.life1, binding.life2, binding.life3) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        answerViews.forEachIndexed { i, view -> view.setOnClickListener { vm.select(i) } }
        binding.btnHint.setOnClickListener { vm.useHint() }
        binding.btnNext.setOnClickListener { vm.next() }

        // The screen only DRAWS the state. All game rules live in QuizViewModel.
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.state.collect { render(it) }
            }
        }
    }

    private fun color(id: Int) = ContextCompat.getColor(this, id)

    private fun render(s: QuizState) {
        if (s.finished) {
            if (!resultOpened) {
                resultOpened = true
                startActivity(
                    Intent(this, ResultActivity::class.java)
                        .putExtra(ResultActivity.EXTRA_SCORE, s.score)
                        .putExtra(ResultActivity.EXTRA_CORRECT, s.correctCount)
                        .putExtra(ResultActivity.EXTRA_TOTAL, s.questions.size)
                        .putExtra(ResultActivity.EXTRA_WON, s.lives > 0)
                )
                finish()
            }
            return
        }
        val q = s.current ?: return

        // Header
        binding.tvProgress.text = "Q ${s.index + 1}/${s.questions.size}"
        binding.tvScore.text = "SCORE: ${s.score.toString().padStart(4, '0')}"

        // Timer bar (max = 1000) and seconds
        val urgent = !s.answered && s.timeLeftMs <= 3000
        binding.timerBar.progress = s.timeLeftMs * 1000 / QUESTION_TIME_MS
        binding.timerBar.progressTintList =
            ColorStateList.valueOf(color(if (urgent) R.color.warn else R.color.yellow))
        binding.tvTime.text = "TIME: ${(s.timeLeftMs + 999) / 1000}"
        binding.tvTime.setTextColor(color(if (urgent) R.color.yellow else R.color.white))

        // Lives
        lifeViews.forEachIndexed { i, v ->
            v.setBackgroundColor(color(if (i < s.lives) R.color.yellow else R.color.life_off))
        }

        // Question and answers
        binding.tvQuestion.text = q.text
        answerViews.forEachIndexed { i, v ->
            v.text = q.options[i]
            v.visibility = if (i in s.hiddenOptions) View.GONE else View.VISIBLE
            v.isEnabled = !s.answered
            when {
                !s.answered -> {
                    v.setBackgroundKeepPadding(R.drawable.bg_answer)
                    v.setTextColor(color(R.color.white))
                }
                i == q.answerIndex -> {
                    v.setBackgroundKeepPadding(R.drawable.bg_answer_correct)
                    v.setTextColor(color(R.color.black))
                }
                i == s.selected -> {
                    v.setBackgroundKeepPadding(R.drawable.bg_answer_wrong)
                    v.setTextColor(color(R.color.white))
                }
                else -> {
                    v.setBackgroundKeepPadding(R.drawable.bg_answer)
                    v.setTextColor(color(R.color.white))
                }
            }
        }

        // After answering: feedback, explanation, NEXT. Before: HINT.
        if (s.answered) {
            binding.tvFeedback.text = when {
                s.timedOut -> "TIME'S UP! -1 LIFE"
                s.selected == q.answerIndex -> "CORRECT! +100"
                else -> "WRONG! -1 LIFE"
            }
            binding.tvNoteText.text = q.explanation
            binding.btnNext.text =
                if (s.index == s.questions.size - 1 || s.lives <= 0) "FINISH" else "NEXT"
            binding.tvFeedback.visibility = View.VISIBLE
            binding.noteBox.visibility = View.VISIBLE
            binding.btnNext.visibility = View.VISIBLE
            binding.btnHint.visibility = View.GONE
            binding.tvHintCost.visibility = View.GONE
        } else {
            binding.tvFeedback.visibility = View.GONE
            binding.noteBox.visibility = View.GONE
            binding.btnNext.visibility = View.GONE
            binding.btnHint.visibility = View.VISIBLE
            binding.tvHintCost.visibility = View.VISIBLE
            binding.btnHint.setPixelEnabled(!s.hintUsed)
        }
    }
}
