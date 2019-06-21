package com.zzn.wanandroid.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

public class ProgressUtil {
    private static ProgressDialog mDialog;

    private static void createLoadingDialog(Context context) {
        hide();

        mDialog = new ProgressDialog(context);
    }

    /**
     * 显示进度dialog
     *
     * @param context 上下文对象
     * @param
     */
    public static void show(Activity context) {
        if (context == null || context.isFinishing()) return;

        createLoadingDialog(context);
        mDialog.setMessage("正在处理,请稍等...");
        mDialog.show();

    }

    /**
     * 显示进度dialog
     *
     * @param context 上下文对象
     * @param message 显示的message
     */
    public static void show(Activity context, String message) {
        createLoadingDialog(context);
        mDialog.setMessage(message);
        mDialog.show();
    }

    public static void hide() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    //点击空白不让弹框消失
    public static void isShowing() {
        mDialog.setCanceledOnTouchOutside(false);
    }
}
