package com.simple.b_.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.simple.b_.databinding.ItemCardMealBinding

abstract class BaseAdapter<VB : ViewDataBinding, VM : ViewModel, VH : RecyclerView.ViewHolder> :
    RecyclerView.Adapter<VH>() {

    abstract val item : VB
    abstract val viewHolder : VH
}