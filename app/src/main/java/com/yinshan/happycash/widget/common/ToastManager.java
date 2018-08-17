package com.yinshan.happycash.widget.common;

import android.app.Activity;
import android.widget.Toast;

import com.yinshan.happycash.application.AppContext;

/**
 * Created by huxin on 2018/8/16.
 */
public class ToastManager {

    static Toast toast;

    public static void showToast(String msg){
        if(toast == null){
            toast =  Toast.makeText(AppContext.getContext(), msg,Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();
    }
    public static void showToast(int msg){
        String message = AppContext.getContext().getResources().getString(msg);
        if(toast == null){
            toast =  Toast.makeText(AppContext.getContext(), message,Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();
    }
    public static void showToastOnUIThread(final Activity act, final String msg, final int duration, final int gravity) {
        act.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showToast(msg);
            }
        });
    }
}
