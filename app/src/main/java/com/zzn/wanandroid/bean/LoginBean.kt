package com.zzn.wanandroid.bean

/**
 *    author : 郑振楠
 *    date   : 2019/6/17
 */
data class loginbean(
        val `data`: Datalogin,
        val errorCode: Int,
        val errorMsg: String
)

data class Datalogin(
        val admin: Boolean,
        val chapterTops: List<Any>,
        val collectIds: List<Any>,
        val email: String,
        val icon: String,
        val id: Int,
        val password: String,
        val token: String,
        val type: Int,
        val username: String
)