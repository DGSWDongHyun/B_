package com.simple.b_.view.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import com.simple.b_.R
import com.simple.b_.databinding.ActivityMainBinding
import com.simple.b_.databinding.ActivitySplashBinding
import com.simple.b_.viewmodel.splash.SplashViewModel

class SplashActivity : AppCompatActivity() {

    private val splashBinding : ActivitySplashBinding by lazy { DataBindingUtil.setContentView(this, R.layout.activity_splash) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        splashBinding.lifecycleOwner = this
        splashBinding.splashViewModel = SplashViewModel()

        Handler().postDelayed(Runnable {
            finish()
        }, 2000)
    }

    override fun onBackPressed() {
        return
    }
}