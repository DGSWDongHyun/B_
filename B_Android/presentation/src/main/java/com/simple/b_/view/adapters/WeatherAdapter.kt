package com.simple.b_.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.simple.b_.base.BaseAdapter
import com.simple.b_.databinding.ItemWeatherBinding
import com.simple.b_.viewmodel.weather.WeatherViewModel
import com.simple.data.model.WeatherData
import com.simple.data.utils.WeatherConverter

class WeatherAdapter(private var weatherList : ArrayList<WeatherData> = arrayListOf(),val context : Context) :
    BaseAdapter<ItemWeatherBinding, WeatherViewModel, WeatherViewHolder>() {

    override val item: ItemWeatherBinding
        get() = ItemWeatherBinding.inflate(LayoutInflater.from(context))

    override val viewHolder: WeatherViewHolder
        get() = WeatherViewHolder(item)

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) = holder.bind(weatherList[position])

    override fun getItemCount(): Int = weatherList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder = viewHolder

}

class WeatherViewHolder(private val item : ItemWeatherBinding) : RecyclerView.ViewHolder(item.root) {
    fun bind(weatherData : WeatherData) {
        item.weatherTitle.text = WeatherConverter.weatherConverter(weatherData.main!!)
        item.weatherContents.text = weatherData.description
        Glide.with(item.weatherImage.context).load("http://openweathermap.org/img/wn/${weatherData.icon}@2x.png").into(item.weatherImage)

        item.executePendingBindings()
    }
}