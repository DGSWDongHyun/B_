package com.simple.b_.view.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.simple.b_.base.BaseAdapter
import com.simple.b_.databinding.ItemCardMealBinding
import com.simple.b_.viewmodel.meal.MealViewModel
import com.simple.data.model.MealInfo

class MealAdapter (val context : Context, private var mealList : ArrayList<MealInfo> = arrayListOf()) :
    BaseAdapter<ItemCardMealBinding, MealViewModel, MealViewHolder>(){

    fun setData(mealList : ArrayList<MealInfo>) {
        this.mealList = mealList
        Log.d("TAG", "onSuccessData :: ${mealList}")
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder = viewHolder
    override fun onBindViewHolder(holder: MealViewHolder, position: Int) = holder.bind(mealList[position])
    override fun getItemCount() = mealList.size

    override val viewHolder: MealViewHolder
        get() = MealViewHolder(item)

    override val item: ItemCardMealBinding
        get() = ItemCardMealBinding.inflate(LayoutInflater.from(context))
}

class MealViewHolder(val item : ItemCardMealBinding) : RecyclerView.ViewHolder(item.root) {
    val viewModel = MealViewModel()

    fun bind(data : MealInfo) {
        viewModel.bind(data)
        item.mealViewModel = viewModel
    }
}