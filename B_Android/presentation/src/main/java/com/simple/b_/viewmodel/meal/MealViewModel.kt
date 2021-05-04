package com.simple.b_.viewmodel.meal

import android.app.Application
import android.text.Html
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simple.b_.R
import com.simple.b_.view.adapters.MealAdapter
import com.simple.data.model.MealInfo

class MealViewModel() : ViewModel() {
    val mealTitle = MutableLiveData<String>()
    val mealContents = MutableLiveData<String>()
    private val daytimeImage = MutableLiveData<Int>()


    init { // init breakfast
        daytimeImage.value = R.drawable.ic_breakfast_
    }

    val getImage : Int
        get() = daytimeImage.value!!

    fun bind(data : MealInfo) {
        mealTitle.value = data.MMEAL_SC_NM
        mealContents.value = Html.fromHtml(data.DDISH_NM, 1).toString().replace("[0-9]".toRegex(),"").replace("[.]".toRegex(),"")
        when(data.MMEAL_SC_NM) {
            "조식" -> daytimeImage.value = R.drawable.ic_breakfast_
            "중식" -> daytimeImage.value = R.drawable.ic_lunch_
            "석식" -> daytimeImage.value = R.drawable.ic_dinner_
        }
    }

}