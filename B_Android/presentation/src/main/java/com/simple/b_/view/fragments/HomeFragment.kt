package com.simple.b_.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.simple.b_.R
import com.simple.b_.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private val homeBinding : FragmentHomeBinding by lazy { DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_home) }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View {
        return homeBinding.root
    }
}