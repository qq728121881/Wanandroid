package com.zzn.wanandroid.ui.projectframent

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

import com.zzn.wanandroid.R
import com.zzn.wanandroid.RetrofitConfig.ServiceFactory
import com.zzn.wanandroid.ServiceAPI.ConfigURL
import com.zzn.wanandroid.adapter.ProjectContextAdapter
import com.zzn.wanandroid.bean.DataX
import com.zzn.wanandroid.bean.DataXcontext
import com.zzn.wanandroid.bean.projectcontextbean
import com.zzn.wanandroid.utils.ProgressUtil
import com.zzn.wanandroid.utils.ToastUtil
import kotlinx.android.synthetic.main.fragment_project.*
import kotlinx.android.synthetic.main.fragment_project.view.*
import retrofit2.Call
import retrofit2.Response
import java.util.*


@SuppressLint("ValidFragment")
class ProjectFragment : Fragment {

    var id1: Int

    constructor(id: Int) : super() {
        this.id1 = id
    }

    lateinit var homeAdapter: ProjectContextAdapter
    lateinit var recycler: RecyclerView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_project, container, false)
        //每次初始化页数设置为0
        page=0
        recycler = view.findViewById(R.id.recycler) as RecyclerView
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler.layoutManager = layoutManager

        LoadMoreListener(recycler)
        getdata(id1)

        Log.e("Tag", "初始化$id1")

        return view
    }


    fun getframentdata(id: Int) {
        Log.e("Tag", "请求数据")
//        getdata(id)

    }


    var lastVisibleItem: Int = 0
    var ismore: Boolean = false
    var islogin = true
    private fun LoadMoreListener(recycler: RecyclerView) {

        recycler.setOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
//                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == recycler.adapter!!.itemCount) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE && !recyclerView.canScrollVertically(1)) {

                    more_rl1.visibility = View.VISIBLE
                    ismore = true
//                    Handler().postDelayed(Runnable {
//                        more_rl1.visibility = View.GONE
                    if (page != pageCount) {
                        if (islogin) {
                            islogin = false

                            getdata(id)
                        }

                    } else {
                        ToastUtil.showToast(activity, "没有更多数据")
                        more_rl1.visibility = View.GONE
                    }


//                    }, 5000)


                }


            }


            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                //最后一个可见的ITEM
                lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                Log.e("TAG", lastVisibleItem.toString())
                more_rl1.visibility = View.VISIBLE
            }
        })


    }

    var page: Int = 0
    var pageCount = 0
    private fun getdata(id: Int) {

        page++
        var uri = ConfigURL.Object.API_URL + "/project/list/%d/%s"
        val url = String.format(Locale.getDefault(), uri, page, "json?cid=$id")
        Log.e("TAG", url)
        ProgressUtil.show(activity, "正在加载....")
        ProgressUtil.isShowing()

        ServiceFactory.getBusinessApi()!!.getprojectcontext(url).enqueue(object : retrofit2.Callback<projectcontextbean> {
            override fun onFailure(call: Call<projectcontextbean>?, t: Throwable?) {
                ProgressUtil.hide()
            }

            override fun onResponse(call: Call<projectcontextbean>?, response: Response<projectcontextbean>?) {
                islogin = true
                ProgressUtil.hide()
                val code = response!!.code()
                Log.e("TAG", code.toString())
                pageCount = response.body().data.pageCount

                val test = response.body()

                val datas = test.data.datas


                if (!ismore) {
                    homeAdapter = ProjectContextAdapter(datas, activity)
                    recycler.adapter = homeAdapter
                } else {
                    more_rl1.visibility = View.GONE
                    homeAdapter.addDatas(datas)

                }


            }


        })


    }

}
