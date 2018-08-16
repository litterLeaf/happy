package com.yinshan.happycash.analytic.location;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by yyhuang on 2017/6/16  15:17.
 */

public class LocationTracker {

    public static final int GPS_TYPE = 20000;

    Context mContext;
    private LocationContract mContract;
    private LocationClient mLocationClient = null;
    private BDLocationListener mListener = null;
    private LocationManager mSystemLocationManager = null;
    int duration;

    boolean isGPSListener = false;

    public LocationTracker(Context context, final LocationContract contract, int locationCollectDuration) {
        mContext = context;
        mContract = contract;
        duration = locationCollectDuration;
        //声明百度LocationClient类
        mLocationClient = new LocationClient(context.getApplicationContext());
        //系统location服务
        mSystemLocationManager = (LocationManager) context.getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        //注册监听函数
        mListener = new MyLisenter();
        mLocationClient.registerLocationListener(mListener);

        initBaiduLocation();
        initSystemLocation();
    }

    private void initBaiduLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("WGS84");
//        1. WGS84：为一种大地坐标系，也是目前广泛使用的GPS全球卫星定位系统使用的坐标系；
//        2. GCJ02：表示经过国测局加密的坐标；
//        3. BD09：为百度坐标系，其中bd09ll表示百度经纬度坐标，bd09mc表示百度墨卡托米制坐标；
        //可选，默认gcj02，设置返回的定位结果坐标系

        int span = 1000;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

        option.setIsNeedAddress(false);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        option.setLocationNotify(false);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        option.setIsNeedLocationDescribe(false);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setIsNeedLocationPoiList(false);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集

        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

        mLocationClient.setLocOption(option);
    }

    @SuppressLint("MissingPermission")
    private void initSystemLocation() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(mContext.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }else{
                openGPSListener();
            }
        } else {
            openGPSListener();
        }
    }

    @SuppressLint("MissingPermission")
    private void openGPSListener() {
        Location lastKnownLocation = mSystemLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(lastKnownLocation!=null){
            com.yinshan.happycash.dao.Location nLocationFromSystem = getNLocationFromSystem(lastKnownLocation);
            if(nLocationFromSystem.getLongitude()==0.0){
                startBaiduLocationUpdate();
            }else{
                mContract.onReceived(nLocationFromSystem);
            }
        }

        if(isGPSListener)
            return;
        isGPSListener = true;
        mSystemLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, duration, 10, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                com.yinshan.happycash.dao.Location nLocationFromSystem = getNLocationFromSystem(location);
                //startBaiduLocationUpdate();
                mContract.onReceived(nLocationFromSystem);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
                int h=3;
            }

            @Override
            public void onProviderEnabled(String s) {
                startBaiduLocationUpdate();
            }

            @Override
            public void onProviderDisabled(String s) {
                startBaiduLocationUpdate();
            }
        });
    }

    private com.yinshan.happycash.dao.Location getNLocationFromSystem(Location location) {
        com.yinshan.happycash.dao.Location lLocation = new com.yinshan.happycash.dao.Location();
        lLocation.setLocType(GPS_TYPE);
        lLocation.setLongitude(location.getLongitude());
        lLocation.setLatitude(location.getLatitude());
        lLocation.setAltitude(location.getAltitude());
        lLocation.setDateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(location.getTime())));
        return lLocation;
    }

    public void startBaiduLocationUpdate(){
        if (mLocationClient != null) {
            mLocationClient.start();
        }
    }

    public void start() {
        initSystemLocation();
    }

    public void stop() {
        if (mLocationClient != null) {
            mLocationClient.stop();
        }
    }

    public void destroy() {
        if (mLocationClient != null && mListener != null) {
            mLocationClient.unRegisterLocationListener(mListener);
            mListener = null;
            mLocationClient = null;
        }
    }

    private class MyLisenter implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            stop();

            //获取定位结果
            StringBuffer sb = new StringBuffer(256);

            sb.append("time : ");
            sb.append(location.getTime());    //获取定位时间

            sb.append("\nerror code : ");
            sb.append(location.getLocType());    //获取类型类型

            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());    //获取纬度信息

            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());    //获取经度信息

            sb.append("\nradius : ");
            sb.append(location.getRadius());    //获取定位精准度

            if (location.getLocType() == BDLocation.TypeGpsLocation){

                // GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());    // 单位：公里每小时

                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());    //获取卫星数

                sb.append("\nheight : ");
                sb.append(location.getAltitude());    //获取海拔高度信息，单位米

                sb.append("\ndirection : ");
                sb.append(location.getDirection());    //获取方向信息，单位度

                sb.append("\naddr : ");
                sb.append(location.getAddrStr());    //获取地址信息

                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation){

                // 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());    //获取地址信息

                sb.append("\noperationers : ");
                sb.append(location.getOperators());    //获取运营商信息

                sb.append("\ndescribe : ");
                sb.append("网络定位成功");

            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {

                // 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");

            } else if (location.getLocType() == BDLocation.TypeServerError) {

                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");

            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {

                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");

            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {

                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");

            }

            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());    //位置语义化信息

            List<Poi> list = location.getPoiList();    // POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }

            com.yinshan.happycash.dao.Location lLocation = new com.yinshan.happycash.dao.Location();
            lLocation.setLocType(location.getLocType());
            lLocation.setLongitude(location.getLongitude());
            lLocation.setLatitude(location.getLatitude());
            lLocation.setAltitude(location.getAltitude());
            lLocation.setDateTime(location.getTime());

            mContract.onReceived(lLocation);
        }

//        @Override
//        public void onConnectHotSpotMessage(String s, int i) {
//
//        }
    }
}
