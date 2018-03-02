package com.yinshan.happycash.application;

import android.support.multidex.MultiDexApplication;
import android.util.Log;

/**
 * Created by huxin on 2018/3/2.
 */

public class AppApplication extends MultiDexApplication{

    private static final String TAG = AppApplication.class.getSimpleName();

    //单例模式instance
    private static AppApplication instance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    private void init() {

        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);

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
