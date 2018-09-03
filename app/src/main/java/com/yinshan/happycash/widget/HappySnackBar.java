package com.yinshan.happycash.widget;

import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.application.AppContext;

/**
 * Created by huxin on 2018/4/17.
 */

public class HappySnackBar {

    private static Snackbar snackbar;

    public static void showSnackBar(View view,String content,int type){
        snackbar= getInstence(view);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(AppContext.getContext().getResources().getColor(R.color.transparent));
        Snackbar.SnackbarLayout snackbarLayout=(Snackbar.SnackbarLayout)snackbarView;
        creatView(snackbarView,snackbarLayout,type,content);
        snackbar.show();
    }

    public static void showSnackBar(View view,int content,int type) {
        String contentString= AppContext.getContext().getString(content);
        showSnackBar(view,contentString,type);
    }

    private static Snackbar getInstence(View view){
        snackbar= Snackbar.make(view,"",  Snackbar.LENGTH_SHORT);
        return snackbar;
    }

    public static void showSnackBar(View view,int content,int type,int time) {
        String contentString= AppContext.getContext().getString(content);
        snackbar= getInstence(view);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(AppContext.getContext().getResources().getColor(R.color.transparent));
        Snackbar.SnackbarLayout snackbarLayout=(Snackbar.SnackbarLayout)snackbarView;
        creatView(snackbarView,snackbarLayout,type,contentString);
        snackbar.setDuration(time);
        snackbar.show();
    }

    public static void showSnackBar(View view,String content,int type,int time) {
        snackbar= getInstence(view);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(AppContext.getContext().getResources().getColor(R.color.snackbar_bg));
        Snackbar.SnackbarLayout snackbarLayout=(Snackbar.SnackbarLayout)snackbarView;
        creatView(snackbarView,snackbarLayout,type,content);
        snackbar.setDuration(time);
        snackbar.show();
    }

    private static void creatView(View snackbarView,Snackbar.SnackbarLayout snackbarLayout, int type, String content){
        View add_view = LayoutInflater.from(snackbarView.getContext()).inflate(R.layout.view_snackbar,null);
        add_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        p.gravity= Gravity.CENTER_VERTICAL;
        TextView text = (TextView) add_view.findViewById(R.id.text);
        text.setText(content);
        snackbarLayout.addView(add_view,Snackbar.LENGTH_SHORT,p);
    }
}
