package com.example.nutria.di.module

import androidx.core.util.Supplier
import androidx.lifecycle.ViewModelProvider
import com.example.nutria.ViewModelProviderFactory
import com.example.nutria.base.BaseFragment
import com.example.nutria.data.DataManager
import com.example.nutria.ui.ingredients.IngredientsViewModel
import dagger.Module
import dagger.Provides
import java.util.*

@Module
class FragmentModule(fragment: BaseFragment<*, *>) {
    private val fragment: BaseFragment<*, *> = fragment

    @Provides
    fun provideIngredientsViewModel(dataManager: DataManager): IngredientsViewModel? {
        val supplier: Supplier<IngredientsViewModel> =
            Supplier {
                IngredientsViewModel(dataManager)
            }

        val factory: ViewModelProviderFactory<IngredientsViewModel> = ViewModelProviderFactory(
            IngredientsViewModel::class.java, supplier
        )

        return ViewModelProvider(fragment, factory).get(IngredientsViewModel::class.java)
    }
}