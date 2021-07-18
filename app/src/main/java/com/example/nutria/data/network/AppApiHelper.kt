package com.example.nutria.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nutria.BuildConfig
import com.example.nutria.data.model.api.IngredientDetailsRequest
import com.example.nutria.data.model.api.IngredientDetailsResponse
import com.example.nutria.ui.base.BaseCallBack
import com.example.nutria.data.network.retrofitService.IngredientsService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Inject

class AppApiHelper @Inject constructor(private val coroutineScope:CoroutineScope,
                                       private val retrofit: Retrofit) : ApiHelper {

    override fun getIngredientsDetails(ingredients: List<String>,
                                       result: MutableLiveData<IngredientDetailsResponse>,
                                       dataState: MutableLiveData<StateCodes>) {
        loadData(retrofit.create(IngredientsService::class.java).getIngredientDetails(
            BuildConfig.NUTRITION_APP_ID,
            BuildConfig.NUTRITION_APP_KEY,
            IngredientDetailsRequest(ingredients)
        ), result, dataState, object : BaseCallBack.ResponseHandler<IngredientDetailsResponse> {
            override fun onResponse(response: IngredientDetailsResponse?) {
                if (response == null) {
                    Timber.e("Ingredient Details returned with null")
                    dataState.postValue(StateCodes.GENERAL_ERROR)
                    return
                }
            }
        })
    }

    fun <N> loadData(
        call: Call<N>, liveDataObj: MutableLiveData<N>? = null,
        dataState: MutableLiveData<StateCodes>,
        responseHandler: BaseCallBack.ResponseHandler<N>? = null) {
        coroutineScope.launch {
            object : BaseCallBack<N>(responseHandler) {
                override fun getServiceCall(): Call<N> {
                    dataState.postValue(StateCodes.LOADING)
                    return call;
                }

                override fun handleFailure(msg: String?, code: Int, errorBody: ResponseBody?) {
                    when (code) {
                        401 -> dataState.postValue(StateCodes.UNAUTHORIZED)
                        404 -> dataState.postValue(StateCodes.NOT_FOUND)
                        422 -> dataState.postValue(StateCodes.UNPROCESSABLE_ENTITY)
                        555 -> dataState.postValue(StateCodes.INVALID_RECIPE)
                        -1 -> dataState.postValue(StateCodes.NETWORK_ERROR)
                        else -> {
                            dataState.postValue(StateCodes.getStateFromCode(code))
                        }
                    }
                }

                override fun handleResponse(response: N?) {
                    dataState.postValue(StateCodes.SUCCESSFUL)
                    liveDataObj?.postValue(response)
                    responseHandler?.onResponse(response = response)
                }
            }
        }
    }
}