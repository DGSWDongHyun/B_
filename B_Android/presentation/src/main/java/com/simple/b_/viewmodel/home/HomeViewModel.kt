package com.simple.b_.viewmodel.home

import android.app.Activity
import android.app.Application
import android.location.LocationManager
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
import com.simple.data.model.WeatherList
import com.simple.data.network.service.MealsAPI
import com.simple.data.network.service.WeatherAPI
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
import kotlin.math.ceil

class HomeViewModel(val longitude : Double, val latitude : Double) : ViewModel() {

    val weatherData = MutableLiveData<WeatherData>()

    private val mealDataList = ArrayList<MealInfo>()

    private val instanceMeal = NetworkModule.getDefaultMealInstance()
    private val apiMeal = instanceMeal.create(MealsAPI::class.java)

    val temp = MutableLiveData<String>()

    private val instanceWeather = NetworkModule.getDefaultWeatherInstance()
    private val apiWeather = instanceWeather.create(WeatherAPI::class.java)

    private val mealAdapter = MutableLiveData<MealAdapter>()
    private val dateTime = "${SimpleDateFormat("MM월 dd일").format(Date(System.currentTimeMillis()))} 급식이에요.".replace("0", "")


    val getMealAdapter : MealAdapter
        get() = mealAdapter.value!!

    init {
        temp.value = ""
        weatherData.value = WeatherData(0, "", "", "")

        mealAdapter.value = MealAdapter()
        for(i in 1 until 8) { mealDataList.add(MealInfo("대구 7월 ${i}일","대구 7월 ${i}일","대구 7월 ${i}일","대구 7월 ${i}일")) }

        mealAdapter.value?.setData(mealDataList)

        onServedMealInfo()
        onServedWeatherInfo()
    }

    private fun onServedWeatherInfo() {
        apiWeather.getWeather(longitude, latitude)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<Response<WeatherList>>() {
                override fun onSuccess(response: Response<WeatherList>) {
                    if(response.isSuccessful) {
                        if(response.code() == 200) {
                            val responseWeatherData : WeatherData = response.body()!!.weather[0]

                            weatherData.apply {
                                value = responseWeatherData
                            }

                            val temp_ = (response.body()!!.main!!.temp?.minus(273.15))
                            temp.value = "안녕하세요. 좋은 날이죠? \n현재 기온은 ${ceil(temp_!!)}°C 입니다."
                        }
                    }else{
                        Log.d("TAG", "onFailed :: ${response.message()}")
                    }
                }

                override fun onError(e: Throwable) {
                    Log.d("TAG", "onError :: ${e.message}")
                }

            })
    }

    private fun onServedMealInfo(){
        apiMeal.getMeals()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<Response<MealsData>>() {
                override fun onSuccess(response: Response<MealsData>) {
                    if(response.isSuccessful){
                        if(response.code() == 200) {
                            mealDataList.apply {
                                clear()
                                addAll(response.body()!!.mealServiceDietInfo[1].row)
                            }
                            mealAdapter.value?.setData(mealDataList)
                        }
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