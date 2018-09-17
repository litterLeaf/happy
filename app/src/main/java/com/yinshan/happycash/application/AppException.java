package com.yinshan.happycash.application;

import android.content.Context;
import android.content.Intent;

import com.yinshan.happycash.R;
import com.yinshan.happycash.network.common.base.BaseURL;
import com.yinshan.happycash.view.login.LoginActivity;
import com.yinshan.happycash.widget.common.ToastManager;

/**
 * Created by huxin on 2018/9/17.
 */
public class AppException {

    public static boolean handleException(Context context, int errorCode, String errorMessage){
//        if(BaseURL.HTTP_404==errorCode){
//            ToastManager.showToast(context.getString(R.string.app_exception_server));
//            return true;
//        }
        if(errorCode== BaseURL.HTTP_401){
            context.startActivity(new Intent(context, LoginActivity.class));
        }
        return false;
    }
}
