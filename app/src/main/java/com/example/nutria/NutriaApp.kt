package com.example.nutria

import android.app.Application
import com.example.nutria.di.component.AppComponent
import com.example.nutria.di.component.DaggerAppComponent
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import javax.inject.Inject

class NutriaApp : Application() {

    var appComponent: AppComponent? = null

    @Inject
    internal lateinit var calligraphyConfig: CalligraphyConfig

    override fun onCreate() {
        super.onCreate()

        initDagger()
        initCalligraphy()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder()
            .application(this)
            ?.build()

        appComponent?.inject(this)
    }

    private fun initCalligraphy() {
        CalligraphyConfig.initDefault(calligraphyConfig)
    }
}