package com.zzn.wanandroid.utils;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by 郑振楠 on 2018/10/29.
 */

public class BanViewPager extends ViewPager {

    //设置pager是否可以滑动，false为可以滑动
    private boolean isCanScroll = false;

    public BanViewPager(Context context) {

        super(context);

    }

    public BanViewPager(Context context, AttributeSet attrs) {

        super(context, attrs);

    }

    public void setNoScroll(boolean noScroll) {

        this.isCanScroll = noScroll;

    }

    @Override

    public void scrollTo(int x, int y) {

        super.scrollTo(x, y);

    }

    @Override

    public boolean onTouchEvent(MotionEvent arg0) {

        if (isCanScroll){

            return false;

        }else{

            return super.onTouchEvent(arg0);

        }

    }

    @Override

    public boolean onInterceptTouchEvent(MotionEvent arg0) {

        if (isCanScroll){

            return false;

        }else{

            return super.onInterceptTouchEvent(arg0);

        }

    }
}
