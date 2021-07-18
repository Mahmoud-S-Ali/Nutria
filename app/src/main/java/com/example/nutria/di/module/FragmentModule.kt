package com.example.nutria.di.module

import androidx.core.util.Supplier
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nutria.ViewModelProviderFactory
import com.example.nutria.base.BaseFragment
import com.example.nutria.data.DataManager
import com.example.nutria.data.model.api.Ingredient
import com.example.nutria.data.model.api.IngredientParsedData
import com.example.nutria.ui.ingredients.IngredientsViewModel
import com.example.nutria.ui.summary.SummaryRecyclerAdapter
import com.example.nutria.ui.summary.SummaryViewModel
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

    @Provides
    fun provideSummaryViewModel(dataManager: DataManager): SummaryViewModel? {
        val supplier: Supplier<SummaryViewModel> =
            Supplier {
                SummaryViewModel(dataManager)
            }

        val factory: ViewModelProviderFactory<SummaryViewModel> = ViewModelProviderFactory(
            SummaryViewModel::class.java, supplier
        )

        return ViewModelProvider(fragment, factory).get(SummaryViewModel::class.java)
    }

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager? {
        return LinearLayoutManager(fragment.activity)
    }

    @Provides
    fun provideSummaryAdapter(): SummaryRecyclerAdapter? {
        return SummaryRecyclerAdapter(ArrayList<Ingredient>())
    }
}