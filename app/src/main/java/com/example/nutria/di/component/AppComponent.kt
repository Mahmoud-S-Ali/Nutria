package com.example.nutria.di.component

import android.app.Application
import com.example.nutria.NutriaApp
import com.example.nutria.data.DataManager
import com.example.nutria.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(app: NutriaApp?)

    fun getDataManager(): DataManager

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application?): Builder?
        fun build(): AppComponent?
    }
}