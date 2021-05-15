package com.simple.b_.viewmodel.intro

import android.app.Activity
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.simple.b_.R

class IntroViewModel : ViewModel() {
    val onClickNext = View.OnClickListener {
        Navigation.findNavController(it).navigate(R.id.action_introFragment_to_introSecondFragment)
    }
    val onClickFinish = View.OnClickListener {
        ((it.context) as Activity).finish()
    }
}