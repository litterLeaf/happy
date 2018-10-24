package com.yinshan.happycash.framework;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;

import com.yinshan.happycash.R;

/**
 * Created by huxin on 2018/9/14.
 */
public class LoadingActivity extends Activity {

    private AlertDialog alertDialog;

    public void showSelfLoadingDialog() {
        if(alertDialog!=null&&alertDialog.isShowing()){
            return;
        }
        if(alertDialog==null){
            alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable());
            alertDialog.setCancelable(true);
        }
        alertDialog.show();
        alertDialog.setContentView(R.layout.loading_alert);
        alertDialog.setCanceledOnTouchOutside(false);
    }

    public void dismissSelfLoadingDialog() {
        if (null != alertDialog && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }
}
