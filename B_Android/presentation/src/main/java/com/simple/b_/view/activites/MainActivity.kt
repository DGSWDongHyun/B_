package com.simple.b_.view.activites

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.google.android.material.navigation.NavigationView
import com.simple.b_.R
import com.simple.b_.base.BaseActivity
import com.simple.b_.databinding.ActivityMainBinding
import com.simple.b_.viewmodel.main.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() , NavigationView.OnNavigationItemSelectedListener{

    override val viewModel: MainViewModel
        get() = MainViewModel()
    override val layoutRes: Int
        get() = R.layout.activity_main

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.item_notification) {
            //it will be go activity soon ( notification activity )

            return true
        }

        return false
    }
}