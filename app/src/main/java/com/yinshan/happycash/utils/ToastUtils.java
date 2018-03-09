package com.yinshan.happycash.utils;

import android.content.Context;
import android.widget.Toast;

import com.yinshan.happycash.application.AppApplication;

/**
 * Created by admin on 2018/1/30.
 */

public class ToastUtils {

    private static Context mContext;
    private static Toast toast;

    /**
     * 短时间显示Toast
     */
    public static void showShort(CharSequence message) {
        if (mContext==null){
            mContext= AppApplication.appContext;
        }
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
        toast.setText(message);
        toast.show();
    }

    public static void showShort(int resId) {
        if (mContext==null){
            mContext= AppApplication.appContext;
        }
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(mContext, mContext.getString(resId), Toast.LENGTH_SHORT);
        toast.setText(mContext.getString(resId));
        toast.show();
    }

}



