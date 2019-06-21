package com.zzn.wanandroid.frament

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.zzn.wanandroid.RetrofitConfig.ServiceFactory
import com.zzn.wanandroid.ServiceAPI.ConfigURL
import com.zzn.wanandroid.adapter.HomeAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Response
import java.util.*
import android.os.Handler
import android.view.Gravity
import android.widget.TextView
import com.google.android.flexbox.FlexboxLayout
import com.zzn.wanandroid.R
import com.zzn.wanandroid.bean.*
import com.zzn.wanandroid.ui.home.HomeWebView
import com.zzn.wanandroid.utils.GlideImageLoader
import com.zzn.wanandroid.utils.ProgressUtil
import retrofit2.Callback
import kotlin.collections.ArrayList


class HomeFragment : Fragment() {

    lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(com.zzn.wanandroid.R.layout.fragment_home, container, false)
        //热搜recycleview
        val banner = view.findViewById(R.id.banner) as com.youth.banner.Banner
        banner.setImageLoader(GlideImageLoader())

        val recycler = view.findViewById(com.zzn.wanandroid.R.id.recycler) as RecyclerView
        val mFlexboxLayout = view.findViewById(com.zzn.wanandroid.R.id.mFlexboxLayout) as FlexboxLayout
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler.layoutManager = layoutManager

        LoadMoreListener(recycler)

        getdata()
        //热词搜索
        getHotText(mFlexboxLayout)

        //得到轮播图
        getBanner(banner)

        banner.setOnBannerListener {

            val intent = Intent(Intent(context, HomeWebView::class.java))
            intent.putExtra("title", data.get(it).title)
            intent.putExtra("link", data.get(it).url)
            startActivity(intent)

        }

        return view

    }

    var imagelist = ArrayList<String>()
    var titlelist = ArrayList<String>()
    lateinit var data: List<BannerData>
    private fun getBanner(banner: com.youth.banner.Banner) {

        ServiceFactory.getBusinessApi()!!.getBanner().enqueue(object : Callback<Banner> {
            override fun onFailure(call: Call<Banner>?, t: Throwable?) {


            }

            override fun onResponse(call: Call<Banner>?, response: Response<Banner>?) {

                data = response!!.body().data

                for (i in data.indices) {
                    imagelist.add(data.get(i).imagePath)
                    titlelist.add(data.get(i).title)
                }

                banner.setBannerTitles(titlelist)
                banner.setImages(imagelist)
                banner.start()


            }


        })


    }

    private fun getHotText(mFlexboxLayout: FlexboxLayout) {


        ServiceFactory.getBusinessApi()!!.getHot().enqueue(object : Callback<HotTextBean> {
            override fun onFailure(call: Call<HotTextBean>?, t: Throwable?) {
                val s = t!!.message
            }

            @SuppressLint("WrongConstant")
            override fun onResponse(call: Call<HotTextBean>?, response: Response<HotTextBean>?) {
                var code = response!!.code()
                if (code == 200) {
                    val mutableList = response.body().data

                    for (i in 0 until mutableList.size) {
                        val textView = TextView(activity)
                        textView.text = mutableList.get(i).name
                        textView.height = 60
                        textView.gravity = Gravity.CENTER
                        textView.setPadding(30, 10, 30, 10)
                        textView.setBackgroundResource(com.zzn.wanandroid.R.drawable.bg_edittext_selector)

                        mFlexboxLayout.addView(textView)


                    }
                }


            }

        })


    }


    var lastVisibleItem: Int = 0
    var ismore: Boolean = false
    private fun LoadMoreListener(recycler: RecyclerView) {

        recycler.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == recycler.adapter!!.itemCount) {
                    more_rl.visibility = View.VISIBLE
                    ismore = true
//                    Handler().postDelayed(Runnable {
                    more_rl.visibility = View.GONE
                    getdata()

//                    }, 3000)


                }


            }


            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                //最后一个可见的ITEM
                lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                more_rl.visibility = View.VISIBLE
            }
        })


    }

    var page: Int = 0

    private fun getdata() {

        var uri = ConfigURL.Object.API_URL + "/article/list/%d/json"
        val url = String.format(Locale.getDefault(), uri, page)

        ProgressUtil.show(activity, "正在加载中...")
        ServiceFactory.getBusinessApi()!!.gethome(url).enqueue(object : retrofit2.Callback<Test> {
            override fun onFailure(call: Call<Test>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<Test>?, response: Response<Test>?) {
                ProgressUtil.hide()
                page++
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


 
