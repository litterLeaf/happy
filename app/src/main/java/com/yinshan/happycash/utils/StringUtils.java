package com.yinshan.happycash.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

import com.yinshan.happycash.R;
import com.yinshan.happycash.application.AppContext;

/**
 * Created by huxin on 2018/8/13.
 */
public class StringUtils {


    private static StringUtils stringUtils;
    private final String code= "008";
    private final String code1="88608";
    private final String code2="XENDIT 88608";
    private final String code3= "013";
    private Context context;
    private final String bluepay="BluePay";

//    private  String doku;


    public StringUtils(){
        context= AppContext.getContext();
    }

    public  static StringUtils getInstance(){
        if(stringUtils == null) {
            stringUtils = new StringUtils();
        }
        return stringUtils;
    }

    public SpannableStringBuilder setStringWords(int content){
        String format = context.getResources().getString(content);
        SpannableStringBuilder builder = new SpannableStringBuilder(format);
        conditionalSelection(format,builder);
        return builder;
    }
    public   SpannableStringBuilder setStringWords(String s,int content){
        String format = context.getResources().getString(content);
        SpannableStringBuilder builder = new SpannableStringBuilder(format);
        conditionalSelection(format,s,builder);
        return builder;
    }
    public   SpannableStringBuilder setStringBluePayWords(int content){
        String format = context.getResources().getString(content);
        SpannableStringBuilder builder = new SpannableStringBuilder(format);
        conditionalSelection(format,bluepay,builder);
        return builder;
    }

