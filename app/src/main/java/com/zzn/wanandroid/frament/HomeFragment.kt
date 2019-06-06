package com.zzn.wanandroid.frament

import android.opengl.Visibility
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.zzn.wanandroid.R

import com.zzn.wanandroid.RetrofitConfig.ServiceFactory
import com.zzn.wanandroid.ServiceAPI.ConfigURL
import com.zzn.wanandroid.adapter.HomeAdapter
import com.zzn.wanandroid.bean.Test
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Response
import java.util.*
import com.zzn.wanandroid.MainActivity
import android.support.v4.os.HandlerCompat.postDelayed
import android.os.Handler
import com.zzn.wanandroid.bean.DataX


class HomeFragment : Fragment() {

    lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(com.zzn.wanandroid.R.layout.fragment_home, container, false)
        val recycler = view.findViewById(com.zzn.wanandroid.R.id.recycler) as RecyclerView

        val layoutManager = LinearLayoutManager(activity)
        recycler.layoutManager = layoutManager

        LoadMoreListener(recycler)
        getdata()
        return view

    }

    var lastVisibleItem: Int = 0
    var ismore: Boolean = false
    private fun LoadMoreListener(recycler: RecyclerView) {

        recycler.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == recycler.adapter!!.itemCount) {
                    more_rl.visibility = View.VISIBLE
                    ismore=true
                    Handler().postDelayed(Runnable {
                        more_rl.visibility = View.GONE
                        getdata()

                    }, 3000)


                }


            }


            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                //最后一个可见的ITEM
                lastVisibleItem = layoutManager.findLastVisibleItemPosition()
            }
        })


    }

    var page: Int = 0

    private fun getdata() {
        page++
        var uri = ConfigURL.Object.API_URL + "/article/list/%d/json"
        val url = String.format(Locale.getDefault(), uri, page)

        ServiceFactory.getBusinessApi()!!.gethome(url).enqueue(object : retrofit2.Callback<Test> {
            override fun onFailure(call: Call<Test>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<Test>?, response: Response<Test>?) {
                val code = response!!.code()
                Log.e("TAG", code.toString())
                val test = response.body()
                val datas: MutableList<DataX> = test.data.datas
                val desc = test.data.datas[0].title
                if (!ismore) {
                    homeAdapter = HomeAdapter(datas, activity)
                    recycler.adapter = homeAdapter
                } else {
                    homeAdapter.addDatas(datas)
                }


            }


        })


    }


}


 
