package com.example.codequest.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.codequest.R
import com.example.codequest.data.AppDatabase
import com.example.codequest.data.ScoreRepository
import com.example.codequest.databinding.ActivityResultBinding
import kotlinx.coroutines.launch

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private var saved = false
    private var newBest = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        saved = savedInstanceState?.getBoolean("saved") ?: false
        newBest = savedInstanceState?.getBoolean("newBest") ?: false

        val score = intent.getIntExtra(EXTRA_SCORE, 0)
        val correct = intent.getIntExtra(EXTRA_CORRECT, 0)
        val total = intent.getIntExtra(EXTRA_TOTAL, 0)
        val won = intent.getBooleanExtra(EXTRA_WON, false)

        binding.tvResultTitle.text = if (won) "QUEST COMPLETE!" else "GAME OVER"
        binding.tvScore.text = "SCORE: $score"
        binding.tvCorrect.text = "CORRECT: $correct/$total"

        // Save the game and show best score + last games (from the Room database)
        val repo = ScoreRepository(AppDatabase.get(this).scoreDao())
        lifecycleScope.launch {
            if (!saved) {
                val previousBest = repo.best()
                repo.save(score, correct, total, won)
                newBest = score > 0 && score > previousBest
                saved = true
            }
            if (newBest) {
                binding.tvBest.text = "NEW HIGH SCORE!"
                binding.tvBest.setTextColor(ContextCompat.getColor(this@ResultActivity, R.color.yellow))
            } else {
                binding.tvBest.text = "BEST: ${repo.best()}"
            }
            val recent = repo.recent(5)
            if (recent.isNotEmpty()) {
                binding.tvNoteText.text = recent
                    .mapIndexed { i, s -> "${i + 1}. ${s.score} PTS  ${s.correct}/${s.total}" }
                    .joinToString("\n")
                binding.noteBox.visibility = View.VISIBLE
            }
        }

        binding.btnPlayAgain.setOnClickListener {
            startActivity(Intent(this, QuizActivity::class.java))
            finish()
        }
        binding.btnMenu.setOnClickListener {
            startActivity(
                Intent(this, TitleActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            )
            finish()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("saved", saved)
        outState.putBoolean("newBest", newBest)
    }

    companion object {
        const val EXTRA_SCORE = "score"
        const val EXTRA_CORRECT = "correct"
        const val EXTRA_TOTAL = "total"
        const val EXTRA_WON = "won"
    }
}