    public   SpannableStringBuilder setStringWords(int content,String param){
        String format = String.format(context.getResources().getString(content), param);
        SpannableStringBuilder builder = new SpannableStringBuilder(format);
        builder.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.colorPrimary)),
                format.indexOf(param), format.indexOf(param) + param.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        conditionalSelection(format,builder);
        return builder;
    }

    public   SpannableStringBuilder setStringWords(int content,String param,int color){
        String format = String.format(context.getResources().getString(content), param);
        SpannableStringBuilder builder = new SpannableStringBuilder(format);
        builder.setSpan(new ForegroundColorSpan(context.getResources().getColor(color)),
                format.indexOf(param), format.indexOf(param) + param.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        conditionalSelection(format,builder);
        return builder;
    }
    public   SpannableStringBuilder setBoldStringWords(int content,String param){
        String format = String.format(context.getResources().getString(content), param);
        SpannableStringBuilder builder = new SpannableStringBuilder(format);
        StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);//粗体
        builder.setSpan(styleSpan, format.indexOf(param), format.indexOf(param) + param.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.colorPrimary)),
                format.indexOf(param), format.indexOf(param) + param.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        conditionalSelection(format,builder);
        return builder;
    }


    public   SpannableStringBuilder setBoldStringWords(String content,String param,int color){
        String format = String.format(content, param);
        SpannableStringBuilder builder = new SpannableStringBuilder(format);
        StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);//粗体
        int value  = format.indexOf(param);
        builder.setSpan(styleSpan, format.indexOf(param), format.indexOf(param) + param.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new ForegroundColorSpan(context.getResources().getColor(color)),
                format.indexOf(param), format.indexOf(param) + param.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        conditionalSelection(format,builder);
        return builder;
    }

    public  SpannableStringBuilder setStringWords(int content,String paCodeOrVA,String moneyCount,String s){

        String format = String.format(context.getResources().getString(content), paCodeOrVA, moneyCount);
        SpannableStringBuilder builder = new SpannableStringBuilder(format);
        builder.setSpan(new ForegroundColorSpan( context.getResources().getColor(R.color.colorPrimary)),
                format.indexOf(paCodeOrVA), format.indexOf(paCodeOrVA) + paCodeOrVA.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        if (format.contains(moneyCount)) {
            builder.setSpan(new ForegroundColorSpan( context.getResources().getColor(R.color.colorPrimary)),
                    format.indexOf(moneyCount), format.indexOf(moneyCount) + moneyCount.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        conditionalSelection(format,s,builder);
        return builder;
    }

    public  SpannableStringBuilder setStringWords(String content,String paCodeOrVA,String moneyCount){
        String format = String.format(content, paCodeOrVA, moneyCount);
        SpannableStringBuilder builder = new SpannableStringBuilder(format);
        builder.setSpan(new ForegroundColorSpan( context.getResources().getColor(R.color.colorSPTextPrimary)),
                format.indexOf(paCodeOrVA), format.indexOf(paCodeOrVA) + paCodeOrVA.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        if (format.contains(moneyCount)) {
            builder.setSpan(new ForegroundColorSpan( context.getResources().getColor(R.color.colorSPTextPrimary)),
                    format.indexOf(moneyCount), format.indexOf(moneyCount) + moneyCount.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        conditionalSelection(format,builder);
        return builder;
    }

    public  SpannableStringBuilder setStringWords(int content,String paCodeOrVA,String moneyCount){

        String format = String.format(context.getResources().getString(content), paCodeOrVA, moneyCount);
        SpannableStringBuilder builder = new SpannableStringBuilder(format);
        builder.setSpan(new ForegroundColorSpan( context.getResources().getColor(R.color.colorPrimary)),
                format.indexOf(paCodeOrVA), format.indexOf(paCodeOrVA) + paCodeOrVA.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        if (format.contains(moneyCount)) {
            builder.setSpan(new ForegroundColorSpan( context.getResources().getColor(R.color.colorPrimary)),
                    format.indexOf(moneyCount), format.indexOf(moneyCount) + moneyCount.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        conditionalSelection(format,builder);
        return builder;
    }

    public  SpannableStringBuilder setStringWordsWihtDialog(int content,String data,String month,String year,String date,String name,String ktp){

        String format = String.format(context.getResources().getString(content), data, month,year,date,name,ktp);
        SpannableStringBuilder builder = new SpannableStringBuilder(format);
        builder.setSpan(new ForegroundColorSpan( context.getResources().getColor(R.color.colorPrimary)),
                format.indexOf(data), format.indexOf(data) + data.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        builder.setSpan(new ForegroundColorSpan( context.getResources().getColor(R.color.colorPrimary)),
                format.indexOf(month), format.indexOf(month) + month.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        builder.setSpan(new ForegroundColorSpan( context.getResources().getColor(R.color.colorPrimary)),
                format.indexOf(year), format.indexOf(year) + year.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        builder.setSpan(new ForegroundColorSpan( context.getResources().getColor(R.color.colorPrimary)),
                format.indexOf(date), format.indexOf(date) + date.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        builder.setSpan(new ForegroundColorSpan( context.getResources().getColor(R.color.colorPrimary)),
                format.indexOf(name), format.indexOf(name) + name.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        builder.setSpan(new ForegroundColorSpan( context.getResources().getColor(R.color.colorPrimary)),
                format.indexOf(ktp), format.indexOf(ktp) + ktp.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        conditionalSelection(format,builder);
        return builder;
    }

    public SpannableStringBuilder getFirstStringWithDialog(int content,String borrower,String province){
        String date = DateUtil.showDate("yyyy-MM-dd");
        String format = String.format(context.getResources().getString(content),date, borrower, province);
        SpannableStringBuilder builder = new SpannableStringBuilder(format);
        builder.setSpan(new ForegroundColorSpan( context.getResources().getColor(R.color.colorPrimary)),
                format.indexOf(date), format.indexOf(date) + date.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new ForegroundColorSpan( context.getResources().getColor(R.color.colorPrimary)),
                format.indexOf(borrower), format.indexOf(borrower) + borrower.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new ForegroundColorSpan( context.getResources().getColor(R.color.colorPrimary)),
                format.indexOf(province), format.indexOf(province) + province.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        conditionalSelection(format,builder);
        return builder;
    }

    public SpannableStringBuilder getSpanWithStrsDialog(int content,Object... args){
        String format = String.format(context.getResources().getString(content),args);
        SpannableStringBuilder builder = new SpannableStringBuilder(format);
        for(int i=0;i<args.length;i++){
            builder.setSpan(new ForegroundColorSpan( context.getResources().getColor(R.color.colorPrimary)),
                    format.indexOf((String) args[i]), format.indexOf((String) args[i]) + ((String)args[i]).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        conditionalSelection(format,builder);
        return builder;
    }

    public SpannableStringBuilder getSecondWithStrsDialog(int content,String amount,String day){
        String format = String.format(context.getResources().getString(content),amount,day);
        SpannableStringBuilder builder = new SpannableStringBuilder(format);

        builder.setSpan(new ForegroundColorSpan( context.getResources().getColor(R.color.colorPrimary)),
                format.indexOf(amount), format.indexOf(amount) + amount.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new ForegroundColorSpan( context.getResources().getColor(R.color.colorPrimary)),
                format.indexOf(day), format.indexOf(day) + day.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        conditionalSelection(format,builder);
        return builder;
    }

    private void conditionalSelection(String format,SpannableStringBuilder builder){


        if (format.contains(code)) {
            builder.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.colorPrimary)),
                    format.indexOf(code), format.indexOf(code) + code.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        if (format.contains(code1)) {
            builder.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.colorPrimary)),
                    format.indexOf(code1), format.indexOf(code1) + code1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        if (format.contains(code2)) {
            builder.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.colorPrimary)),
                    format.indexOf(code2), format.indexOf(code2) + code2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        if (format.contains(code3)) {
            builder.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.colorPrimary)),
                    format.indexOf(code3), format.indexOf(code3) + code3.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    private void conditionalSelection(String format,String s,SpannableStringBuilder builder){
        if (format.contains(s)) {
            builder.setSpan(new ForegroundColorSpan( context.getResources().getColor(R.color.colorPrimary)),
                    format.indexOf(s), format.indexOf(s) + s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }
}
