package com.zzn.wanandroid.bean

/**
 *    author : 郑振楠
 *    date   : 2019/6/17
 */
data class Project(
    val `data`: List<Dataproject>,
    val errorCode: Int,
    val errorMsg: String
)

data class Dataproject(
    val children: List<Any>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)
