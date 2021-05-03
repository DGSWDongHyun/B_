package com.simple.b_.module

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.simple.data.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    private var network : Retrofit ?= null

    fun getDefaultMealInstance() : Retrofit {
        if(network == null) {
            network = Retrofit.Builder()
                .baseUrl(Constants.DEFAULT_ADDRESS)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return network!!
    }
}