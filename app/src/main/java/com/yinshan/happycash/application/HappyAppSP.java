package com.yinshan.happycash.application;

import android.content.Context;

import com.yinshan.happycash.application.support.BaseSharedPref;

/**
 * Created by huxin on 2018/5/2.
 */

public class HappyAppSP extends BaseSharedPref {
    private static final String SP_APPINFO_FILENAME = "appInformation";
    private static final String IMEI = "imei";

    private static HappyAppSP mInstance;

    public HappyAppSP(Context context, String fileName, int mode) {
        super(context, fileName, mode);
    }

    public static HappyAppSP getInstance(){
        if(mInstance == null){
            synchronized (HappyAppSP.class){
                if(mInstance==null){
                    mInstance = new HappyAppSP(AppContext.instance,SP_APPINFO_FILENAME,Context.MODE_PRIVATE);
                }
            }
        }
        return mInstance;
    }

    public String getImei() {
        return readString(IMEI);
    }

    public void setImei(String imei) {
        write(IMEI, imei);
    }
}
