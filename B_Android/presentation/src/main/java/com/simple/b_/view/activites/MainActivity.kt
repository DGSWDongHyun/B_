package com.simple.b_.view.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.google.android.material.navigation.NavigationView
import com.simple.b_.R
import com.simple.b_.databinding.ActivityMainBinding
import com.simple.b_.viewmodel.main.MainViewModel

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{

    private val mainBinding : ActivityMainBinding by lazy { DataBindingUtil.setContentView(this, R.layout.activity_main) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(this, SplashActivity::class.java))

        mainBinding.lifecycleOwner = this
        mainBinding.mainViewModel = MainViewModel()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.item_notification) {
            //it will be go activity soon ( notification activity )

            return true
        }

        return false
    }
}