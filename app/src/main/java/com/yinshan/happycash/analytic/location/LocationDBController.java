package com.yinshan.happycash.analytic.location;

import android.content.Context;

import com.yinshan.happycash.application.AppApplication;
import com.yinshan.happycash.dao.DaoDbHelper;
import com.yinshan.happycash.dao.DaoMaster;
import com.yinshan.happycash.dao.DaoSession;
import com.yinshan.happycash.dao.Location;
import com.yinshan.happycash.dao.LocationDao;

import java.util.List;

/**
 * Created by yyhuang on 2017/6/16  18:36.
 */

public class LocationDBController {

    private static LocationDBController mInstance;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;
    private LocationDao mLocationDao;

    public LocationDBController(Context context) {
//        DaoMaster.OpenHelper openHelper = new DaoMaster.DevOpenHelper(context, DaoDbHelper.DB_NAME, null);
//        mDaoMaster = new DaoMaster(openHelper.getWritableDatabase());
//        mDaoSession = mDaoMaster.newSession();
        DaoSession daoSession = DaoDbHelper.getInstance().getDaoSession();
        mLocationDao = daoSession.getLocationDao();
    }

    public static LocationDBController getInstance() {
        if (mInstance == null) {
            synchronized (LocationDBController.class) {
                if (mInstance == null) {
                    mInstance = new LocationDBController(AppApplication.appContext);
                }
            }
        }
        return mInstance;
    }

    public void insert(Location location) {
        mLocationDao.insertInTx(location);

//        long count = mLocationDao.count();
//        if (count >= AppReport.LOCATION_NUMBER) {
//            //打开App时，5s之后会发送地理位置信息，如果这是又插入了新的位置，会造成重复发送
//            AppReport.sendLocationsDelay();
//        }
    }

    public List<Location> getList() {
        return mLocationDao.queryBuilder().list();
    }

    public void delete(List<Location> locations) {
        mLocationDao.deleteInTx(locations);
    }

    public void deleteByKey(List<Long> keys) {
        mLocationDao.deleteByKeyInTx(keys);
    }
}
