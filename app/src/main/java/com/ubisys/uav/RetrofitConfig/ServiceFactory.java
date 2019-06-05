package com.ubisys.uav.RetrofitConfig;

import com.google.gson.Gson;
import com.ubisys.uav.ServiceAPI.ConfigURL;
import com.ubisys.uav.ServiceAPI.ServiceAPI;
import com.ubisys.uav.application.MyApplication;
import com.ubisys.uav.utils.ACache;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/**
 * Created by 郑振楠 on 2018/1/29.
 * 接口调用单例
 */

public class ServiceFactory {

    private static final long MAX_CACHE_SIZE = 10 * 1024 * 1024;
    private static final int TIMEOUT = 30;
    private static final OkHttpClient OK_HTTP_CLIENT;
    static ACache aCache;
    private static String token;

    static {
        aCache = ACache.get(MyApplication.getContext());
        final OkHttpClient.Builder builder = new OkHttpClient.Builder()
//                .cache(new Cache(new File(App.getApp().getFilesDir(), "retrofit"), MAX_CACHE_SIZE))
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .followRedirects(true)
                .retryOnConnectionFailure(true);

        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                token = aCache.getAsString("Token");
                Request originalRequest = chain.request();
                Request authorised = originalRequest.newBuilder()
                        .header("Authorization", "Bearer " + token)
                        .build();
                return chain.proceed(authorised);

            }
        });


        OK_HTTP_CLIENT = builder.build();

    }

    private static ServiceAPI businessApi;

    public static ServiceAPI getBusinessApi() {

        if (businessApi == null) {
            synchronized (ServiceFactory.class) {
                if (businessApi == null) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create(new Gson()))
                            //配置的URL
                            .baseUrl(ConfigURL.API_URL)
                            .client(OK_HTTP_CLIENT)
                            .build();

                    businessApi = retrofit.create(ServiceAPI.class);

                }
            }
        }
        return businessApi;
    }

}
