package com.example.nutria.di.component

import com.example.nutria.di.module.FragmentModule
import com.example.nutria.di.scope.FragmentScope
import dagger.Component

@FragmentScope
@Component(modules = [FragmentModule::class], dependencies = [AppComponent::class])
interface FragmentComponent {
}