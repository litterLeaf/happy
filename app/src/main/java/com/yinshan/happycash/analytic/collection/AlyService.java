package com.yinshan.happycash.analytic.collection;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.yinshan.happycash.analytic.location.LocationContract;
import com.yinshan.happycash.analytic.location.LocationRetryPolicy;
import com.yinshan.happycash.analytic.location.LocationTracker;
import com.yinshan.happycash.dao.Location;

import java.util.stream.Collector;

public class AlyService extends Service implements LocationContract {
    private final static String TAG = "AlyService";
    private final static boolean DEBUG = true;

    private final static int LOCATION_COLLECT_DURATION =  10 * 60 * 1000;
    private final static int LOCATION_RETRY_DURAION = 5 * 1000;             //5S之后重试
    private final static int PHONE_COLLECT_DURATION = 60 * 60 * 1000;

    private LocationTracker mTracker;
    private Collector mCollector;           //位置信息收集器，定义了时间间隔
    private LocationRetryPolicy retryPolicy;
    private Notification notif;




    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onReceived(Location bdLocation) {

    }
}
