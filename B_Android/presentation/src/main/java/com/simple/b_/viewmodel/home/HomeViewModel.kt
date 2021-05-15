package com.simple.b_.viewmodel.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simple.b_.extensions.NetworkModule
import com.simple.b_.view.adapters.MealAdapter
import com.simple.data.model.*
import com.simple.data.network.service.MealsAPI
import com.simple.data.network.service.WeatherAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.ceil

class HomeViewModel() : ViewModel() {

    val gpsData = MutableLiveData<GpsData>()

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
        gpsData.value = GpsData(0.0,0.0)
        temp.value = ""
        weatherData.value = WeatherData(0, "", "", "")

        mealAdapter.value = MealAdapter()
        for(i in 1 until 8) { mealDataList.add(MealInfo("대구 7월 ${i}일","대구 7월 ${i}일","대구 7월 ${i}일","대구 7월 ${i}일")) }

        mealAdapter.value?.setData(mealDataList)

        onServedMealInfo()
        onServedWeatherInfo()
    }

    private fun onServedWeatherInfo() {
        apiWeather.getWeather(gpsData.value?.latitude, gpsData.value?.longitude)
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
                            checkMealInfo(response)
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

    private fun checkMealInfo(response : Response<MealsData>) {

        if(!response.body()!!.mealServiceDietInfo.isNullOrEmpty()) {
            mealDataList.apply {
                clear()
                addAll(response.body()!!.mealServiceDietInfo[1].row)
            }
        }else{
            val mealInfo = ArrayList<MealInfo>()

            mealDataList.apply {
                clear()

                mealInfo.add(MealInfo("조식","","급식이 없습니다.","0.0 kcal"))
                mealInfo.add(MealInfo("중식", "", "급식이 없습니다.", "0.0 kcal"))
                mealInfo.add(MealInfo("석식", "", "급식이 없습니다.", "0.0 kcal"))

                addAll(mealInfo)
            }
        }
        mealAdapter.value?.setData(mealDataList)
    }
}