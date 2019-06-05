package com.zzn.wanandroid.RetrofitConfig

import com.google.gson.Gson
import com.zzn.wanandroid.ServiceAPI.ConfigURL
import com.zzn.wanandroid.ServiceAPI.ServiceAPI
import com.zzn.wanandroid.application.MyApplication
import com.zzn.wanandroid.utils.ACache

import java.io.IOException
import java.util.concurrent.TimeUnit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


/**
 * Created by 郑振楠 on 2018/1/29.
 * 接口调用单例
 */

object ServiceFactory {

    private val MAX_CACHE_SIZE = (10 * 1024 * 1024).toLong()
    private val TIMEOUT = 30
    private val OK_HTTP_CLIENT: OkHttpClient
//    internal var aCache: ACache
    private var token: String? = null

    private var businessApi: ServiceAPI? = null

    init {
//        aCache = ACache.get(MyApplication.getContext())
        val builder = OkHttpClient.Builder()
                //                .cache(new Cache(new File(App.getApp().getFilesDir(), "retrofit"), MAX_CACHE_SIZE))
                .connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
                .followRedirects(true)
                .retryOnConnectionFailure(true)

        builder.addInterceptor { chain ->
//            token = aCache.getAsString("Token")
            val originalRequest = chain.request()
            val authorised = originalRequest.newBuilder()
//                    .header("Authorization", "Bearer " + token!!)
                    .build()
            chain.proceed(authorised)
        }


        OK_HTTP_CLIENT = builder.build()

    }

    fun getBusinessApi(): ServiceAPI? {

        if (businessApi == null) {
            synchronized(ServiceFactory::class.java) {
                if (businessApi == null) {
                    val retrofit = Retrofit.Builder()
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create(Gson()))
                            //配置的URL
                            .baseUrl(ConfigURL.Object.API_URL)
                            .client(OK_HTTP_CLIENT)
                            .build()

                    businessApi = retrofit.create(ServiceAPI::class.java)

                }
            }
        }
        return businessApi
    }

}
