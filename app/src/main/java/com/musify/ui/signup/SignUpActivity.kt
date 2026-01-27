package com.musify.ui.signup

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.musify.databinding.ActivityRegisterBinding
import com.musify.ui.MainActivity
import com.musify.ui.landing.LandingActivity
import com.musify.ui.signin.SignInActivity

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUpButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.signInButton.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }

        binding.goBackButton.setOnClickListener {
            val intent = Intent(this, LandingActivity::class.java)
            startActivity(intent)
        }
    }
}