package com.yinshan.happycash.framework;


/**
 * Created by admin on 2018/3/26.
 */

public class DateManager {
    public static String MOBILE_CACHE_KEY        = "mobile_cache_key";
    ACache cache;
    public static DateManager getInstance() {
        return DateManager.Holder.INSTANCE;
    }
    private static class Holder{
        private static final DateManager INSTANCE = new DateManager();
    }
    public static void putMessage(String key,Object obj){
        Holder.INSTANCE.storeMessage(key,obj);
    }
    public static void putMessageToFile(String key,String obj){
        Holder.INSTANCE.cache.put(key,obj);
    }
    public static String checkoutMessageFromFile(String key){
        return Holder.INSTANCE.cache.getAsString(key);
    }
//    public static Object checkoutMessage(String key){
//        return Holder.INSTANCE.getMessage(key);
//    }

//    public static Object removeMessage(String key){
//        return Holder.INSTANCE.getAndRemove(key);
//    }
    public void setMobile(String mobile){
        cache.put(MOBILE_CACHE_KEY, mobile);
    }
    public String getMobile(){
        return cache.getAsString(MOBILE_CACHE_KEY);
    }
    private synchronized void storeMessage(String key,Object message){

        if (key != null) {
//            mTempStatus.put(key, message);
        }
    }
}
