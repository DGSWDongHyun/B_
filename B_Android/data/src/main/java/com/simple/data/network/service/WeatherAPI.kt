package com.simple.data.network.service

import com.simple.data.model.WeatherData
import io.reactivex.Single
import retrofit2.http.GET

interface WeatherAPI {
    @GET
    fun getWeather() : Single<retrofit2.Response<WeatherData>>
}