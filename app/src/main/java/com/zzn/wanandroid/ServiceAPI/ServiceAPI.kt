package com.zzn.wanandroid.ServiceAPI

import com.zzn.wanandroid.bean.*
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by 郑振楠 on 2017/6/27.
 */

interface ServiceAPI {


    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("/user/login")
    fun getLogin(@Field ("username") userName:String,@Field ("password") password:String ): Call<loginbean>

    /**
     * 获取首页
     */

    @GET()
    fun gethome(@Url url: String): Call<Test>

    /**
     * 获取热词
     */
    @GET("/hotkey/json")
    fun getHot(): Call<HotTextBean>

    /**
     * 得到项目分类
     */
    @GET("/project/tree/json")
    fun getProject(): Call<Project>

    /**
     * 得到项目分类内容
     */
    @GET()
    fun getprojectcontext(@Url url: String): Call<projectcontextbean>

    /**
     * 首页轮播图
     */
    @GET("/banner/json")
    fun getBanner(): Call<Banner>

    /**
     * 收藏站内文章
     */
    @POST()
    fun getLike(@Url url: String):Call<String>

    /**
     * 我的收藏
     */

    @GET
    fun minegetLike(@Url url:String ):Call<mineLike>



}

