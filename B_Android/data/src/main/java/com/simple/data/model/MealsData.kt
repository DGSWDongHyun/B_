package com.simple.data.model

data class MealsData(
    val mealServiceDietInfo : ArrayList<RowData>
)

data class RowData(
    val row : ArrayList<MealInfo>
)

data class MealInfo(
    val MMEAL_SC_NM : String?,
    val MLSV_YMD : String?,
    val DDISH_NM : String?,
    val CAL_INFO : String?
)
