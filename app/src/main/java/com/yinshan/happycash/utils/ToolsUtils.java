package com.yinshan.happycash.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.view.WindowManager;

import com.yinshan.happycash.application.AppApplication;
import com.yinshan.happycash.application.AppContext;
import com.yinshan.happycash.config.inner.AppInnerConfig;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by huxin on 2018/3/29.
 */

public class ToolsUtils {

    /**
     * 判断印尼手机号的
     * @param wording
     * @return
     */
    public static  boolean checkInputWording(String wording){
        if (TextUtils.isEmpty(wording)) {
            return false;
        }
        if(wording.equals("0"))
            return true;
        String rule= "^(8|08)\\d{0,12}$";
        if(wording.matches(rule)){
            return true;
        }else{
            return false;
        }
    }
    public static String ignorePhone(String pNumber){
        if(!TextUtils.isEmpty(pNumber) && pNumber.length() > 6 ){
            StringBuilder sb  =new StringBuilder();
            for (int i = 0; i < pNumber.length(); i++) {
                char c = pNumber.charAt(i);
                if (i >= 3 && i <= pNumber.length()-4) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }else {
            return pNumber;
        }
    }

    public static String getRollover7Day(){
        SimpleDateFormat formatter   =   new   SimpleDateFormat   ("dd-MM-yyyy");
        long date= System.currentTimeMillis()+604800000;
        Date curDate =  new Date(date);
        String   str   =   formatter.format(curDate);
        return  str;
    }
    public static String getRollover14Day(){
        SimpleDateFormat formatter   =   new   SimpleDateFormat   ("dd-MM-yyyy");
        long date= System.currentTimeMillis()+1209600000;
        Date curDate =  new Date(date);
        String   str   =   formatter.format(curDate);
        return  str;
    }
    /**
     * 启动到应用商店app详情界面
     *
     * @param marketPkg 应用商店包名 ,如果为""则由系统弹出应用商店列表供用户选择,否则调转到目标市场的应用详情界面，某些应用商店可能会失败
     */
    public static void launchAppDetail(Context context, String marketPkg) {
        try {
            //应用包名
            String appPkg = AppContext.getContext().getPackageManager().getPackageInfo(AppContext.getContext().getPackageName(), 0).packageName;
            if (TextUtils.isEmpty(appPkg))
                return; Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(marketPkg)) {
                intent.setPackage(marketPkg);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (android.content.ActivityNotFoundException anfe) {
            anfe.printStackTrace();
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + AppContext.getContext().getPackageName())));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * String  pageName= "https://app.appsflyer.com/com.yinshan.program.martket?pid=rupiahplus";
     * @param context
     */
    public static void openURL(Context context){
        String  url="https://app.appsflyer.com/com.yinshan.program.martket?pid=rupiahplus";
        Uri content_url = Uri.parse(url);
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(content_url);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 直接跳Google play
     * @param context
     *
     */
    public static void launchAppDetail(Context context) {
        String pageName= AppInnerConfig.PACKAGE_NAME;
        String market = "com.android.vending";
        try {
            //应用包名
            if (TextUtils.isEmpty(pageName))
                return; Uri uri = Uri.parse("market://details?id=" + pageName);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(market)) {
                intent.setPackage(market);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (android.content.ActivityNotFoundException anfe) {
            anfe.printStackTrace();
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + pageName)));
        }
    }
    public static String getMonth(int month){
        String monthString = "";
        switch (month){
            case 1:
                monthString = "January";
                break;
            case 2:
                monthString =    "February";
                break;
            case 3:
                monthString =     "March";
                break;
            case 4:
                monthString =   "April";
                break;
            case 5:
                monthString =   "May";
                break;
            case 6:
                monthString =    "June";
                break;
            case 7:
                monthString =   "July";
                break;
            case 8:
                monthString =    "August";
                break;
            case 9:
                monthString =     "September";
                break;
            case 10:
                monthString =   "October";
                break;
            case 11:
                monthString =     "November";
                break;
            case 12:
                monthString =     "December";
                break;
        }
        return  monthString;
    }
    /**
     * 获取屏幕分辨率
     * @param context
     * @return
     */
    public static int[] getScreenDispaly(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = windowManager.getDefaultDisplay().getWidth();// 手机屏幕的宽度
        int height = windowManager.getDefaultDisplay().getHeight();// 手机屏幕的高度
        int result[] = { width, height };
        return result;
    }
}
