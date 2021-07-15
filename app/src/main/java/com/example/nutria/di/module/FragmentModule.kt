package com.example.nutria.di.module

import androidx.core.util.Supplier
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nutria.base.BaseFragment
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import java.util.*

@Module
class FragmentModule(fragment: BaseFragment<*, *>) {
    private val fragment: BaseFragment<*, *> = fragment

    @Provides
    fun provideViewModelJob(): Job {
        return Job()
    }

    @Provides
    fun provideCoroutineScope(viewModelJob: Job): CoroutineScope {
        return CoroutineScope(viewModelJob + Dispatchers.Main)
    }
}