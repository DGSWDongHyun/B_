package com.simple.b_.view.adapters

import android.os.Handler
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simple.b_.databinding.ItemCardMealBinding
import com.simple.data.model.MealInfo
import com.simple.data.model.MealsData
import com.simple.data.model.RowData
import com.simple.data.model.WeatherData

class MealAdapter(private var mealList : ArrayList<MealInfo> = arrayListOf()) : RecyclerView.Adapter<MealViewHolder>(){

    fun setData(mealList : ArrayList<MealInfo>) {
        this.mealList = mealList
        Log.d("TAG", "onSuccessData :: ${mealList}")
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val layoutBinder = ItemCardMealBinding.inflate(LayoutInflater.from(parent.context))

        return MealViewHolder(layoutBinder)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bind(mealList[position])
    }

    override fun getItemCount() = mealList.size
}

class MealViewHolder(val item : ItemCardMealBinding) : RecyclerView.ViewHolder(item.root) {
    fun bind(data : MealInfo) {
        item.mealTitle.text = data.MMEAL_SC_NM
        item.mealContents.text = Html.fromHtml(data.DDISH_NM, 1)
        item.executePendingBindings()
    }
}