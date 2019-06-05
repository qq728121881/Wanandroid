package com.ubisys.uav.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ubisys.uav.R;

/**
 * Created by 郑振楠 on 2017/6/13.
 */
public class WorkAdapterTop extends BaseAdapter {

    private int[] images;
    private String[] text;
    private Context mContext;

    public WorkAdapterTop(String[] text, int[] images, Context mContext) {
        this.text = text;
        this.images = images;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {

        return images[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHodle hodle = null;
        if (view == null) {
            hodle = new ViewHodle();

            view = LayoutInflater.from(mContext).inflate(
                    R.layout.work_item, null);

            hodle.imag = (ImageView) view.findViewById(R.id.iv_gridView_item);
            hodle.tv_con = (TextView) view.findViewById(R.id.tv_gridView_item);

            view.setTag(hodle);
        } else {
            hodle = (ViewHodle) view.getTag();
        }

        hodle.imag.setImageResource(images[position]);
        hodle.tv_con.setText(text[position]);

        return view;
    }


    class ViewHodle {
        private ImageView imag;
        private TextView tv_con;

    }
}
