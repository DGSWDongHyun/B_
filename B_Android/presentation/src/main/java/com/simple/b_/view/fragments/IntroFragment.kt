package com.simple.b_.view.fragments

import com.simple.b_.R
import com.simple.b_.base.BaseFragment
import com.simple.b_.databinding.FragmentIntroBinding
import com.simple.b_.viewmodel.intro.IntroViewModel


class IntroFragment : BaseFragment<FragmentIntroBinding, IntroViewModel>() {

    override val viewModel: IntroViewModel
        get() = IntroViewModel()

    override val layoutRes: Int
        get() = R.layout.fragment_intro

}