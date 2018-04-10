package com.yinshan.happycash.application;

import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.yinshan.happycash.config.AppEnvConfig;
import com.yinshan.happycash.config.AppNetConfig;
import com.yinshan.happycash.config.AppSdkConfig;
import com.yinshan.happycash.widget.logger.LogUtil;

/**
 * Created by huxin on 2018/3/2.
 */

public class AppApplication extends MultiDexApplication{

    private static final String TAG = AppApplication.class.getSimpleName();

    //单例模式instance
    private static AppApplication instance = null;
    public static Context appContext = null;
    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        init();
    }

    private void init() {
        AppContext.init(this);

        //设置访问网络配置
        AppContext.setAppEnvConfig(AppEnvConfig.indexOf(AppNetConfig.RUN_NET_CONFIG));

        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
        appContext = this.getApplicationContext();
        LogUtil.getInstance().init(this);
        //内存泄漏检测
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//
//            return;
//        }

//        LeakCanary.install(this);
    }

    public static AppApplication getInstance() {
        if (instance == null) {
            Log.d(TAG, "instance is null");
        }
        return instance;
    }
}
