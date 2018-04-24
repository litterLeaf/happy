package com.yinshan.happycash.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.DateManager;
import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.utils.AppLoanStatus;
import com.yinshan.happycash.utils.SPKeyUtils;
import com.yinshan.happycash.utils.ServiceLoanStatus;
import com.yinshan.happycash.utils.StatusManagementUtils;
import com.yinshan.happycash.view.main.contract.SplashContract;
import com.yinshan.happycash.view.main.model.LastLoanAppBean;
import com.yinshan.happycash.view.main.presenter.SplashPresenter;

/**
 * Created by admin on 2018/3/20.
 */

public class SplashActivity extends AppCompatActivity implements SplashContract.View{
     SplashContract.Presenter splashPresenter;
     LastLoanAppBean mLatestLoanAppBean;
    private Handler           mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        splashPresenter = new SplashPresenter(this);
        splashPresenter.attachView(this);
        splashPresenter.getLastLoanAppBean(TokenManager.getInstance().getToken());
//        toGoMainActivity();
    }

    private void toGoMainActivity() {
        mHandler = new Handler();
        mHandler.postDelayed(()-> {
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
        },3000);
    }

    @Override
    public void getStatusSuccess(LastLoanAppBean latestLoanAppBean) {
        mLatestLoanAppBean = latestLoanAppBean;
        DateManager dateManager = DateManager.getInstance();
        if(mLatestLoanAppBean ==null){
            if(!TokenManager.getInstance().hasLogin()){
                dateManager.putToCache(SPKeyUtils.APP_STATUES, AppLoanStatus.UNLOAN);
            }
        }else {
            dateManager.putToCache(SPKeyUtils.LOANAPPBEAN,mLatestLoanAppBean);
            dateManager.putToCache(SPKeyUtils.SERVER_STATUES, mLatestLoanAppBean.getStatus());
            dateManager.putToCache(SPKeyUtils.APP_STATUES, StatusManagementUtils.loanStatusClassify(mLatestLoanAppBean));
            StatusManagementUtils.loanStatusClassify(mLatestLoanAppBean);
        }
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void getStatusError(String message) {
        DateManager.getInstance().putToCache(SPKeyUtils.APP_STATUES, AppLoanStatus.UNLOAN);
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
