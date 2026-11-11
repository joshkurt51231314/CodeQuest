package com.example.codequest.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.codequest.data.AccountRepository
import com.example.codequest.data.AppDatabase
import com.example.codequest.databinding.ActivitySignupBinding
import kotlinx.coroutines.launch

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private val repo by lazy { AccountRepository(AppDatabase.get(this).userDao()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupPasswordToggle(binding.etPassword, binding.tvToggle)
        binding.btnSignUp.setOnClickListener { signUp() }
    }

    private fun signUp() {
        binding.btnSignUp.setPixelEnabled(false)
        lifecycleScope.launch {
            val error = repo.signUp(binding.etUsername.text.toString(), binding.etPassword.text.toString())
            binding.btnSignUp.setPixelEnabled(true)
            if (error == null) {
                Toast.makeText(this@SignUpActivity, "ACCOUNT CREATED!", Toast.LENGTH_SHORT).show()
                finish() // back to the login screen
            } else {
                binding.tvError.text = error
                binding.tvError.visibility = View.VISIBLE
            }
        }
    }
}
