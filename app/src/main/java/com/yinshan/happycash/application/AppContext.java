package com.yinshan.happycash.application;

import android.content.Context;

import com.yinshan.happycash.config.AppEnvConfig;

/**
 * Created by huxin on 2018/3/2.
 */

public class AppContext {

    public static Context instance;
    private static AppEnvConfig appEnvConfig;

    public static void init(Context context){
        if(null == instance){
            instance = context;
            AppManager.getInstance().init();
        }
    }

    public static void setAppEnvConfig(AppEnvConfig envConfig){
        appEnvConfig = envConfig;
    }

    public static void init(Context context, AppEnvConfig envConfig) {
        if(null == instance){
            instance = context;
            appEnvConfig = envConfig;
            AppManager.getInstance().init();
        }
    }

    public static Context getContext() {
        return instance;
    }

    public static AppEnvConfig getAppEnv() {
        return appEnvConfig;
    }
}
