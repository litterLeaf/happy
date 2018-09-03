package com.yinshan.happycash.widget;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by huxin on 2018/4/17.
 */

public class HappySnackBar {

    private static Snackbar snackbar;

    public static void showSnackBar(View view,String content,int type){
//        snackbar= getInstence(view);
//        View snackbarView = snackbar.getView();
    }

    public static void showSnackBar(View view,int content,int type) {

    }

    private static Snackbar getInstence(View view){
        snackbar= Snackbar.make(view,"",  Snackbar.LENGTH_SHORT);
        return snackbar;
    }
}
