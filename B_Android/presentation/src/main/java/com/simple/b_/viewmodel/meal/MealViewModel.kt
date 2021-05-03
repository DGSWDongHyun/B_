package com.simple.b_.viewmodel.meal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.simple.b_.view.adapters.MealAdapter
import com.simple.data.model.MealInfo

class MealViewModel(application : Application) : AndroidViewModel(application) {
    private val mealTitle = MutableLiveData<String>()
    private val mealContents = MutableLiveData<String>()

    val getMealTitle : String
        get() = mealTitle.value!!

    val getMealContents : String
        get() = mealContents.value!!
}