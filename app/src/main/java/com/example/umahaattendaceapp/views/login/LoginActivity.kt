package com.example.umahaattendaceapp.views.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umahaattendaceapp.R
import com.example.umahaattendaceapp.databinding.ActivityLoginBinding
import com.example.umahaattendaceapp.views.forgotpass.ForgotPasswordActivity
import com.example.umahaattendaceapp.views.main.MainActivity
import org.jetbrains.anko.startActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        OnClick()
    }

    private fun OnClick() {
     binding.btnLogin.setOnClickListener {
        startActivity<MainActivity>()
     }
        binding.btnForgotPassword.setOnClickListener {
            startActivity<ForgotPasswordActivity>()
        }
    }
}