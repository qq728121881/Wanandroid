package com.zzn.wanandroid.bean

/**
 *    author : 郑振楠
 *    date   : 2019/6/19
 */
data class Banner(
    val `data`: List<BannerData>,
    val errorCode: Int,
    val errorMsg: String
)

data class BannerData(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)