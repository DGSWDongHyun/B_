package com.simple.b_.viewmodel.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.simple.b_.view.adapters.WeatherAdapter
import com.simple.data.model.WeatherData

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val weatherAdapter = MutableLiveData<WeatherAdapter>()
    private val weatherDateList = ArrayList<WeatherData>()

    val getWeatherAdapter : WeatherAdapter
        get() = weatherAdapter.value!!

    init {
        weatherAdapter.value = WeatherAdapter()
        for(i in 1 until 8) { weatherDateList.add(WeatherData("대구 7월 ${i}일")) }

        weatherAdapter.value?.setData(weatherDateList)
        weatherAdapter.value?.notifyDataSetChanged()
    }
}