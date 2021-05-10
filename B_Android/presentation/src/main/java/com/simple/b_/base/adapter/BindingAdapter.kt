package com.simple.b_.base.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.bumptech.glide.Glide
import com.simple.b_.view.adapters.MealAdapter
import com.simple.data.utils.Constants

class BindingAdapter {
    companion object{
        @JvmStatic
        @BindingAdapter("bind:adapter")
        fun initAdapter(recyclerView: RecyclerView?, adapter : RecyclerView.Adapter<*>?) {
            val linearLayoutManager = LinearLayoutManager(recyclerView?.context)
            linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            recyclerView?.layoutManager = linearLayoutManager
            recyclerView?.adapter = adapter

            if(adapter is MealAdapter) {
                val snapHelper = PagerSnapHelper()
                snapHelper.attachToRecyclerView(recyclerView!!)
            }
        }

        @JvmStatic
        @BindingAdapter("bind:glide")
        fun setImage(imageView : ImageView?, res : Int?) {
            Glide.with(imageView!!.context).load(res).into(imageView)
        }

        @JvmStatic
        @BindingAdapter(value = ["bind:glide", "bind:type"], requireAll = false)
        fun setImage(imageView : ImageView?, res : String, type : Int? = Constants.IMAGE_TYPE_ORIGIN) {
            when (type) {
                Constants.IMAGE_TYPE_ORIGIN -> {
                    Glide.with(imageView!!.context).load(res).into(imageView)
                }
                Constants.IMAGE_TYPE_URL -> {
                    Glide.with(imageView!!.context)
                        .load("${Constants.OPEN_WEATHER_IMAGE}${res}@2x.png").into(imageView)
                }
            }
        }
    }
}