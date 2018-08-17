package com.yinshan.happycash.analytic;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.opencsv.CSVWriter;
import com.yinshan.happycash.analytic.location.LocationDBController;
import com.yinshan.happycash.application.AppApplication;
import com.yinshan.happycash.dao.Location;
import com.yinshan.happycash.utils.GZipUtils;
import com.yinshan.happycash.utils.MachineUtils;
import com.yinshan.happycash.utils.SPUtils;
import com.yinshan.happycash.utils.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import static com.yinshan.happycash.utils.MachineUtils.getAppVersionName;

/**
 * imei,androidId,deviceType,deviceBrand,SimOperator,netOperator,electricity,batteryStatus,systemVersion,locatonStatus,locationX,locationY,locationZ
 */
public class DeviceInfoController {

    private static DeviceInfoController mInstance;

    public static DeviceInfoController getInstance() {
        if (mInstance == null) {
            synchronized (DeviceInfoController.class) {
                if (mInstance == null) {
                    mInstance = new DeviceInfoController();
                }
            }
        }
        return mInstance;
    }
    private  DeviceInfoController (){ }

    /**
     * 写入文件并压缩
     * @return
     */
    public  void gainDeviceInfo(){
        Observable.create(new Observable.OnSubscribe<File>() {
            @Override
            public void call(Subscriber<? super File> subscriber) {

                    try {
                        File deviceInfo=  writeDeviceInfoFile();
                        subscriber.onNext(deviceInfo);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }

        }).observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<File>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("DeviceInfoController","deviceInfoControllerLog----"+e.getMessage());
                    }

                    @Override
                    public void onNext(File file) {
                        try {
                            GZipUtils.compress(file);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private File writeDeviceInfoFile() throws IOException {
        File storagePath;
            if (TextUtils.equals(Environment.MEDIA_MOUNTED, Environment.getExternalStorageState())) {
                storagePath = new File(Environment.getExternalStorageDirectory() + Util.cacheDir);
                if (!storagePath.exists()) {
                    storagePath.mkdirs();
                }
            } else {
                storagePath = AppApplication.appContext.getCacheDir();
            }
            File myTxt = new File(storagePath,
                    "DeviceInfo"+ ".csv");
            myTxt.createNewFile();
            FileWriter fileWriter = new FileWriter(myTxt,false);
            CSVWriter csvWriter = new CSVWriter(fileWriter, ',');
            csvWriter.writeNext(getData());
            csvWriter.close();
            fileWriter.close();
            return myTxt;
    }

    private String[]  getData(){
        String [] a ={"Imei : "+ SPUtils.getInstance().getImei(),
                "AndroidId : "+ MachineUtils.getAndroidId(AppApplication.appContext),
                "DeviceType : "+getDeviceType(),
                "DeviceBrand : "+toCSVString(MachineUtils.getDeviceBrand()),
               MachineUtils.getSIMOperator(),
                MachineUtils.getNetOperator(),
                "systemVersion : "+toCSVString(getSystemVersion()),
                "appVersion : "+ toCSVString(getAppVersionName(AppApplication.appContext,0)),
                "appVersionCode : "+ getAppVersionName(AppApplication.appContext,1),
                "location : "+getLocation()};
        return a;
    }

    private  String getDeviceType(){
        String deviceType = android.os.Build.MODEL;
        if(deviceType!=null&&!deviceType.equals("")){
                return android.os.Build.MODEL;
        }
        return "deviceType : null";
    }

    private String toCSVString(Object input) {
        if (input == null) {
            return "";
        } else {
            return String.valueOf(input);
        }
    }

    private String  getLocation(){
        List<Location> list = LocationDBController.getInstance().getList();
            if(list!=null&&list.size()>0){
                return list.get(list.size()-1).toString();
            }else {
                return "";
            }
    }

    private  String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    public String uploadDeviceInfo(){
        File deviceInfo =new File(Environment.getExternalStorageDirectory() + Util.cacheDir+Util.deviceInfoFileName);
        if(deviceInfo!=null){
            return fileToBase64(deviceInfo);
        }
        return "bad DeviceInfo file";
    }
    private  String fileToBase64(File file) {
        String base64 = null;
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            byte[] bytes = new byte[in.available()];
            int length = in.read(bytes);
            base64 = Base64.encodeToString(bytes, 0, length, Base64.DEFAULT);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return base64;
    }
}
