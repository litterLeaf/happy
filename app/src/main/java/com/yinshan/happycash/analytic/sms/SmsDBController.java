package com.yinshan.happycash.analytic.sms;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.yinshan.happycash.analytic.contacts.ContactContract;
import com.yinshan.happycash.dao.DaoDbHelper;
import com.yinshan.happycash.dao.DaoSession;
import com.yinshan.happycash.dao.Message;
import com.yinshan.happycash.dao.MessageDao;
import com.yinshan.happycash.utils.DateUtil;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.LinkedList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by yyhuang on 2017/6/27  11:10.
 */

public class SmsDBController {

    private Context mContext;
    private static SmsDBController mInstance;
    private MessageDao mMessageDao;

    public SmsDBController() {
        mContext = DaoDbHelper.getInstance().getContext();

        DaoSession daoSession = DaoDbHelper.getInstance().getDaoSession();
        mMessageDao = daoSession.getMessageDao();
    }

    public static SmsDBController getInstance() {
        if (mInstance == null) {
            synchronized (SmsDBController.class) {
                if (mInstance == null) {
                    mInstance = new SmsDBController();
                }
            }
        }
        return mInstance;
    }

    public List<Message> getUploadMessages() {
        QueryBuilder<Message> qb = mMessageDao.queryBuilder();
        qb.where(MessageDao.Properties.State.notEq(ContactContract.STATE_IDLE));
        qb.orderDesc(MessageDao.Properties.Date);
        qb.limit(500);
        return qb.list();
    }

    public void gainMessages() {
        Observable.create(new Observable.OnSubscribe<List<Message>>() {
            @Override
            public void call(Subscriber<? super List<Message>> subscriber) {
                List<Message> messages = getMessages();
                subscriber.onNext(messages);
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<List<Message>>() {
                    @Override
                    public void onCompleted() {
//                        AppReport.sendMessagesDelay();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Message> messages) {
                        updateMessages(messages);
                    }
                });
    }

    private List<Message> getMessages() {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        List<Message> messages = new LinkedList<>();
        ContentResolver resolver = mContext.getContentResolver();
        Cursor cursor = resolver.query(SmsColumns.CONTENT_URI, null, null, null, SmsColumns.DEFAULT_SORT_ORDER);
        try {
            while (cursor.moveToNext()) {
                int messageId = cursor.getInt(cursor.getColumnIndex(SmsColumns._ID));
                int type = cursor.getInt(cursor.getColumnIndex(SmsColumns.TYPE));
                int threadId = cursor.getInt(cursor.getColumnIndex(SmsColumns.THREAD_ID));
                String address = cursor.getString(cursor.getColumnIndex(SmsColumns.ADDRESS));
                Long date = cursor.getLong(cursor.getColumnIndex(SmsColumns.DATE));
                Long date_sent = cursor.getLong(cursor.getColumnIndex(SmsColumns.DATE_SENT));
                String subject = cursor.getString(cursor.getColumnIndex(SmsColumns.SUBJECT));
                String body = cursor.getString(cursor.getColumnIndex(SmsColumns.BODY));

                Message message = new Message();
                message.setMessageId(messageId);
                message.setType(type);
                message.setThreadId(threadId);
                message.setAddress(address);
                message.setDate(DateUtil.now(date));
                message.setDate_sent(DateUtil.now(date_sent));
                message.setSubject(subject);
                message.setBody(body);

                messages.add(message);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return messages;
    }

    private void updateMessages(List<Message> messages) {
        List<Message> addMessages = new LinkedList<>();
        if(messages==null){
            Log.e("MessageDBController","messageList is null");
            return;}
        for (Message message : messages) {
            QueryBuilder<Message> qb = mMessageDao.queryBuilder();
            qb.where(MessageDao.Properties.MessageId.eq(message.getMessageId()));
            Message m = qb.unique();
            if (m == null) {
                message.setFlag(ContactContract.FLAG_NEED_UPLOAD);
                message.setState(ContactContract.STATE_ADD);
                addMessages.add(message);
            }
        }
        if (addMessages.size() > 0) {
            mMessageDao.insertInTx(addMessages);
        }
    }

    public void updateStateByKey(List<Long> keys) {
        QueryBuilder<Message> qb = mMessageDao.queryBuilder();
        List<Message> messages = qb.where(MessageDao.Properties.Id.in(keys)).list();
        if (messages == null || messages.size() == 0) {
            return;
        }
        for (Message message : messages) {
            message.setState(ContactContract.STATE_IDLE);
        }
        mMessageDao.updateInTx(messages);
    }
//
//    public List<Message> getDBMessages() {
//        QueryBuilder<Message> qb = mMessageDao.queryBuilder();
//        qb.orderDesc(MessageDao.Properties.Date);
//        qb.orderDesc(MessageDao.Properties.Id);
//        return qb.list();
//    }

    /**
     * 整体上报
     * @return
     */
    public List<Message> getUploadFlagMessages() {
        QueryBuilder<Message> qb = mMessageDao.queryBuilder();
        qb.where(MessageDao.Properties.Flag.eq(ContactContract.FLAG_NEED_UPLOAD));
        return qb.list();
    }

    public void updateSmsFlagByKey() {
        mMessageDao.getDatabase().execSQL("UPDATE " +MessageDao.TABLENAME+" SET "+"FLAG = 9");
//        List<Message> messages;
//        QueryBuilder<Message> qb = mMessageDao.queryBuilder();
//        for (int i =0;i<keys.size();i++) {
//            messages = qb.where(MessageDao.Properties.Id.in(keys.get(i))).list();
//            Message sms = messages.get(i);
//            sms.setFlag(ContactContract.FLAG_ALREADY_UPLOAD);
//            mMessageDao.updateInTx(sms);
//        }
//             messages = qb.where(ContactDao.Properties.Id.in(i)).list();
//            Message callLog= messages.get(i);
//            callLog.setFlag(ContactContract.FLAG_IDLE);
//            mMessageDao.update(callLog);
    }
}
