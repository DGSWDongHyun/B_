package com.simple.b_.viewmodel.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val helloTextValue = MutableLiveData<String>()

    init {
        helloTextValue.value = "hello tv with databinding , clean-architectiure"
    }
}