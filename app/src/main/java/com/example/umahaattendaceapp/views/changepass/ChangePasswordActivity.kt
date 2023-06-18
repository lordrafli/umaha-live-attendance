package com.example.umahaattendaceapp.views.changepass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umahaattendaceapp.databinding.ActivityChangePasswordBinding
import com.orhanobut.hawk.Hawk.init


class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChangePasswordBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init(){

        }
        OnClick()
    }

    private fun OnClick() {
        binding.btnChangePassword.setOnClickListener {
            finish()
        }
    }

    private fun init(function: () -> Unit) {
        setSupportActionBar(binding.tbChangePassword)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}

