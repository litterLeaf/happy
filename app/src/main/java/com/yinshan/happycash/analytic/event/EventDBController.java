package com.yinshan.happycash.analytic.event;

import android.content.Context;

import com.yinshan.happycash.application.AppApplication;
import com.yinshan.happycash.dao.DaoDbHelper;
import com.yinshan.happycash.dao.DaoMaster;
import com.yinshan.happycash.dao.DaoSession;
import com.yinshan.happycash.dao.Event;
import com.yinshan.happycash.dao.EventDao;
import com.yinshan.happycash.utils.DateUtil;
import com.yinshan.happycash.utils.ThreadPoolManager;

import java.util.List;

/**
 * Created by yyhuang on 2017/6/22  14:23.
 */

public class EventDBController {
    private static EventDBController mInstance;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;
    private EventDao mEventDao;

    public EventDBController(Context context) {
//        DaoMaster.OpenHelper openHelper = new DaoMaster.DevOpenHelper(context, DaoDbHelper.DB_NAME, null);
//        mDaoMaster = new DaoMaster(openHelper.getWritableDatabase());
//        mDaoSession = mDaoMaster.newSession();
        DaoSession daoSession = DaoDbHelper.getInstance().getDaoSession();
        mEventDao = daoSession.getEventDao();
    }

    public static EventDBController getInstance() {
        if (mInstance == null) {
            synchronized (EventDBController.class) {
                if (mInstance == null) {
                    mInstance = new EventDBController(AppApplication.instance);
                }
            }
        }
        return mInstance;
    }

    public void insertByTd(final String eventId) {
        ThreadPoolManager.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                insert(eventId);
            }
        });

//        long count = mEventDao.count();
//        if (count >= AppReport.EVENT_NUMBER) {
//            AppReport.sendEvents();
//        }
    }

    public void insertByTd(final String eventId, final String data) {

    }

    public void insert(final String eventId) {
        Event event = new Event();
        event.setEventId(eventId);
        event.setEventTime(DateUtil.nowDateFormat());
        mEventDao.insert(event);
    }

    public List<Event> getList() {
        return mEventDao.loadAll();
    }

    public void deleteByKey(List<Long> keys) {
        mEventDao.deleteByKeyInTx(keys);
    }

    public void delete() {
//        mEventDao.getDatabase().execSQL("TRUNCATE   TABLE " +EventDao.TABLENAME);
     mEventDao.getDatabase().execSQL("DELETE  FROM " +EventDao.TABLENAME);
    }
}
