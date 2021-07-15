package com.example.nutria.ui.base

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

abstract class BaseCallBack<T>(private val responseHandler: ResponseHandler<T>? = null) : Callback<T> {
    private val call: Call<T>

    init {
        call = this.getServiceCall()
        call.enqueue(this)
    }

    abstract fun handleFailure(msg: String? = null, code: Int = -1, errorBody: ResponseBody? = null)
    abstract fun getServiceCall(): Call<T>
    abstract fun handleResponse(response: T?)

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful)
            handleResponse(response = response.body())
        else handleFailure(code = response.code(), errorBody = response.errorBody())
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        if (t is HttpException)
            handleFailure(code = t.code())
        else handleFailure(t.message)
    }

    interface ResponseHandler<T> {
        fun onResponse(response: T?)
    }
}