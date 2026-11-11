package com.example.codequest.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.codequest.data.AccountRepository
import com.example.codequest.data.AppDatabase
import com.example.codequest.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val repo by lazy { AccountRepository(AppDatabase.get(this).userDao()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupPasswordToggle(binding.etPassword, binding.tvToggle)
        binding.btnLogin.setOnClickListener { login() }
        binding.btnSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    private fun login() {
        binding.btnLogin.setPixelEnabled(false)
        lifecycleScope.launch {
            val error = repo.login(binding.etUsername.text.toString(), binding.etPassword.text.toString())
            binding.btnLogin.setPixelEnabled(true)
            if (error == null) {
                startActivity(Intent(this@LoginActivity, QuizActivity::class.java))
                finish()
            } else {
                binding.tvError.text = error
                binding.tvError.visibility = View.VISIBLE
            }
        }
    }
}
