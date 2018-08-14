package com.yinshan.happycash.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.yinshan.happycash.application.AppApplication;
import com.yinshan.happycash.application.AppContext;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.UUID;

/**
 * Created by huxin on 2018/3/23.
 */

public class MachineUtils {

    /**
     * 获取AndroidID  AppFlyer用的
     * @param context
     * @return
     */
    public static   String getAndroidId(Context context){
        String  id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return  id!=null?id:"null";
    }
    /**
     * 生成一种唯一标识 用于customerID
     * @return
     */
    public static   String getGUID(){
        String  id = UUID.randomUUID().toString();
        return  id!=null?id:"null";
    }
    /**
     * 获取运营商信息
     */
    public static String getSIMOperator(){
        TelephonyManager tm;
        tm = (TelephonyManager) AppContext.getContext().getSystemService(Context.TELEPHONY_SERVICE);
        String simOperator=tm.getSimOperatorName();
        return  "SimOperator:"+simOperator;

    }
    public static String getNetOperator(){
        TelephonyManager tm;
        tm = (TelephonyManager) AppContext.getContext().getSystemService(Context.TELEPHONY_SERVICE);
        String  networkOperator=tm.getNetworkOperator();
        return   "NetworkOperator:"+networkOperator;

    }
    public static String getIPAddress(Context context) {
        NetworkInfo info = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {//当前使用2G/3G/4G网络
                try {
                    //Enumeration<NetworkInterface> en=NetworkInterface.getNetworkInterfaces();
                    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                        NetworkInterface intf = en.nextElement();
                        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                            InetAddress inetAddress = enumIpAddr.nextElement();
                            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                return inetAddress.getHostAddress();
                            }
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                }

            } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {//当前使用无线网络
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());//得到IPV4地址
                return ipAddress;
            }
        } else {
            //当前无网络连接,请在设置中打开网络
        }
        return null;
    }

    /**
     * 将得到的int类型的IP转换为String类型
     *
     * @param ip
     * @return
     */
    public static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
    }

    public static   String getDeviceBrand() {
        return android.os.Build.BRAND;
    }
}
