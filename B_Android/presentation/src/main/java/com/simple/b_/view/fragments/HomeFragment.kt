package com.simple.b_.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.simple.b_.R
import com.simple.b_.databinding.FragmentHomeBinding
import com.simple.b_.view.adapters.WeatherAdapter
import com.simple.b_.viewmodel.home.HomeViewModel


class HomeFragment : Fragment() {

    private lateinit var homeBinding : FragmentHomeBinding

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View {
        homeBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container, false)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeBinding.homeViewModel = HomeViewModel(requireActivity().application)
        homeBinding.lifecycleOwner = requireActivity()
    }
}