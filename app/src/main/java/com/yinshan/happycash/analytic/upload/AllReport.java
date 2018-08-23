package com.yinshan.happycash.analytic.upload;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;


import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.yinshan.happycash.analytic.callLog.CallLogDBController;
import com.yinshan.happycash.analytic.contacts.ContactDBController;
import com.yinshan.happycash.analytic.sms.SmsDBController;
import com.yinshan.happycash.application.AppApplication;
import com.yinshan.happycash.dao.CallLog;
import com.yinshan.happycash.dao.Contact;
import com.yinshan.happycash.dao.Message;
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
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 *        ┃　　　┃   兽神保佑
 *        ┃　　　┃   代码无BUG！
 *        ┃　　　┗━━━┓
 *        ┃　　　　　　　┣┓
 *        ┃　　　　　　　┏┛
 *        ┗┓┓┏━┳┓┏┛
 *           ┃┫┫　┃┫┫
 *           ┗┻┛　┗┻┛
 *
 *    描述：
 *    创建人：     admin
 *    创建时间：2018/8/22
 *
 */

public class AllReport  {

    private CallLogDBController callLogDBController;
    private ContactDBController contactDBController;
    private SmsDBController smsDBController;
    private static AllReport mInstance;
    private List<Contact> contacts= new ArrayList<>();
    private List<CallLog> callLogs= new ArrayList<>();
    private List<Message> messages= new ArrayList<>();

    public AllReport() {

    }

    public static AllReport getInstance() {
        if (mInstance == null) {
            synchronized (AllReport.class) {
                if (mInstance == null) {
                    mInstance = new AllReport();
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
    private File writeCallLogFile(int count, List<CallLog> data, String name) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
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
            for(CallLog callLog: data){
                String[] strs = {callLog.getId().toString() ,toCSVString(callLog.getName()) ,toCSVString(callLog.getNumber()),toCSVString(callLog.getType()),
                        toCSVString(callLog.getDate()),toCSVString(callLog.getDuration()), toCSVString(callLog.getState()), toCSVString(callLog.getFlag())};
                csvWriter.writeNext(strs);
            }
            csvWriter.close();
            fileWriter.close();
            return myTxt;
        }
        return null;
    }
    /**
     * 写Contact文件
     * @param count
     * @param data
     * @param name
     * @return
     * @throws IOException
     */
    private File writeContactFile(int count, List<Contact> data, String name) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
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

            for(Contact contact: data){
                String[] strs = {contact.getId().toString() , contact.getContactId().toString() ,toCSVString(contact.getName()),toCSVString(contact.getPhone()),
                        toCSVString(contact.getEmail()),toCSVString(contact.getUpdateTime()),toCSVString(contact.getAccountType()), toCSVString(contact.getState()),
                        toCSVString(contact.getFlag())};
                csvWriter.writeNext(strs);
            }
            csvWriter.close();
            fileWriter.close();
            return myTxt;
        }
        return null;
    }

