package com.zzn.wanandroid.bean

/**
 *    author : 郑振楠
 *    date   : 2019/6/11
 */
data class HotTextBean(
        val `data`: List<Data1>,
        val errorCode: Int,
        val errorMsg: String
)

data class Data1(
        val id: Int,
        val link: String,
        val name: String,
        val order: Int,
        val visible: Int
)