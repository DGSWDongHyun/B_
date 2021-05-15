package com.simple.b_.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.simple.b_.databinding.ItemCardMealBinding
import com.simple.b_.viewmodel.meal.MealViewModel
import com.simple.data.model.MealInfo

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
    val viewModel = MealViewModel()

    fun bind(data : MealInfo) {
        viewModel.bind(data)
        item.mealViewModel = viewModel

        item.root.onFocusChangeListener = OnFocusChangeListener { v: View?, hasFocus: Boolean ->
            if (hasFocus) {
                ViewCompat.animate(v!!).scaleX(1.12f).scaleY(1.12f).setDuration(30)
                    .translationZ(1f).start()
            } else {
                ViewCompat.animate(v!!).scaleX(1.0f).scaleY(1.0f).setDuration(10).translationZ(0f)
                    .start()
            }
        }
    }
}