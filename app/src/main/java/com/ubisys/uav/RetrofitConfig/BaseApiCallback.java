package com.ubisys.uav.RetrofitConfig;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public abstract class BaseApiCallback<T> implements Callback<ApiResponse<T>> {

    protected abstract void onSuccess(Call<ApiResponse<T>> call, T t);

    protected void onSuccessWrapper(Call<ApiResponse<T>> call, ApiResponse<T> resp) {
        onSuccess(call, resp.getBody());
    }

    protected abstract void onApiError(Call<ApiResponse<T>> call, String code, String msg);

    @Override
    public void onResponse(Call<ApiResponse<T>> call, Response<ApiResponse<T>> response) {
        if (response.isSuccessful()) {
            ApiResponse<T> resp = response.body();
            if (!resp.isSuccessful()) {
                onApiError(call, resp.getCode(), resp.getMsg());
            } else {
                onSuccessWrapper(call, resp);
            }
        } else {
            onFailure(call, new HttpException(response));
        }
    }

    @Override
    public void onFailure(Call<ApiResponse<T>> call, Throwable t) {

    }


}
