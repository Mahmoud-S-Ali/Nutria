package com.example.nutria.di.module

import android.app.Application
import android.content.Context
import com.example.nutria.data.AppDataManager
import com.example.nutria.data.DataManager
import com.example.nutria.data.network.ApiHelper
import com.example.nutria.data.network.AppApiHelper
import com.example.nutria.data.network.NetworkConstants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import java.util.concurrent.TimeUnit
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

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(logging.apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideNutritionRetrofit(client: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(NetworkConstants.NUTRITION_BASE_URL)
            .client(client)
            .build()
    }

    @Provides
    fun provideViewModelJob(): Job {
        return Job()
    }

    @Provides
    fun provideCoroutineScope(viewModelJob: Job): CoroutineScope {
        return CoroutineScope(viewModelJob + Dispatchers.Main)
    }

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: AppApiHelper): ApiHelper {
        return apiHelper
    }

    @Provides
    @Singleton
    fun provideDataManager(dataManager: AppDataManager): DataManager {
        return dataManager
    }
}