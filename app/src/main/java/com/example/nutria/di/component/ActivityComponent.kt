package com.example.nutria.di.component

import com.example.nutria.di.module.ActivityModule
import com.example.nutria.di.scope.ActivityScope
import com.example.nutria.ui.main.MainActivity
import dagger.Component

@ActivityScope
@Component(modules = [ActivityModule::class], dependencies = [AppComponent::class])
interface ActivityComponent {
    fun inject(activity: MainActivity?)
}