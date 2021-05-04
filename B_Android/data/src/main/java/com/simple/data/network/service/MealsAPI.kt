package com.simple.data.network.service

import com.simple.data.model.MealInfo
import com.simple.data.model.MealsData
import com.simple.data.utils.Constants
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.util.*

interface MealsAPI {
    @GET("mealServiceDietInfo")
    fun getMeals(
        @Query("KEY") key : String = Constants.NEIS_API_KEY ,
        @Query("Type") type : String = Constants.DEFAULT_TYPE,
        @Query("pSize") size : Int = Constants.SIZE,
        @Query("MLSV_FROM_YMD") date : String = Constants.DEFAULT_DATE,
        @Query("MLSV_TO_YMD") date_end : String =  Constants.DEFAULT_DATE,
        @Query("SD_SCHUL_CODE") school_cd : String = Constants.DEFAULT_SCHOOL_CD,
        @Query("ATPT_OFCDC_SC_CODE") official_sc_cd : String = Constants.DEFAULT_CODE
    ) : Single<retrofit2.Response<MealsData>>
}