package com.zzn.wanandroid.ServiceAPI

import com.zzn.wanandroid.bean.Test
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by 郑振楠 on 2017/6/27.
 */

interface ServiceAPI {
    /**
     * 获取首页
     */

    @GET()
    fun gethome(@Url url: String): Call<Test>


}

