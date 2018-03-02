package com.yinshan.happycash.utils;

import com.orhanobut.logger.Logger;

/**
 * Created by huxin on 2018/3/2.
 */

public class LoggerWrapper {
    private static final boolean DOLOG = false;
    public static void d(String message,Object ...obj){
        if (DOLOG) {
            Logger.d(message,obj);
        }
    }

    public static void d(Object o){
        if (DOLOG) {
            Logger.d(o);
        }
    }

    public static void e(String message,Object ... objs){
        if (DOLOG) {
            Logger.e(message,objs);
        }
    }
    public static void e(Throwable e,String message,Object ... objs){
        if (DOLOG) {
            Logger.e(e,message,objs);
        }
    }

    public static void w(String message,Object...objs){
        if (DOLOG) {
            Logger.w(message,objs);
        }
    }
}
