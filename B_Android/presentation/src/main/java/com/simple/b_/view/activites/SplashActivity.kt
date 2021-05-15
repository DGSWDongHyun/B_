package com.simple.b_.view.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import com.simple.b_.R
import com.simple.b_.base.BaseActivity
import com.simple.b_.databinding.ActivityMainBinding
import com.simple.b_.databinding.ActivitySplashBinding
import com.simple.b_.viewmodel.splash.SplashViewModel

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    override val viewModel: SplashViewModel
        get() = SplashViewModel()
    override val layoutRes: Int
        get() = R.layout.activity_splash

    override fun onBackPressed() {
        return
    }
}