package com.simple.data.utils

import java.text.SimpleDateFormat
import java.util.*

class Constants {
    companion object {
        const val DEFAULT_ADDRESS = "https://open.neis.go.kr/hub/"
        const val NEIS_API_KEY = "503bcc6acfd4461f8fdf117c90319b51"
        const val DEFAULT_TYPE = "json"
        const val SIZE = 25
        var DEFAULT_DATE = getDate()
        const val DEFAULT_SCHOOL_CD = "7240454"
        const val DEFAULT_CODE = "D10"

        const val DEFAULT_ADDRESS_WEATHER = "api.openweathermap.org/data/2.5/"

        private fun getDate() : String {
            val calendar = Calendar.getInstance()
            return SimpleDateFormat("yyyyMMdd").format(calendar.time)
        }
    }
}