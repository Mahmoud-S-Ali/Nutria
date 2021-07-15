package com.example.nutria.di.module

import androidx.core.util.Supplier
import androidx.lifecycle.ViewModelProvider
import com.example.nutria.base.BaseActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(activity: BaseActivity<*>) {

    private val activity: BaseActivity<*> = activity
}