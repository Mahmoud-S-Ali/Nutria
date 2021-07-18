package com.example.nutria.di.component

import com.example.nutria.di.module.FragmentModule
import com.example.nutria.di.scope.FragmentScope
import com.example.nutria.ui.ingredients.IngredientsFragment
import com.example.nutria.ui.main.MainActivity
import com.example.nutria.ui.summary.SummaryFragment
import com.example.nutria.ui.totalNutrients.TotalNutrientsFragment
import dagger.Component

@FragmentScope
@Component(modules = [FragmentModule::class], dependencies = [AppComponent::class])
interface FragmentComponent {

    fun inject(fragment: IngredientsFragment?)

    fun inject(fragment: SummaryFragment?)

    fun inject(fragment: TotalNutrientsFragment?)
}