package com.zzn.wanandroid.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.zzn.wanandroid.bean.Dataproject

/**
 *    author : 郑振楠
 *    date   : 2019/6/17
 */

class projectAdapter(var fm: FragmentActivity?, var data: List<Dataproject>, fm1: FragmentManager?, var list: ArrayList<Fragment>) : FragmentPagerAdapter(fm1) {

    override fun getItem(p0: Int): Fragment {

        return list!!.get(p0)
    }

    override fun getCount(): Int {
        return list!!.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return data!!.get(position).name
    }

}