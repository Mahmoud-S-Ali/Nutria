package com.example.nutria.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nutria.BuildConfig
import com.example.nutria.data.model.api.IngredientDetailsResponse
import com.example.nutria.data.network.ApiHelper
import com.example.nutria.data.network.StateCodes
import com.example.nutria.ui.base.BaseCallBack
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import timber.log.Timber
import javax.inject.Inject

class AppDataManager @Inject constructor(private val apiHelper: ApiHelper): DataManager {
    override fun getIngredientsDetails(ingredients: List<String>,
                                       result: MutableLiveData<IngredientDetailsResponse>,
                                       dataState: MutableLiveData<StateCodes>
    ) {
        apiHelper.getIngredientsDetails(ingredients, result, dataState)
    }
}