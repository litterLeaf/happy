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

import com.hwangjr.rxbus.RxBus;
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
import com.yinshan.happycash.view.main.view.impl.MainActivity;

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
        MainActivity.loanMoney = MainActivity.MIN_VALUE;

        RxBus.get().register(this);

        if(MainActivity.choosePeriod==1)
            setChoose1Period();
        else
            setChoose3Period();
        setComputeMoney();

        unloanSeeker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                MainActivity.setSeg(i);
                MainActivity.loanMoney = MainActivity.MIN_VALUE+(MainActivity.MAX_VALUE-MainActivity.MIN_VALUE)/MainActivity.MONEY_SEG*MainActivity.seg;
                MyDebugUtils.v("the info is "+i+"   "+MainActivity.seg+"   "+MainActivity.loanMoney);
                setComputeMoney();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                unloanSeeker.setProgress(MainActivity.seg*100/MainActivity.MONEY_SEG);
            }
        });
        setComputeMoney();
    }

    @Override
    protected void initUIValue(View view) {

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
                        RxBus.get().post(new MessageEvent());
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
        MainActivity.choosePeriod = 1;
        periodOne.setBackgroundResource(R.drawable.shape_unloan_bg);
        periodThree.setBackgroundResource(R.drawable.shape_period_bg);
    }

    private void setChoose3Period(){
        MainActivity.choosePeriod = 3;
        periodOne.setBackgroundResource(R.drawable.shape_period_bg);
        periodThree.setBackgroundResource(R.drawable.shape_unloan_bg);
    }

    private void setComputeMoney(){
        mChooseMoney.setText(String.valueOf(MainActivity.loanMoney));
        mUnloanFee.setText(String.valueOf(MainActivity.getLastMoney()));
    }
}