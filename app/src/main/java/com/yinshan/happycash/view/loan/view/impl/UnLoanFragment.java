package com.yinshan.happycash.view.loan.view.impl;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseFragment;
import com.yinshan.happycash.framework.MessageEvent;
import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.utils.AndroidUtils;
import com.yinshan.happycash.utils.MyDebugUtils;
import com.yinshan.happycash.utils.SPKeyUtils;
import com.yinshan.happycash.utils.SPUtils;
import com.yinshan.happycash.utils.ServiceLoanStatus;
import com.yinshan.happycash.view.liveness.view.impl.OliveStartActivity;
import com.yinshan.happycash.view.login.LoginActivity;
import com.yinshan.happycash.view.main.model.LastLoanAppBean;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 *        ┃　　　┃   神兽保佑
 *        ┃　　　┃   代码无BUG！
 *        ┃　　　┗━━━┓
 *        ┃　　　　　　　┣┓
 *        ┃　　　　　　　┏┛
 *        ┗┓┓┏━┳┓┏┛
 *            ┃┫┫　┃┫┫
 *            ┗┻┛　┗┻┛
 *
 * @author admin
 *         on 2018/1/31
 */

public class UnLoanFragment extends BaseFragment {

    private static final int MIN_VALUE = 2000000;
    private static final int MAX_VALUE = 6000000;
    private static final int MONEY_SEG = 4;
    private static final int RATE = 8;

    long loanMoney = MIN_VALUE;
    int seg;
    int choosePeriod = 1;

    @BindView(R.id.chooseMoney)
    TextView mChooseMoney;
    @BindView(R.id.unloan_fee)
    TextView mUnloanFee;
    @BindView(R.id.unloan_seeker)
    SeekBar unloanSeeker;
    @BindView(R.id.bt_period_1_unloan)
    Button periodOne;



    @BindView(R.id.bt_period_3_unloan)
    Button periodThree;
    @BindView(R.id.unloan_go_information)
    Button goInformation;

    @Override
    protected void initView() {
        unloanSeeker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(i<3)
                    seg = 0;
                else
                    seg = (i-1)/(100/MONEY_SEG)+1;
                loanMoney = MIN_VALUE+(MAX_VALUE-MIN_VALUE)/MONEY_SEG*seg;
                MyDebugUtils.v("the info is "+i+"   "+seg+"   "+loanMoney);
                setComputeMoney();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                unloanSeeker.setProgress(seg*100/MONEY_SEG);
            }
        });
        setComputeMoney();
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_main_unloan;
    }


    @OnClick({R.id.bt_period_1_unloan, R.id.bt_period_3_unloan, R.id.unloan_go_information})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_period_1_unloan:
                setChoose1Period();
                setComputeMoney();
                break;
            case R.id.bt_period_3_unloan:
                setChoose3Period();
                setComputeMoney();
                break;
            case R.id.unloan_go_information:
                if(TokenManager.getInstance().hasLogin()){
                    LastLoanAppBean loanBean = SPUtils.getInstance().getObject(SPKeyUtils.LOANAPPBEAN,LastLoanAppBean.class);
                    if(loanBean!=null&&loanBean.getStatus()!=null&&loanBean.getStatus().equals(ServiceLoanStatus.SUBMITTED)){
                        //if(TextUtils.isEmpty(SPUtils.getInstance().getImie())){
                        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE) !=
                                PackageManager.PERMISSION_GRANTED){
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_PHONE_STATE}, 100);
                        }else{
                            startActivity(new Intent(getActivity(),OliveStartActivity.class));
                        }
                    }else{
                        EventBus.getDefault().post(new MessageEvent());
                    }
                }else {
                    mStartActivity( LoginActivity.class);
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 100:
                for(int grant:grantResults){
                    if(grant== PackageManager.PERMISSION_GRANTED){
                        SPUtils.getInstance().setImei(AndroidUtils.getIMIE(getActivity()));
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }

    }

    private void setChoose1Period(){
        choosePeriod = 1;
        periodOne.setBackgroundResource(R.drawable.shape_unloan_bg);
        periodThree.setBackgroundResource(R.drawable.shape_period_bg);
    }

    private void setChoose3Period(){
        choosePeriod = 3;
        periodOne.setBackgroundResource(R.drawable.shape_period_bg);
        periodThree.setBackgroundResource(R.drawable.shape_unloan_bg);
    }

    private void setComputeMoney(){
        mChooseMoney.setText(String.valueOf(loanMoney));
        mUnloanFee.setText(String.valueOf(getLastMoney()));
    }

    private long getLastMoney(){
        return loanMoney+loanMoney*RATE*choosePeriod/100;
    }
}