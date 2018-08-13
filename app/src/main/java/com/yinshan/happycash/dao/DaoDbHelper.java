package com.yinshan.happycash.dao;


import android.content.Context;

import com.yinshan.happycash.application.AppApplication;


public class DaoDbHelper {
    public final static String DB_NAME = "banda-db";

    private static DaoDbHelper mInstance;

    private Context mContext;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;

    public DaoDbHelper(Context context) {
        mContext = context;
        DaoMaster.OpenHelper openHelper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        mDaoMaster = new DaoMaster(openHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public static DaoDbHelper getInstance() {
        if (mInstance == null) {
            synchronized (DaoDbHelper.class) {
                if (mInstance == null) {
                    mInstance = new DaoDbHelper(AppApplication.instance);
                }
            }
        }
        return mInstance;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public Context getContext() {
        return mContext;
    }
}


