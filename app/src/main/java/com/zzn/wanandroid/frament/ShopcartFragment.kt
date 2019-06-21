package com.zzn.wanandroid.frament

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout

import com.zzn.wanandroid.R
import com.zzn.wanandroid.ui.mine.LikeActivity


class ShopcartFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_shop_cat, container, false)

        val like_rl = inflate.findViewById(R.id.like_rl) as RelativeLayout

        like_rl.setOnClickListener {

            val intent = Intent(Intent(context, LikeActivity::class.java))
            startActivity(intent)

        }





        return inflate
    }


}
