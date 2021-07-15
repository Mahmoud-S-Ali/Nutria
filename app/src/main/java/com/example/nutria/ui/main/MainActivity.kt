package com.example.nutria.ui.main

import android.os.Bundle
import com.example.nutria.R
import com.example.nutria.base.BaseActivity
import com.example.nutria.databinding.ActivityMainBinding
import com.example.nutria.di.component.ActivityComponent

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun performDependencyInjection(buildComponent: ActivityComponent?) {
        buildComponent?.inject(this)
    }
}