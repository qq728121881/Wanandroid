package com.zzn.wanandroid.adapter

import android.content.Intent
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.zzn.wanandroid.R
import com.zzn.wanandroid.RetrofitConfig.ServiceFactory
import com.zzn.wanandroid.ServiceAPI.ConfigURL
import com.zzn.wanandroid.bean.DataX
import com.zzn.wanandroid.bean.DataXcontext
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
class ProjectContextAdapter(var data: MutableList<DataXcontext>, var context: FragmentActivity?) : RecyclerView.Adapter<ProjectContextAdapter.MyHolde>() {
    lateinit var uri: String


    fun addDatas(datas: List<DataXcontext>) {
        this.data.addAll(datas)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyHolde {

        val inflate = LayoutInflater.from(context).inflate(com.zzn.wanandroid.R.layout.home_item, parent, false)
        var holder = ProjectContextAdapter.MyHolde(inflate)
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



            if (data.get(position).collect) {
                Glide.with(this!!.context!!).load(R.mipmap.like_true).into(holder.like)
            } else {
                Glide.with(this!!.context!!).load(R.mipmap.like_false).into(holder.like)
            }

            holder.ll.setOnClickListener {

                Toast.makeText(context, "$position", Toast.LENGTH_LONG).show()
                val intent = Intent(Intent(context, HomeWebView::class.java))
                intent.putExtra("title", data.get(position).title)
                intent.putExtra("link", data.get(position).link)
                context!!.startActivity(intent)

            }


            holder.like_rl.setOnClickListener {

                if (data.get(position).collect) {
                    data.get(position).collect = false
                    uri = ConfigURL.Object.API_URL + "/lg/uncollect_originId/%d/json"
                } else {
                    data.get(position).collect = true
                    uri = ConfigURL.Object.API_URL + "/lg/collect/%d/json"
                }

                val url = String.format(Locale.getDefault(), uri, data.get(position).id)

                ServiceFactory.getBusinessApi()!!.getLike(url).enqueue(object : Callback<String> {
                    override fun onFailure(call: Call<String>?, t: Throwable?) {
                        val message = t!!.message


                    }

                    override fun onResponse(call: Call<String>?, response: Response<String>?) {
                        val code = response!!.code()
                        if (code == 200) {
                            if (data.get(position).collect) {
                                Glide.with(context!!).load(R.mipmap.like_true).into(holder.like)
                            }else{
                                Glide.with(context!!).load(R.mipmap.like_false).into(holder.like)
                            }

                        }

                    }

                })


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


