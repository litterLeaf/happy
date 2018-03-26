package com.yinshan.happycash.framework;


import com.yinshan.happycash.application.AppApplication;
import com.yinshan.happycash.network.common.RxHttpUtils;

import java.util.HashMap;

/**
 * Created by admin on 2018/3/26.
 */

public class DateManager {
    public static String MOBILE_CACHE_KEY        = "mobile_cache_key";
    private static DateManager instance;
    ACache cache;
    private HashMap<String,Object> mTempStatus;

    private DateManager(){
        cache = ACache.get(AppApplication.appContext);
        mTempStatus = new HashMap<>();
    }
    public static DateManager getInstance() {
        if (instance == null) {
            synchronized (RxHttpUtils.class) {
                if (instance == null) {
                    instance = new DateManager();
                }
            }
        }
        return instance;
    }



    public static void putToCache(String key,Object obj){
        instance.storeMessage(key,obj);
    }
    public static void removeToCache(String key){
        instance.getAndRemove(key);
    }
    public static void putToFile(String key,String obj){
        instance.cache.put(key,obj);
    }

    public static void removeToFile(String key){
        instance.cache.remove(key);
    }

    public static Object checkoutMessage(String key){
        return instance.getMessage(key);
    }

    public static Object removeMessage(String key){
        return instance.getAndRemove(key);
    }
    public void setMobile(String mobile){
        cache.put(MOBILE_CACHE_KEY, mobile);
    }

    public String getMobile(){
        return cache.getAsString(MOBILE_CACHE_KEY);
    }

    private synchronized void storeMessage(String key,Object message){
        if (key != null) {
            mTempStatus.put(key, message);
        }
    }

    /**
     * 获取暂存的信息，不删除
     * @param key
     * @return
     */
    public synchronized Object getMessage(String key){
        if (key == null) {
            return null;
        }
        return mTempStatus.get(key);
    }
    /**
     * 获取暂存的信息并删除
     * @param key
     * @return
     */
    private synchronized Object getAndRemove(String key){
        if (key == null) {
            return null;
        }
        return mTempStatus.remove(key);
    }


}
