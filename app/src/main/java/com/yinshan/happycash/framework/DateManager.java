package com.yinshan.happycash.framework;


import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.yinshan.happycash.application.AppApplication;
import com.yinshan.happycash.utils.AppLoanStatus;
import com.yinshan.happycash.utils.SPKeyUtils;
import com.yinshan.happycash.utils.SPUtils;
import com.yinshan.happycash.view.main.model.LastLoanAppBean;

/**
 * Created by admin on 2018/3/26.
 */

public class DateManager {

    public static String getMobile(){
        return SPUtils.get(SPKeyUtils.MOBILE,"");
    }

    public static void setMobile(String mobile){
        SPUtils.put(SPKeyUtils.MOBILE,mobile);
    }

    public static void setServerStatus(String status){
        SPUtils.get(SPKeyUtils.SERVER_STATUES,status);
    }
    public  static String  getStatus(){
        if(TextUtils.isEmpty(SPUtils.get(SPKeyUtils.SERVER_STATUES,""))){
            return AppLoanStatus.UNLOAN;
        }else {
            return SPUtils.get(SPKeyUtils.SERVER_STATUES,"");
        }
    }
    public static void setAPPStatus(String status){
        SPUtils.get(SPKeyUtils.APP_STATUES,status);
    }
    public  static String  getAPPStatus(){
        if(TextUtils.isEmpty(SPUtils.get(SPKeyUtils.APP_STATUES,""))){
            return AppLoanStatus.UNLOAN;
        }else {
            return SPUtils.get(SPKeyUtils.APP_STATUES,"");
        }
    }

    public static void putToCache(String key, Object mLatestLoanAppBean) {
        SPUtils.put(AppApplication.appContext,key,mLatestLoanAppBean);
    }

    public static Object getMessage(String key) {
        return SPUtils.get(AppApplication.appContext,key,null);

    }
    public static Object getMessage(Context context,String key) {
        return SPUtils.get(context,key,null);

    }
}
