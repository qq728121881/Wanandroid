package com.ubisys.uav.application;

import android.app.Application;
import android.content.Context;


/**
 * Created by itrax on 2017/6/12.
 */
public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

    }

    public static Context getContext() {

        return context;
    }
}
