package com.simple.b_.view.fragments

import android.content.Context
import android.content.SharedPreferences
import com.simple.b_.R
import com.simple.b_.base.BaseFragment
import com.simple.b_.databinding.FragmentIntroSecondBinding
import com.simple.b_.viewmodel.intro.IntroViewModel


class IntroSecondFragment : BaseFragment<FragmentIntroSecondBinding, IntroViewModel>() {

    private val sharedPreferences : SharedPreferences by lazy { requireActivity().getSharedPreferences( "localSharedPreference", Context.MODE_PRIVATE) }


    override val viewModel: IntroViewModel
        get() = IntroViewModel()
    override val layoutRes: Int
        get() = R.layout.fragment_intro_second

    override fun onDestroy() {
        super.onDestroy()
        sharedPreferences.edit().putBoolean("isFirst", false).apply()
    }
}