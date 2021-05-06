package com.simple.b_.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.simple.b_.databinding.ItemWeatherBinding
import com.simple.data.model.WeatherData
import com.simple.data.utils.WeatherConverter

class WeatherAdapter(private var weatherList : ArrayList<WeatherData> = arrayListOf()) : RecyclerView.Adapter<WeatherViewHolder>(){

    fun setData(weatherList: ArrayList<WeatherData>) {
        this.weatherList = weatherList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val layoutBinder = ItemWeatherBinding.inflate(LayoutInflater.from(parent.context))

        return WeatherViewHolder(layoutBinder)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(weatherList[position])
    }

    override fun getItemCount() = weatherList.size
}

class WeatherViewHolder(private val item : ItemWeatherBinding) : RecyclerView.ViewHolder(item.root) {
    fun bind(weatherData : WeatherData) {
        item.weatherTitle.text = WeatherConverter.weatherConverter(weatherData.main!!)
        item.weatherContents.text = weatherData.description
        Glide.with(item.weatherImage.context).load("http://openweathermap.org/img/wn/${weatherData.icon}@2x.png").into(item.weatherImage)

        item.executePendingBindings()
    }
}