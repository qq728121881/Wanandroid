package com.zzn.wanandroid.bean

/**
 *    author : 郑振楠
 *    date   : 2019/6/18
 */
data class projectcontextbean(
    val `data`: Datacontext,
    val errorCode: Int,
    val errorMsg: String
)

data class Datacontext(
    val curPage: Int,
    val datas: MutableList<DataXcontext>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)

data class DataXcontext(
    val apkLink: String,
    val author: String,
    val chapterId: Int,
    val chapterName: String,
    var collect: Boolean,
    val courseId: Int,
    val desc: String,
    val envelopePic: String,
    val fresh: Boolean,
    val id: Int,
    val link: String,
    val niceDate: String,
    val origin: String,
    val prefix: String,
    val projectLink: String,
    val publishTime: Long,
    val superChapterId: Int,
    val superChapterName: String,
    val tags: List<Tagcontext>,
    val title: String,
    val type: Int,
    val userId: Int,
    val visible: Int,
    val zan: Int
)

data class Tagcontext(
    val name: String,
    val url: String
)