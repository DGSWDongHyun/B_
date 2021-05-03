package com.simple.b_.module

import com.simple.data.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    private var network : Retrofit ?= null

    fun getDefaultMealInstance() : Retrofit {
        if(network == null) {
            network = Retrofit.Builder()
                .baseUrl(Constants.DEFAULT_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return network!!
    }
}