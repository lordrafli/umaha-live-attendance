package com.example.umahaattendaceapp.views.forgotpass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umahaattendaceapp.R
import com.example.umahaattendaceapp.databinding.ActivityForgotPasswordBinding
import com.orhanobut.hawk.Hawk.init

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init(){

        }


    }

    private fun init(function: () -> Unit) {
        setSupportActionBar(binding.tbForgotPassword)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}