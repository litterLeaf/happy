package com.yinshan.happycash.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.telephony.TelephonyManager;

import com.yinshan.happycash.application.AppApplication;
import com.yinshan.happycash.application.AppContext;

/**
 * Created by huxin on 2018/5/2.
 */

public class SystemUtil {

    private static SystemUtil mInstance = null;

    private PackageManager packageManager;

    public SystemUtil() {
        packageManager = AppContext.instance.getPackageManager();
    }

    public static SystemUtil getInstance() {
        if (mInstance == null) {
            synchronized (SystemUtil.class) {
                if (mInstance == null) {
                    mInstance = new SystemUtil();
                }
            }
        }
        return mInstance;
    }

    public String getApkName() {
        String apkName = "";
        try {
            ApplicationInfo info = packageManager.getApplicationInfo(AppContext.instance.getPackageName(), 0);
            apkName = (String)packageManager.getApplicationLabel(info);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return apkName;
    }

    @SuppressLint("MissingPermission")
    public String getImei() {
        try {
            TelephonyManager tm = (TelephonyManager) AppContext.instance.getSystemService(Context.TELEPHONY_SERVICE);
            return tm.getDeviceId();
        } catch (Exception e) {
            return null;
        }
    }

    public String getVersionCode() {
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(AppContext.instance.getPackageName(), 0);
            return String.valueOf(packageInfo.versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            return "";
        }
    }

    public String getVersionName() {
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(AppContext.instance.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return " ";
        }
    }

    public static final boolean isGpsOpen(final Context context) {

        LocationManager locationManager = (LocationManager) context.getSystemService(
                Context.LOCATION_SERVICE);
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (gps || network) {
            return true;
        }

        return false;
    }

    /**
     * 获取手机厂商
     *
     * @return  手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    public static String getLastBrand(){
        String initDeviceBrand = getDeviceBrand();
        if(initDeviceBrand!=null&&!initDeviceBrand.equals("")){
            if(initDeviceBrand.contains("xiaomi")||initDeviceBrand.contains("Xiaomi"))
                return "xiaomi";
//            else if(initDeviceBrand.contains("huawei")||initDeviceBrand.contains("Huawei"))
//                return "huawei";
            else
                return "others";
        }
        return "others";
    }
}
