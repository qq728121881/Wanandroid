package com.zzn.wanandroid.adapter

import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.zzn.wanandroid.bean.DataX
import java.text.SimpleDateFormat


/**
 *    author : 郑振楠
 *    date   : 2019/6/4
 */
class HomeAdapter(var data: MutableList<DataX>, var context: FragmentActivity?) : RecyclerView.Adapter<HomeAdapter.MyHolde>() {


    fun addDatas(datas: List<DataX>) {
        this.data.addAll(datas)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyHolde {

        val inflate = LayoutInflater.from(context).inflate(com.zzn.wanandroid.R.layout.home_item, parent, false)
        var holder = HomeAdapter.MyHolde(inflate)
        return holder

    }

    override fun onBindViewHolder(holder: MyHolde, position: Int) {
        if (holder != null) {
            holder.name.text = data.get(position).author

            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val time = sdf.format(data.get(position).publishTime)

            holder.time.text = time.substring(0, 10)
            holder.title.text = data.get(position).title
            holder.type_tv.text = data.get(position).superChapterName
            holder.ll.setOnClickListener {

                Toast.makeText(context, "$position", Toast.LENGTH_LONG).show()

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
    }


}


