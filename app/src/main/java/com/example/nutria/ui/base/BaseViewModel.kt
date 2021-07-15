package com.example.nutria.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nutria.data.network.StateCodes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import javax.inject.Inject

abstract class BaseViewModel<out M> : ViewModel() {

    //CoRoutines
    @Inject
    internal lateinit var viewModelJob: Job

    @Inject
    internal lateinit var coroutineScope: CoroutineScope

    protected val _dataState = MutableLiveData<StateCodes>()
    val dataState: LiveData<StateCodes>
        get() = _dataState

    val service: M
        get() = this.getRetrofitService()

    abstract fun getRetrofitService(): M


    fun <N> loadData(
        call: Call<N>, liveDataObj: MutableLiveData<N>? = null,
        responseHandler: BaseCallBack.ResponseHandler<N>? = null) {
        coroutineScope.launch {
            object : BaseCallBack<N> (responseHandler) {
                override fun getServiceCall(): Call<N> {
                    _dataState.postValue(StateCodes.LOADING)
                    return call;
                }

                override fun handleFailure(msg: String?, code: Int, errorBody: ResponseBody?) {
                    when (code) {
                        401 -> _dataState.postValue(StateCodes.UNAUTHORIZED)
                        -1 -> _dataState.postValue(StateCodes.NETWORK_ERROR)
                        else -> {
                            _dataState.postValue(StateCodes.getStateFromCode(code))
                        }
                    }
                }

                override fun handleResponse(response: N?) {
                    _dataState.postValue(StateCodes.SUCCESSFUL)
                    liveDataObj?.postValue(response)
                    responseHandler?.onResponse(response = response)
                }
            }
        }
    }

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }
}