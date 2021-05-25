package com.simple.b_.view.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.simple.b_.R
import com.simple.b_.base.BaseActivity
import com.simple.b_.databinding.ActivityIntroBinding
import com.simple.b_.viewmodel.intro.IntroViewModel

class IntroActivity : BaseActivity<ActivityIntroBinding, IntroViewModel>() {
    override val viewModel: IntroViewModel
        get() = IntroViewModel()
    override val layoutRes: Int
        get() = R.layout.activity_intro


}