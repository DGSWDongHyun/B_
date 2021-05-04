package com.simple.b_.viewmodel.home

import android.app.Activity
import android.app.Application
import android.os.Handler
import android.text.Html
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simple.b_.module.NetworkModule
import com.simple.b_.view.adapters.MealAdapter
import com.simple.b_.view.adapters.WeatherAdapter
import com.simple.data.model.MealInfo
import com.simple.data.model.MealsData
import com.simple.data.model.WeatherData
import com.simple.data.network.service.MealsAPI
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import java.util.function.Consumer
import kotlin.collections.ArrayList

class HomeViewModel() : ViewModel() {

    private val weatherAdapter = MutableLiveData<WeatherAdapter>()
    private val weatherDateList = ArrayList<WeatherData>()
    private val mealDataList = ArrayList<MealInfo>()
    private val instance = NetworkModule.getDefaultMealInstance()
    private val api = instance.create(MealsAPI::class.java)
    private val mealAdapter = MutableLiveData<MealAdapter>()
    private val dateTime = "${SimpleDateFormat("MM월 dd일").format(Date(System.currentTimeMillis()))} 급식이에요.".replace("0", "")

    val mealTodayDate : String
        get() = Html.fromHtml("<b>${dateTime}</b>", 1).toString()

    val getMealAdapter : MealAdapter
        get() = mealAdapter.value!!

    val getWeatherAdapter : WeatherAdapter
        get() = weatherAdapter.value!!

    init {
        mealAdapter.value = MealAdapter()
        weatherAdapter.value = WeatherAdapter()
        weatherDateList.add(WeatherData(0, "", "", ""))
        for(i in 1 until 8) { mealDataList.add(MealInfo("대구 7월 ${i}일","대구 7월 ${i}일","대구 7월 ${i}일","대구 7월 ${i}일")) }

        mealAdapter.value?.setData(mealDataList)
        weatherAdapter.value?.setData(weatherDateList)
        onServedMealInfo()
    }

    private fun onServedMealInfo(){
        api.getMeals()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<Response<MealsData>>() {
                override fun onSuccess(response: Response<MealsData>) {
                    if(response.isSuccessful){
                        mealDataList.apply {
                            clear()
                            addAll(response.body()!!.mealServiceDietInfo[1].row)
                        }
                        mealAdapter.value?.setData(mealDataList)
                    }else{
                        Log.d("TAG", "onFailed :: ${response.message()}")
                    }
                }

                override fun onError(e: Throwable) {
                    Log.d("TAG", "onError :: ${e.message}")
                }
            })


    }
}