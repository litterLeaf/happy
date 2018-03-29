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
    private Long              mStartTime;
    private Handler           mHandler;
    private boolean canFinish= true;
    private boolean isVisible = true;
    private SplashContract.Presenter splashPresenter;
    private LastLoanAppBean mLatestLoanAppBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStartTime = SystemClock.currentThreadTimeMillis();
        mHandler = new Handler();
        jumpToMainActivity(mStartTime);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        splashPresenter = new SplashPresenter(this);
        splashPresenter.attachView(this);
        if (!TokenManager.getInstance().hasLogin()) {
            canFinish = true;
            jumpToMainActivity(SystemClock.currentThreadTimeMillis());
        } else {
            splashPresenter.getLastLoanAppBean(TokenManager.getInstance().getToken());
        }
    }

    private void jumpToMainActivity(Long currentTime) {
        Long delayTime = (currentTime - mStartTime >= 3000L) ? 0 : (mStartTime + 3000 - currentTime);
        mHandler.postDelayed(() -> {
//            if(TextUtils.isEmpty(password)){
//                if(TextUtils.isEmpty(TokenManager.getInstance().getToken())){
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    if (canFinish) {
                        mHandler.removeCallbacksAndMessages(null);
                        finish();
                    } else {
                        //                    setVisible(false);
                        isVisible = false;
                    }
//                }else {
//                    Intent intent = new Intent(SplashActivity.this, SetPatternActivity.class);
//                    intent.putExtra("splash",true);
//                    startActivity(intent);
//                    if (canFinish) {
//                        mHandler.removeCallbacksAndMessages(null);
//                        finish();
//                    } else {
//                        //                    setVisible(false);
//                        isVisible = false;
//                    }
//                }
//            }else {
//                Intent intent = new Intent(SplashActivity.this, VerifyPatternActivity.class);
//                startActivity(intent);
//                if (canFinish) {
//                    mHandler.removeCallbacksAndMessages(null);
//                    finish();
//                } else {
//                    //                    setVisible(false);
//                    isVisible = false;
//                }
//            }
        },delayTime);
    }

    @Override
    public void getStatusSuccess(LastLoanAppBean latestLoanAppBean) {
        mLatestLoanAppBean = latestLoanAppBean;
        DateManager dateManager = DateManager.getInstance();
        if(latestLoanAppBean ==null){
            if(!TokenManager.getInstance().hasLogin()){
                dateManager.putToCache(SPKeyUtils.APP_STATUES, AppLoanStatus.UNLOAN);
            }
        }else {
            dateManager.putToCache(SPKeyUtils.LOANAPPBEAN,latestLoanAppBean);
//            dateManager.putToCache(SPKeyUtils.STATUES,latestLoanAppBean.getStatus());
            dateManager.putToCache(SPKeyUtils.SERVER_STATUES, latestLoanAppBean.getStatus());
            dateManager.putToCache(SPKeyUtils.APP_STATUES, StatusManagementUtils.loanStatusClassify(latestLoanAppBean));
            StatusManagementUtils.loanStatusClassify(latestLoanAppBean);
        }
        canFinish = true;
        if (SplashActivity.this.isVisible) {
            jumpToMainActivity(SystemClock.currentThreadTimeMillis());
        } else {
            mHandler.removeCallbacksAndMessages(null);
            finish();
        }
    }

    @Override
    public void getStatusError(String message) {
        DateManager.getInstance().putToCache(SPKeyUtils.APP_STATUES, AppLoanStatus.UNLOAN);
        canFinish = true;
        if (SplashActivity.this.isVisible) {
            jumpToMainActivity(SystemClock.currentThreadTimeMillis());
        } else {
            mHandler.removeCallbacksAndMessages(null);
            finish();
        }
    }
}
