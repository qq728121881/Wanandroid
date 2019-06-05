package com.zzn.wanandroid.RetrofitConfig


import retrofit2.Call

/**
 * Created by liulinjie on 11/07/2016.
 * api回调封装
 */

abstract class SimpleApiCallback<T> : BaseApiCallback<T>() {

    override fun onApiError(call: Call<ApiResponse<T>>, code: String?, msg: String?) {
        //        ToastUtils.showShortToast(msg);

    }

    override fun onFailure(call: Call<ApiResponse<T>>, t: Throwable) {

    }

    override fun onSuccess(call: Call<ApiResponse<T>>, t: T?) {


    }

}
