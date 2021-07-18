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

abstract class BaseViewModel: ViewModel() {

    protected val _dataState = MutableLiveData<StateCodes>()
    val dataState: LiveData<StateCodes>
        get() = _dataState

    override fun onCleared() {
        //viewModelJob.cancel()
        super.onCleared()
    }
}