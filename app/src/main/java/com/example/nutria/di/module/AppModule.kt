package com.example.nutria.di.module

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Job
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideCalligraphyDefaultConfig(): CalligraphyConfig {
        return CalligraphyConfig.Builder()
            //TODO: Add font here
            //.setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
            //.setFontAttrId(R.attr.fontPath)
            .build()
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }
}