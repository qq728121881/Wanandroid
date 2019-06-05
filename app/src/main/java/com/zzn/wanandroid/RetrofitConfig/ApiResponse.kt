package com.zzn.wanandroid.RetrofitConfig

import android.text.TextUtils

import com.google.gson.annotations.SerializedName

import java.io.Serializable

/**
 * Created by liulinjie on 14/03/2017.
 * api返回值
 */

class ApiResponse<T> : Serializable {

    @SerializedName("code")
    var code: String? = null

    @SerializedName("msg")
    var msg: String? = null

    @SerializedName("body")
    var body: T? = null

    @SerializedName("weCharSign")
    var weCharSign: WeCharSign? = null

    @SerializedName("aliPaySign")
    var aliPaySign: String? = null

    @SerializedName("upacp")
    var upacp: String? = null

    val isSuccessful: Boolean
        get() {
            if (TextUtils.isEmpty(code)) {
                return false
            }
            try {
                return Integer.parseInt(code!!) == 0
            } catch (e: NumberFormatException) {
                return false
            }

        }

    class WeCharSign {

        var appId: String? = null
        var nonceStr: String? = null
        @SerializedName("package")
        var packageX: String? = null
        var paySign: String? = null
        var timeStamp: String? = null
        var prepay_id: String? = null
        var partnerId: String? = null
    }

    companion object {

        private const val serialVersionUID = -5053125710481712211L
    }
}
