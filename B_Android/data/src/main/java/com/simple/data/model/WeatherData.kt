package com.simple.data.model

data class WeatherList(
    val main : MainData?,
    val weather : ArrayList<WeatherData>
)

data class MainData(
    val temp : Double?,
    val feels_like : Double?,
    val temp_min : Double?,
    val temp_max : Double?
)

data class WeatherData(
    val id : Int?,
    val main : String?,
    val description : String?,
    val icon : String?
)