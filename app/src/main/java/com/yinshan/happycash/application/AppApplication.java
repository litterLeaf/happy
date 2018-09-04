package com.yinshan.happycash.application;

import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.util.DisplayMetrics;
import android.util.Log;

import com.appsflyer.AppsFlyerLib;
import com.yinshan.happycash.config.AppEnvConfig;
import com.yinshan.happycash.config.AppNetConfig;
import com.yinshan.happycash.config.AppSdkConfig;
import com.yinshan.happycash.widget.logger.LogUtil;

import cn.fraudmetrix.octopus.aspirit.main.OctopusManager;

/**
 * Created by huxin on 2018/3/2.
 */

public class AppApplication extends MultiDexApplication{

    private static final String TAG = AppApplication.class.getSimpleName();

    //单例模式instance
    public static AppApplication instance = null;
    public static Context appContext = null;


    public static int mScreenWidth = 0;
    public static int mScreenHeight = 0;

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

        DisplayMetrics mDisplayMetrics = getApplicationContext().getResources()
                .getDisplayMetrics();
        AppApplication.mScreenWidth = mDisplayMetrics.widthPixels;
        AppApplication.mScreenHeight = mDisplayMetrics.heightPixels;
//        SyncAccount.addAccount(this);
        //内存泄漏检测
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//
//            return;
//        }

//        LeakCanary.install(this);

        //AF初始化
        AppsFlyerLib.getInstance().startTracking(AppApplication.instance, "ynLJnCRQMzbG8ncHcBWnwh");

        OctopusManager.getInstance().init(this,"rupiahp_hw_mohe","89e4461bb0034c53945afaf617b3624f");
    }

    public static AppApplication getInstance() {
        if (instance == null) {
            Log.d(TAG, "instance is null");
        }
        return instance;
    }
}
