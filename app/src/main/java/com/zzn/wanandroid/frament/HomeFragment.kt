package com.zzn.wanandroid.frament

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.zzn.wanandroid.R

import butterknife.ButterKnife


class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
    }


}
