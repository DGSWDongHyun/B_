package com.simple.data.network.service

import com.simple.data.model.WeatherData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("weather")
    fun getWeather(
        @Query("lat") lat : String?,
        @Query("lon") lon : String?,
        @Query("appid") appid : String?
    ) : Single<retrofit2.Response<WeatherData>>
}