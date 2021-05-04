package com.simple.b_.viewmodel.weather

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simple.b_.view.adapters.WeatherAdapter

class WeatherViewModel() : ViewModel() {
    private val weatherTemp = MutableLiveData<String>()
}