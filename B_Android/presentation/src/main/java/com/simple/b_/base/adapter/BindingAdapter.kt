package com.simple.b_.base.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BindingAdapter {
    companion object{
        @JvmStatic
        @BindingAdapter("bind:adapter")
        fun initAdapter(recyclerView: RecyclerView?, adapter : RecyclerView.Adapter<*>?) {
            val linearLayoutManager = LinearLayoutManager(recyclerView?.context)
            linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            recyclerView?.layoutManager = linearLayoutManager
            recyclerView?.adapter = adapter
        }
    }
}