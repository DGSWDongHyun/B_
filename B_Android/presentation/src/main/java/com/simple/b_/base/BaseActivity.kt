package com.simple.b_.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.simple.b_.BR
import com.simple.b_.R
import com.simple.b_.view.activites.IntroActivity
import com.simple.b_.view.activites.SplashActivity

abstract class BaseActivity<VB : ViewDataBinding, VM : ViewModel> : AppCompatActivity() {

    private val sharedPreferences : SharedPreferences by lazy { getSharedPreferences( "localSharedPreference", Context.MODE_PRIVATE) }

    private lateinit var binding : VB

    abstract val viewModel : VM
    abstract val layoutRes : Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializedBinding()
        checkActivityBinding()
    }

    fun initializedBinding() {
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()

    }

    fun checkActivityBinding() {
        if(layoutRes == R.layout.activity_splash) {
            Handler().postDelayed(Runnable {
                if(sharedPreferences.getBoolean("isFirst", true)) {
                    startActivity(Intent(this, IntroActivity::class.java))
                }else{
                    finish()
                }
            }, 2000)
        }else if(layoutRes == R.layout.activity_main) {
            startActivity(Intent(this, SplashActivity::class.java))
        }
    }
}