package com.yinshan.happycash.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static SimpleDateFormat simpleDateFormat;

    public static String now() {
        return getSimpleDateFormatInstence("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
    public static String now(long timeStamp) {
        return getSimpleDateFormatInstence("yyyy-MM-dd HH:mm:ss").format(new Date(timeStamp));
    }
    public static String nowDateFormat() {
        return getSimpleDateFormatInstence("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    }

    public static String showDate(String type) {
        return getSimpleDateFormatInstence(type).format(new Date());
    }
    public static String showDate(String type,long timeStamp) {
        return getSimpleDateFormatInstence(type).format(new Date(timeStamp));
    }

    public static String  dealSpecialDate(String createTime){
        String dateString = "";
        Date d = dealDate(createTime);//注意是空格+UTC
        if(d!=null){
            dateString = getSimpleDateFormatInstence("dd/MM/yyyy HH:mm").format(d);
        }
        return dateString;
    }
    public static String  dealSpecialDate(String createTime,String type){
        String dateString = "";
        Date d = dealDate(createTime);//注意是空格+UTC
        if(d!=null){
            dateString = getSimpleDateFormatInstence(type).format(d);
        }
        return dateString;
    }
    public static String  dealSpecialYear(String createTime){
        String dateString = "";
        Date d = dealDate(createTime);//注意是空格+UTC
        if(d!=null){
            dateString = getSimpleDateFormatInstence("dd/MM/yyyy").format(d);
        }
        return dateString;
    }
    public static String dealSpecialHour(String  time) {
        String dateString = "";
        Date d = dealDate(time);//注意是空格+UTC
        if(d!=null){
            dateString = getSimpleDateFormatInstence("HH:mm").format(d);
        }
        return dateString;
    }

    //处理 例如 “2018-01-04T12:23:45.221Z”
    private static Date dealDate(String createTime){
        if(!TextUtils.isEmpty(createTime)){
            SimpleDateFormat format =getSimpleDateFormatInstence("yyyy-MM-dd'T'HH:mm:ss Z");//注意格式化的表达式
            try {
                Date d = format.parse(createTime.replace("Z", " UTC"));//注意是空格+UTC
                return d;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }



    private static SimpleDateFormat getSimpleDateFormatInstence(String type){
        simpleDateFormat = new SimpleDateFormat(type);
        return simpleDateFormat;
    }
    public static long getCurrentTimeMillis(){
        return System.currentTimeMillis();
    }
}
