package com.zzn.wanandroid.bean

/**
 *    author : 郑振楠
 *    date   : 2019/6/21
 */

data class mineLike(
    val `data`: mineLikeData,
    val errorCode: Int,
    val errorMsg: String
)

data class mineLikeData(
    val curPage: Int,
    val datas: MutableList<mineLikeDataX>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)

data class mineLikeDataX(
    val author: String,
    val chapterId: Int,
    val chapterName: String,
    val courseId: Int,
    val desc: String,
    val envelopePic: String,
    val id: Int,
    val link: String,
    val niceDate: String,
    val origin: String,
    val originId: Int,
    val publishTime: Long,
    val title: String,
    val userId: Int,
    val visible: Int,
    val zan: Int
)