    /**
     * 写SMS文件
     * @param count
     * @param data
     * @param name
     * @return
     * @throws IOException
     */
    private File writeSMSFile(int count, List<Message> data, String name) throws IOException {
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
            for(Message message: data){
                String[] strs = {message.getId().toString() , message.getMessageId().toString() ,message.getType().toString(),message.getThreadId().toString(),
                        toCSVString(message.getAddress()),toCSVString(message.getDate()),toCSVString(message.getDate_sent()),toCSVString(message.getSubject()),
                        toCSVString(message.getBody()),toCSVString(message.getState()),toCSVString(message.getFlag())};
                csvWriter.writeNext(strs);
            }
            csvWriter.close();
            fileWriter.close();
            return myTxt;
        }
        return null;
    }

    private String toCSVString(Object input) {
        if (input == null) {
            return "";
        } else {
            return String.valueOf(input);
        }
    }
    /**
     * 上传文件前的准备工作
     * @return
     */
    public String uploadCallLog(){
        File callLog =new File(Environment.getExternalStorageDirectory() + Util.cacheDir+Util.callLogFileName);
        if(callLog!=null){
            String result=fileToBase64(callLog);
            return result;
        }
        return "bad CallLog file";
    }

    public String uploadSMS(){
        File message =new File(Environment.getExternalStorageDirectory() + Util.cacheDir+Util.smsFileName);
        if(message!=null){
            String result=fileToBase64(message);
            return result;
        }
        return "bad SMS file";
    }

    public String uploadContact(){
        File contact =new File(Environment.getExternalStorageDirectory() + Util.cacheDir+Util.contactFileName);
        if(contact!=null){
            String result=fileToBase64(contact);
            return result;
        }
        return "bad Contact file";
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

    /**
     * 写入文件并压缩
     * @return
     */
    public  void gainCallLog(){
        Observable.create(new Observable.OnSubscribe<File>() {
            @Override
            public void call(Subscriber<? super File> subscriber) {
                callLogs.clear();
                callLogDBController= CallLogDBController.getInstance();
                //   int callLogCount=callLogDBController.getCallLogCount();
                callLogs = callLogDBController.getUploadFlagCallLog();
                int callLogCount=callLogs.size();
                if(callLogCount>= 0){
                    try {
                        File callLog=  writeCallLogFile(callLogCount,callLogs,"CallLog");
                        subscriber.onNext(callLog);
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
                        Log.e("AllReport","gainCallLog----"+e.getMessage());
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

    public void gainSMS(){
        Observable.create(new Observable.OnSubscribe<File>() {
            @Override
            public void call(Subscriber<? super File> subscriber) {
                messages.clear();
                smsDBController= smsDBController.getInstance();
                // int smsCount=smsDBController.getMessageCount();
                messages = smsDBController.getUploadFlagMessages();
                int smsCount=messages.size();

                if(smsCount>=0){
                    try {
                        File sms=  writeSMSFile(smsCount,messages,"SMS");
                        subscriber.onNext(sms);
                    } catch (IOException e) {
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
                        Log.e("AllReport","gainSMS----"+e.getMessage());
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

    public void gainContact(){
        Observable.create(new Observable.OnSubscribe<File>() {
            @Override
            public void call(Subscriber<? super File> subscriber) {
                contacts.clear();
                contactDBController= ContactDBController.getInstance();
                //int contactsCount=contactDBController.getContactsCount();
                contacts = contactDBController.getUploadFlagContacts();
                int contactsCount=contacts.size();
                if(contactsCount>=0){
                    try {
                        File contact=  writeContactFile(contactsCount,contacts,"Contact");
                        subscriber.onNext(contact);
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
                        Log.e("AllReport","gainContact----"+e.getMessage());
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
}
//    public AllReport updateContactsFlag() {
//        ArrayList<Long> contactKeys = new ArrayList<>();
//        for(Contact con : contacts){
//            contactKeys.add(con.getId()) ;
//        }
//        ContactDBController.getInstance().updateContactFlagByKey();
//        return this;
//    }
//    public AllReport updateCallLogFlag() {
//        ArrayList<Long> callLogKeys = new ArrayList<>();
//        for(CallLog c : callLogs){
//            callLogKeys.add(c.getId()) ;
//        }
//        CallLogDBController.getInstance().updateCallLogFlagByKey();
//        return this;
//    }
//    public AllReport updateSMSFlag() {
//        ArrayList<Long> smsKeys = new ArrayList<>();
//        for(Message m : messages){
//            smsKeys.add(m.getId()) ;
//        }
//       SmsDBController.getInstance().updateSmsFlagByKey();
//        return this;
//    }
//
//    public void gzipCallLog() throws Exception {
//        if(getCallLogFile()!=null&&getContactFile()!=null&&getSMSFile()!=null){
//            GZipUtils.compress(getCallLogFile());
//            GZipUtils.compress(getContactFile());
//            GZipUtils.compress(getSMSFile());
//        }
//    }
//
//    private File getSMSFile() throws IOException {
//        smsDBController= smsDBController.getInstance();
//        int smsCount=smsDBController.getMessageCount();
//        List<Message> smsMessage = smsDBController.getDBMessages();
//
//        if(smsCount>0){
//            File sms=  writeSMSFile(smsCount,smsMessage,"SMS");
//            return sms;
//        }
//        return null;
//    }
//    private File getContactFile() throws IOException {
//        contactDBController= ContactDBController.getInstance();
//        int cantactCount=contactDBController.getContactsCount();
//        List<Contact> callLogs = contactDBController.getDBContract();
//
//        if(cantactCount>0){
//            File contact =  writeContactFile(cantactCount,callLogs,"Contact");
//            return contact;
//        }
//        return null;
//    }

//    private File getCallLogFile() throws IOException {
//        callLogDBController= CallLogDBController.getInstance();
//        int callLogCount=callLogDBController.getCallLogCount();
//        List<CallLog> callLogs = callLogDBController.getDBCallLog();
//
//        if(callLogCount>0){
//            File callLog=  writeCallLogFile(callLogCount,callLogs,"CallLog");
//            return callLog;
//        }
//        return null;
//    }

