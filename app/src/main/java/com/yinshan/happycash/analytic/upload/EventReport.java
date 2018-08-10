package com.yinshan.happycash.analytic.upload;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.yinshan.happycash.analytic.event.EventDBController;
import com.yinshan.happycash.application.AppApplication;
import com.yinshan.happycash.dao.Event;
import com.yinshan.happycash.utils.GZipUtils;
import com.yinshan.happycash.utils.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by admin on 2017/11/27.
 */

public class EventReport {

    private static EventReport mInstance;
    private List<Event> events= new ArrayList<>();
    private EventDBController eventDBController;

    public EventReport() {

    }
    public static EventReport getInstance() {
    if (mInstance == null) {
        synchronized (EventReport.class) {
            if (mInstance == null) {
                mInstance = new EventReport();
            }
        }
    }
        return mInstance;
    }

    /**
     * 写callLog文件
     * @param count
     * @param data
     * @param name
     * @return
     * @throws IOException
     */
    private File writeEventFile(int count, List<Event> data, String name) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        File storagePath;
        if(count>=0){
            if (TextUtils.equals(Environment.MEDIA_MOUNTED, Environment.getExternalStorageState())) {
                storagePath = new File(Environment.getExternalStorageDirectory() + Util.cacheDir);
                if (!storagePath.exists()) {
                    storagePath.mkdirs();
                }
            } else {
                storagePath = AppApplication.appContext.getCacheDir();
            }
            File myTxt = new File(storagePath,
                    name+ ".csv");

            myTxt.createNewFile();
            FileWriter fileWriter = new FileWriter(myTxt,false);
            CSVWriter csvWriter = new CSVWriter(fileWriter, ',');
//            for(CallLog callLog: data){
//                fileWriter.write(callLog.toString());
//                fileWriter.write("\n");
//                fileWriter.flush();
//            }
            for(Event event: data){
                String[] strs = {event.getId().toString() ,toCSVString(event.getEventId()) ,toCSVString(event.getEventTime())};
                csvWriter.writeNext(strs);
            }
            csvWriter.close();
            fileWriter.close();
            return myTxt;
        }
        return null;
    }


    /**
     * 写入文件并压缩
     * @return
     */
    public  void gainEvent(){
        Observable.create(new Observable.OnSubscribe<File>() {
            @Override
            public void call(Subscriber<? super File> subscriber) {
                events.clear();
                eventDBController= EventDBController.getInstance();
                //   int callLogCount=callLogDBController.getCallLogCount();
                events = eventDBController.getList();
                int callLogCount=events.size();
                if(callLogCount>= 0){
                    try {
                        File eventFile=  writeEventFile(callLogCount,events,"Event");
                        subscriber.onNext(eventFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (CsvRequiredFieldEmptyException e) {
                        e.printStackTrace();
                    } catch (CsvDataTypeMismatchException e) {
                        e.printStackTrace();
                    }
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
                        Log.e("EventReport","gainEvent----"+e.getMessage());
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

    public String uploadEvent(){
        File event =new File(Environment.getExternalStorageDirectory() + Util.cacheDir+Util.eventFileName);
        if(event!=null){
            String result=fileToBase64(event);
            return result;
        }
        return "bad Event file";
    }


    public String fileToBase64(File file) {
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

    public String toCSVString(Object input) {
        if (input == null) {
            return "";
        } else {
            return String.valueOf(input);
        }
    }
}
