package com.simple.data.network.service

import com.simple.data.model.WeatherData
import com.simple.data.model.WeatherList
import com.simple.data.utils.Constants
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("weather")
    fun getWeather(
        @Query("lat") lat : Double?,
        @Query("lon") lon : Double?,
        @Query("appid") appid : String? = Constants.OPEN_WEATHER_API_KEY
    ) : Single<retrofit2.Response<WeatherList>>
}