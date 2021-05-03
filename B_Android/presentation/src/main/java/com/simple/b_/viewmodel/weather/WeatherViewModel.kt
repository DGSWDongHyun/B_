package com.simple.b_.viewmodel.weather

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.simple.b_.view.adapters.WeatherAdapter

class WeatherViewModel(application: Application) : AndroidViewModel(application) {
    private val weatherTemp = MutableLiveData<String>()
}