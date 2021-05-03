package com.simple.b_.base.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
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
    }
}