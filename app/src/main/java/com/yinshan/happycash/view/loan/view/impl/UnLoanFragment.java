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
 * ┃　　　┃   神兽保佑
 * ┃　　　┃   代码无BUG！
 * ┃　　　┗━━━┓
 * ┃　　　　　　　┣┓
 * ┃　　　　　　　┏┛
 * ┗┓┓┏━┳┓┏┛
 * ┃┫┫　┃┫┫
 * ┗┻┛　┗┻┛
 *
 * @author admin
 *         on 2018/1/31
 */

public class UnLoanFragment extends BaseFragment {

    public static final int MIN_VALUE = 500000;
    public static final int MAX_VALUE = 1500000;


    @BindView(R.id.unloan_seeker)
    SeekBar unloanSeeker;
    @BindView(R.id.bt_period_1_unloan)
    Button periodOne;
    @BindView(R.id.bt_period_3_unloan)
    Button periodThree;
    @BindView(R.id.unloan_fee)
    TextView unloanFee;
    @BindView(R.id.unloan_go_information)
    Button goInformation;

    @Override
    protected void initView() {

    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_main_unloan;
    }


    @OnClick({R.id.bt_period_1_unloan, R.id.bt_period_3_unloan, R.id.unloan_go_information})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_period_1_unloan:

                periodThree.setBackground(getResources().getDrawable(R.drawable.shape_period_bg));
                periodOne.setBackground(getResources().getDrawable(R.drawable.shape_unloan_bg));
                break;
            case R.id.bt_period_3_unloan:
                periodOne.setBackground(getResources().getDrawable(R.drawable.shape_period_bg));
                periodThree.setBackground(getResources().getDrawable(R.drawable.shape_unloan_bg));
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
                        SPUtils.getInstance().setImie(AndroidUtils.getIMIE(getActivity()));
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }

    }
}