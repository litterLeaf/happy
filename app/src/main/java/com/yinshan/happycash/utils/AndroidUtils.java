package com.yinshan.happycash.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.UUID;

/**
 * Created by huxin on 2018/8/9.
 */
public class AndroidUtils {

    /**
     * 获取clientId,思路是先获取imie,因为权限等问题就尝试获取androidId,不行再获取macAddress,再不行获取自定义ID .
     * @param context
     * @param hasReadPhoneStatePermission
     * @return
     */
    public static String getClientId(Context context, boolean hasReadPhoneStatePermission){
        String imie = null;
        if(hasReadPhoneStatePermission){
            imie = getIMIE(context);
        }
        String androidId = null;
        if(imie==null||imie.equals("")){
            androidId = getAndroidId(context);
        }else{
            return imie;
        }
        String macAddress = null;
        if(androidId==null||androidId.equals("")){
            macAddress = getMacAddressOfEthernet();
        }else{
            return androidId;
        }
        String generateSelfClientId = null;
        if(macAddress==null||macAddress.equals("")){
            generateSelfClientId = getSelfClientId();
        }else{
            return macAddress;
        }
        return generateSelfClientId;
    }

    public static String getUUID(){
        return UUID.randomUUID().toString();
    }

    public static String getIMIE(Context context){
        return ((TelephonyManager)context.getSystemService(context.TELEPHONY_SERVICE)).getDeviceId();
    }

    //获取以太网mac地址
    public static String getMacAddressOfEthernet() {
        try {
            String macFileString = "/sys/class/net/eth0/address";
            StringBuffer fileData = new StringBuffer(1000);
            BufferedReader reader = new BufferedReader(new FileReader(
                    macFileString));
            char[] buf = new char[1024];
            int numRead = 0;
            while ((numRead = reader.read(buf)) != -1) {
                String readData = String.valueOf(buf, 0, numRead);
                fileData.append(readData);
            }
            reader.close();
            String macAddress = fileData.toString();
            return macAddress.toUpperCase().substring(0, 17);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getAndroidId(Context context){
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getSelfClientId(){
        return "";
    }

    public static String getChannel(Context context,String key){
        if(context==null|| TextUtils.isEmpty(key)){
            return "";
        }
        String resultData = null;
        try{
            PackageManager packageManager = context.getPackageManager();
            if(packageManager != null){
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(),PackageManager.GET_META_DATA);
                if(applicationInfo!=null){
                    if(applicationInfo.metaData!=null){
                        resultData = applicationInfo.metaData.getString(key);
                    }
                }
            }
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
            return "";
        }
        return resultData;
    }

    /**
     * 得到IP地址
     * @param context
     * @return
     */
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

    /**
     * 获取版本号名称
     *
     * @param context 上下文
     * @return
     */
    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().
                    getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }
}
