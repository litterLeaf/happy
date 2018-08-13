package com.yinshan.happycash.analytic.callLog;


import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;

import com.yinshan.happycash.analytic.contacts.ContactContract;
import com.yinshan.happycash.dao.CallLog;
import com.yinshan.happycash.dao.CallLogDao;
import com.yinshan.happycash.dao.DaoDbHelper;
import com.yinshan.happycash.dao.DaoSession;
import com.yinshan.happycash.utils.DateUtil;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.LinkedList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class CallLogDBController {
    private Context mContext;

    private static CallLogDBController mInstance;

    private CallLogDao mCallLogDao;

    public CallLogDBController() {
        mContext = DaoDbHelper.getInstance().getContext();

        DaoSession daoSession = DaoDbHelper.getInstance().getDaoSession();
        mCallLogDao = daoSession.getCallLogDao();
    }

    public static CallLogDBController getInstance() {
        if (mInstance == null) {
            synchronized (CallLogDBController.class) {
                if (mInstance == null) {
                    mInstance = new CallLogDBController();
                }
            }
        }
        return mInstance;
    }

    public void gainCallLogs() {
        Observable.create(new Observable.OnSubscribe<List<CallLog>>() {
            @Override
            public void call(Subscriber<? super List<CallLog>> subscriber) {
                List<CallLog> callLogs = getCallLogs();
                subscriber.onNext(callLogs);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<List<CallLog>>() {
                    @Override
                    public void onCompleted() {
//                        AppReport.sendCallLogsDelay();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("CallLogDBController", "e" + e.getMessage());
                    }

                    @Override
                    public void onNext(List<CallLog> callLogs) {
                        updateCallLogs(callLogs);
                    }
                });
    }

    public void updateCallLogs(List<CallLog> callLogs) {
        List<CallLog> addCallLogs = new LinkedList<>();
        if (callLogs == null) {
            Log.e("CallLogDBController", "callLogsList is null");
            return;
        }
        for (CallLog callLog : callLogs) {
            QueryBuilder<CallLog> qb = mCallLogDao.queryBuilder();
            qb.where(
                    CallLogDao.Properties.Number.eq(callLog.getNumber()),
                    CallLogDao.Properties.Type.eq(callLog.getType()),
                    CallLogDao.Properties.Date.eq(callLog.getDate())
            );
            CallLog log = qb.unique();
            if (log == null) {
                callLog.setFlag(ContactContract.FLAG_NEED_UPLOAD);
                callLog.setState(ContactContract.STATE_ADD);
                addCallLogs.add(callLog);
            }
        }
        if (addCallLogs.size() > 0) {
            mCallLogDao.insertInTx(addCallLogs);
        }
    }

    public List<CallLog> getUploadCallLogs() {
        QueryBuilder<CallLog> qb = mCallLogDao.queryBuilder();
        qb.where(CallLogDao.Properties.State.notEq(ContactContract.STATE_IDLE));
        qb.orderDesc(CallLogDao.Properties.Date);
        qb.limit(500);
        return qb.list();
    }

    public void updateStateByKey(List<Long> keys) {
        QueryBuilder<CallLog> qb = mCallLogDao.queryBuilder();
        List<CallLog> callLogs = qb.where(CallLogDao.Properties.Id.in(keys)).list();
        if (callLogs == null || callLogs.size() == 0) {
            return;
        }
        for (CallLog callLog : callLogs) {
            callLog.setState(ContactContract.STATE_IDLE);
        }
        mCallLogDao.updateInTx(callLogs);
    }

    private List<CallLog> getCallLogs() {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        List<CallLog> callLogs = new LinkedList<>();
        ContentResolver resolver = mContext.getContentResolver();
        Cursor cursor = resolver.query(android.provider.CallLog.Calls.CONTENT_URI,
                new String[]{
                        android.provider.CallLog.Calls.CACHED_NAME,
                        android.provider.CallLog.Calls.NUMBER,
                        android.provider.CallLog.Calls.TYPE,
                        android.provider.CallLog.Calls.DATE,
                        android.provider.CallLog.Calls.DURATION
                },
                null, null,
                android.provider.CallLog.Calls.DEFAULT_SORT_ORDER);
        try {
            while (cursor.moveToNext()) {
                String name = cursor.getString(0);
                String number = cursor.getString(1);
                int type = cursor.getInt(2);
                Long date = cursor.getLong(3);
                Long duration = cursor.getLong(4);

                CallLog callLog = new CallLog();
                callLog.setNumber(number);
                if (TextUtils.isEmpty(name)) {
                    callLog.setName(number);
                } else {
                    callLog.setName(name);
                }
                callLog.setType(type);
                String dateTime = DateUtil.now(date);
                callLog.setDate(dateTime);
                callLog.setDuration(duration);
                callLogs.add(callLog);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return callLogs;
    }

    /**
     * 整体上报
     *
     * @return
     */
    public void updateCallLogFlagByKey() {

        mCallLogDao.getDatabase().execSQL("UPDATE " + CallLogDao.TABLENAME + " SET " + "FLAG = 9");

    }

    public List<CallLog> getUploadFlagCallLog() {
        QueryBuilder<CallLog> qb = mCallLogDao.queryBuilder();
        qb.where(CallLogDao.Properties.Flag.eq(ContactContract.FLAG_NEED_UPLOAD));
        return qb.list();
    }
}

