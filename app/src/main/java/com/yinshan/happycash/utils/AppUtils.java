package com.yinshan.happycash.utils;

import android.content.Context;
import android.os.Environment;
import android.support.multidex.MultiDexApplication;

import java.io.File;

/**
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 *        ┃　　　┃   神兽保佑
 *        ┃　　　┃   代码无BUG！
 *        ┃　　　┗━━━┓
 *        ┃　　　　　　　┣┓
 *        ┃　　　　　　　┏┛
 *        ┗┓┓┏━┳┓┏┛
 *           ┃┫┫　┃┫┫
 *           ┗┻┛　┗┻┛
 *
 *  @author  admin
 *  on 2018/1/11
 *
 */

public class AppUtils extends MultiDexApplication {

    /**
     * 全局Application实例，等同于在任意Activity调用getApplicationContext()
     */
    private static Context mAppContext;


    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = getApplicationContext();
    }

    /*
     * 通过静态调用得到Application对象
     */
    public static Context getAppContext() {
        return mAppContext;
    }

    /*
      *退出登录删除sp 的缓存信息
      */
    public static void exitLogin() {
    }

}
