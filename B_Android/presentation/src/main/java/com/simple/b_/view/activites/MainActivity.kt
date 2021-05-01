package com.simple.b_.view.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.simple.b_.R
import com.simple.b_.databinding.ActivityMainBinding
import com.simple.b_.viewmodel.main.MainViewModel

class MainActivity : AppCompatActivity() {

    private val mainBinding : ActivityMainBinding by lazy { DataBindingUtil.setContentView(this, R.layout.activity_main) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding.lifecycleOwner = this

        mainBinding.mainViewModel = MainViewModel(application)
    }
}