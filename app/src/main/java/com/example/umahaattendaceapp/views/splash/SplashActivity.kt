package com.example.umahaattendaceapp.views.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.umahaattendaceapp.R
import com.example.umahaattendaceapp.hawkstorage.HawkStorage
import com.example.umahaattendaceapp.views.login.LoginActivity
import com.example.umahaattendaceapp.views.main.MainActivity
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        afterDelayGoToLogin()
    }

    private fun afterDelayGoToLogin() {
        Handler(Looper.getMainLooper()).postDelayed({
            checkIsLogin()
           startActivity<LoginActivity>()
            finishAffinity()
        },1200)
    }

    private fun checkIsLogin() {
        val isLogin = HawkStorage.instance(this).isLogin()
        if (isLogin){
            startActivity<MainActivity>()
            finishAffinity()
        }else{
            startActivity<LoginActivity>()
            finishAffinity()
        }
    }

}

