package com.simple.b_.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.simple.b_.BR

abstract class BaseFragment<VB : ViewDataBinding, VM : ViewModel> : Fragment(){

    private lateinit var binding : VB

    abstract val viewModel : VM

    abstract val layoutRes : Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initializedBinding(container)

        return binding.root
    }

    private fun initializedBinding(container: ViewGroup?) {
        binding = DataBindingUtil.inflate(layoutInflater, layoutRes, container, false)
        binding.setVariable(BR.viewModel, viewModel)

        binding.lifecycleOwner = requireActivity()
    }

}