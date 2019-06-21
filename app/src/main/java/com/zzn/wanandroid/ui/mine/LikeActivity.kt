package com.zzn.wanandroid.ui.mine

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.zzn.wanandroid.R
import com.zzn.wanandroid.RetrofitConfig.ServiceFactory
import com.zzn.wanandroid.ServiceAPI.ConfigURL
import com.zzn.wanandroid.adapter.MinLikeAdapter
import com.zzn.wanandroid.bean.mineLike
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class LikeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_like)
        val recycler = findViewById(R.id.recycler) as RecyclerView
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler.layoutManager = layoutManager

        getData(recycler)

    }

    var page: Int = 0
    private fun getData(recycler: RecyclerView) {

        var uri = ConfigURL.Object.API_URL + "/lg/collect/list/%d/json"
        val url = String.format(Locale.getDefault(), uri, page)

        ServiceFactory.getBusinessApi()!!.minegetLike(url).enqueue(object : Callback<mineLike> {
            override fun onFailure(call: Call<mineLike>?, t: Throwable?) {


            }

            override fun onResponse(call: Call<mineLike>?, response: Response<mineLike>?) {
                page++
                val code = response!!.code()

                val datas = response.body().data.datas
                var adapter = MinLikeAdapter(datas, this@LikeActivity)

                recycler.adapter = adapter

            }

        })


    }
}
