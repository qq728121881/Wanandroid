package com.zzn.wanandroid.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.Initializable
import com.zzn.wanandroid.R
import com.zzn.wanandroid.RetrofitConfig.ServiceFactory
import com.zzn.wanandroid.ServiceAPI.ConfigURL
import com.zzn.wanandroid.bean.DataX
import com.zzn.wanandroid.bean.mineLikeDataX
import com.zzn.wanandroid.ui.home.HomeWebView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


/**
 *    author : 郑振楠
 *    date   : 2019/6/4
 */
class MinLikeAdapter(var data: MutableList<mineLikeDataX>, var context: Context) : RecyclerView.Adapter<MinLikeAdapter.MyHolde>() {
    lateinit var uri: String

    fun addDatas(datas: List<mineLikeDataX>) {
        this.data.addAll(datas)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MinLikeAdapter.MyHolde {

        val inflate = LayoutInflater.from(context).inflate(com.zzn.wanandroid.R.layout.home_item, parent, false)
        var holder = MinLikeAdapter.MyHolde(inflate)
        return holder

    }

    override fun onBindViewHolder(holder: MyHolde, position: Int) {
        if (holder != null) {
            holder.name.text = data.get(position).author

            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val time = sdf.format(data.get(position).publishTime)

            holder.time.text = time.substring(0, 10)
            holder.title.text = data.get(position).title
            holder.like_rl.visibility = View.GONE


            holder.ll.setOnClickListener {


                val intent = Intent(Intent(context, HomeWebView::class.java))
                intent.putExtra("title", data.get(position).title)
                intent.putExtra("link", data.get(position).link)
                context!!.startActivity(intent)

            }



            Glide.with(this!!.context!!).load(data.get(position).envelopePic).into(holder.image)
        }
    }


    override fun getItemCount(): Int {
        return data.size

    }


    class MyHolde(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(com.zzn.wanandroid.R.id.name)
        var time: TextView = itemView.findViewById(com.zzn.wanandroid.R.id.time)
        var title: TextView = itemView.findViewById(com.zzn.wanandroid.R.id.title)
        var type_tv: TextView = itemView.findViewById(com.zzn.wanandroid.R.id.type_tv)
        var ll: LinearLayout = itemView.findViewById(com.zzn.wanandroid.R.id.ll)
        var image: ImageView = itemView.findViewById(com.zzn.wanandroid.R.id.image)
        var like: ImageView = itemView.findViewById(com.zzn.wanandroid.R.id.like_icon)
        var like_rl: RelativeLayout = itemView.findViewById(com.zzn.wanandroid.R.id.like_rl)
    }


}


