package com.zzn.wanandroid.frament

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.zzn.wanandroid.R
import com.zzn.wanandroid.RetrofitConfig.ServiceFactory
import com.zzn.wanandroid.adapter.projectAdapter
import com.zzn.wanandroid.bean.Project
import com.zzn.wanandroid.ui.projectframent.ProjectFragment
import com.zzn.wanandroid.utils.BanViewPager
import kotlinx.android.synthetic.main.fragment_message.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MessageFragment : Fragment() {
    lateinit var list: ArrayList<Fragment>
    lateinit var tabLayout: TabLayout
    lateinit var pag: BanViewPager
    lateinit var frament:ProjectFragment

    @TargetApi(Build.VERSION_CODES.M)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_message, container, false)

        tabLayout = view.findViewById(R.id.tab) as TabLayout
        pag = view.findViewById(R.id.pager) as BanViewPager

        list = ArrayList<Fragment>()

        getData()



        return view
    }

    private fun getData() {


        ServiceFactory.getBusinessApi()!!.getProject().enqueue(object : Callback<Project> {
            override fun onFailure(call: Call<Project>?, t: Throwable?) {


            }

            @RequiresApi(Build.VERSION_CODES.M)
            override fun onResponse(call: Call<Project>?, response: Response<Project>?) {

                if (response!!.code() == 200) {

                    val data = response.body().data


                    for (i in data.indices) {
                        frament = ProjectFragment(data.get(i).id)
                        list.add(frament)

                    }


                    for (i in list.indices) {

                        tabLayout.addTab(tabLayout.newTab().setText(i.toString()))

                    }


                    val adapter = projectAdapter(activity, data, activity!!.getSupportFragmentManager(), list)

                    pag.adapter = adapter
                    tabLayout.setupWithViewPager(pager)

                    //设置可以滑动
                    tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE)




                    pager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

                        }

                        override fun onPageSelected(position: Int) {
                            frament.id1=data.get(position).id
//                            frament.getframentdata(data.get(position).id)



                        }

                        override fun onPageScrollStateChanged(state: Int) {

                        }
                    })
                }


            }
        })


    }


}
