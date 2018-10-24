package com.yinshan.happycash.utils;

import android.util.Log;

/**
 * Created by huxin on 2018/7/18.
 */

public class MyDebugUtils {

    private static boolean IS_DEBUG = true;
    private static String TAG = "app_debug";

    public static void v(String str){
        if(IS_DEBUG)
            Log.v(TAG,"["+str+"]");
    }
}
