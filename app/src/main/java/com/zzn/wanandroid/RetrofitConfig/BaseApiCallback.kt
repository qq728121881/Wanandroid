package com.zzn.wanandroid.RetrofitConfig


import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

abstract class BaseApiCallback<T> : Callback<ApiResponse<T>> {

    protected abstract fun onSuccess(call: Call<ApiResponse<T>>, t: T?)

    protected fun onSuccessWrapper(call: Call<ApiResponse<T>>, resp: ApiResponse<T>) {
        onSuccess(call, resp.body)
    }

    protected abstract fun onApiError(call: Call<ApiResponse<T>>, code: String?, msg: String?)

    override fun onResponse(call: Call<ApiResponse<T>>, response: Response<ApiResponse<T>>) {
        if (response.isSuccessful) {
            val resp = response.body()
            if (!resp.isSuccessful) {
                onApiError(call, resp.code, resp.msg)
            } else {
                onSuccessWrapper(call, resp)
            }
        } else {
            onFailure(call, HttpException(response))
        }
    }

    override fun onFailure(call: Call<ApiResponse<T>>, t: Throwable) {

    }


}
