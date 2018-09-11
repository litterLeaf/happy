package com.yinshan.happycash.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.DateManager;
import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.utils.AppLoanStatus;
import com.yinshan.happycash.utils.SPKeyUtils;
import com.yinshan.happycash.utils.SPUtils;
import com.yinshan.happycash.utils.StatusManagementUtils;
import com.yinshan.happycash.view.main.contract.SplashContract;
import com.yinshan.happycash.view.main.model.LastLoanAppBean;
import com.yinshan.happycash.view.main.presenter.SplashPresenter;
import com.yinshan.happycash.view.main.view.impl.MainActivity;

/**
 * Created by admin on 2018/3/20.
 */

public class SplashActivity extends AppCompatActivity implements SplashContract.View{
     SplashContract.Presenter splashPresenter;
     LastLoanAppBean mLatestLoanAppBean;
    private Handler  mHandler;
    boolean isDestroy = false;

    public static boolean isGetStatus = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        splashPresenter = new SplashPresenter(this);
        splashPresenter.attachView(this);
//        if(TokenManager.getInstance().hasLogin()) {
//            splashPresenter.getLastLoanAppBean(TokenManager.getInstance().getToken());
//        }else
//            toGoMainActivity();
        if(TokenManager.getInstance().hasLogin()) {
            splashPresenter.getLastLoanAppBean(TokenManager.getInstance().getToken());
        }

        toGoMainActivity();
    }

    private void toGoMainActivity() {
        mHandler = new Handler();
        mHandler.postDelayed(()-> {
                if(isDestroy)
                    return;
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
        },3000);
    }

    @Override
    public void getStatusSuccess(LastLoanAppBean latestLoanAppBean) {
        if(isDestroy)
            return;
        mLatestLoanAppBean = latestLoanAppBean;
        if(mLatestLoanAppBean ==null){
            if(!TokenManager.getInstance().hasLogin()){
                DateManager.setAPPStatus(AppLoanStatus.UNLOAN);
            }
        }else {
            SPUtils.getInstance().setObject(SPKeyUtils.LOANAPPBEAN,latestLoanAppBean);
            DateManager.setServerStatus( mLatestLoanAppBean.getStatus());
            DateManager.setAPPStatus(StatusManagementUtils.loanStatusClassify(mLatestLoanAppBean));
        }
        Intent intent = new Intent(this,MainActivity.class);
        isGetStatus = true;
        startActivity(intent);
        finish();

    }

    @Override
    public void getStatusError(String message) {
        DateManager.setAPPStatus(AppLoanStatus.UNLOAN);
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isDestroy = true;
    }
}
