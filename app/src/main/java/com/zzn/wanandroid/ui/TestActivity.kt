package com.zzn.wanandroid.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.zzn.wanandroid.R
import com.zzn.wanandroid.RetrofitConfig.ServiceFactory
import com.zzn.wanandroid.ServiceAPI.ConfigURL
import com.zzn.wanandroid.bean.Test
import okhttp3.Request
import retrofit2.Call
import retrofit2.Response
import java.util.*
import javax.security.auth.callback.Callback

@Suppress("UNREACHABLE_CODE")
class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        var uri = ConfigURL.Object.API_URL + "/article/list/%s/json"
        val url = String.format(Locale.getDefault(), uri, "1")

        ServiceFactory.getBusinessApi()!!.gethome(url).enqueue(object : retrofit2.Callback<Test> {
            override fun onFailure(call: Call<Test>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<Test>?, response: Response<Test>?) {
                val code = response!!.code()
                Log.e("TAG", code.toString())
                val test = response.body()
                val desc = test.data.datas[0].title
                Log.e("TAG", desc)
            }


        })


    }
}
