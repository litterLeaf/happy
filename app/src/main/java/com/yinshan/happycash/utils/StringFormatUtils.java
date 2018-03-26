package com.yinshan.happycash.utils;

import android.content.Context;

import com.yinshan.happycash.R;

/**
 * Created by huxin on 2018/3/26.
 */

public class StringFormatUtils {

    public static String dayTimeFormat(Context context, int period) {
        StringBuffer result = new StringBuffer();
        int days = period;
        if (days > 365) {
            int year = days / 365;
            result.append(year + context.getString(R.string.years));
            days = days % 365;
        }

        if (days >30) {
            int month = days / 30;
            result.append(month + context.getString(R.string.months));
            days = days & 30;
        }

        result.append(days +" "+context.getString(R.string.days));
        return result.toString();
    }

    public static String fullTime(Context context, int period,String periodUnit){
        if(periodUnit.equals("D")){
            return period +" "+ context.getString(R.string.days);
        }else if(periodUnit.equals("M")){
            return period +" "+ context.getString(R.string.months);
        }else if(periodUnit.equals("Y")){
            return period +" "+ context.getString(R.string.years);
        }else{
            return period + "";
        }
    }

    public static String moneyFormat(int amount) {
        StringBuffer result = new StringBuffer();
        while(amount > 1000){
            int temp = amount % 1000;
            amount = amount / 1000;
            String tempResult;
            if(temp < 10){
                tempResult = "00" + temp;
            }else if(temp <100){
                tempResult = "0" + temp;
            }else{
                tempResult = temp + "";
            }
            result.insert(0,"." + tempResult);
        }
        result.insert(0, "" + amount);
        return result.toString();
    }

    public static String moneyFormat(float paidAmount){

        return moneyFormat((int)paidAmount);
    }
    public static String moneyFormatString(String paidAmount){

        return moneyFormat(Integer.valueOf(String.valueOf(paidAmount)));
    }
    public static String moneyFormat(double paidAmount){
        return moneyFormat((int)paidAmount);
    }

    public static String moneyFormat(String paidAmount) {
        if (paidAmount == null) {
            return null;
        }
        String[] split = null;
        if (paidAmount.contains(".")) {
            split = paidAmount.split("\\.");
            paidAmount = split[0];
        }
        StringBuilder result = new StringBuilder();
        int length = paidAmount.length();
        int startIndex = length;
        String substring = null;
        while(startIndex > 3) {
            substring = paidAmount.substring(startIndex - 3, startIndex);
            startIndex = startIndex - 3;
            result.insert(0, "." + substring);
        }
        result.insert(0, paidAmount.substring((0), startIndex));
        if (split != null && split.length >=2) {
            result.append("." + split[1].substring(0,1));
        }
        return result.toString();
    }
}
