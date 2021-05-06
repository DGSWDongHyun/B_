package com.simple.data.utils

object WeatherConverter {
    fun weatherConverter(condition : String) : String {
        var convertedCondition = ""

        when(condition) {
            "Thunderstorm" -> {convertedCondition = "천둥번개"}
            "Drizzle" -> {convertedCondition = "이슬비"}
            "Rain" -> {convertedCondition = "비"}
            "Snow" -> {convertedCondition = "눈"}
            "Atmosphere" -> {convertedCondition = "안개"}
            "Clear" -> {convertedCondition = "맑음"}
            "Clouds" -> {convertedCondition = "구름 있음"}
        }

        return convertedCondition
    }
}