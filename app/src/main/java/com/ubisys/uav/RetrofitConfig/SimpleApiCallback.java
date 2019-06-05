package com.ubisys.uav.RetrofitConfig;



import retrofit2.Call;

/**
 * Created by liulinjie on 11/07/2016.
 * api回调封装
 */

public abstract class SimpleApiCallback<T> extends BaseApiCallback<T> {

    @Override
    protected void onApiError(Call<ApiResponse<T>> call, String code, String msg) {
//        ToastUtils.showShortToast(msg);

    }

    @Override
    public void onFailure(Call<ApiResponse<T>> call, Throwable t) {

    }

    @Override
    protected void onSuccess(Call<ApiResponse<T>> call, T t) {


    }

}
