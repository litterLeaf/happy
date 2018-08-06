package com.yinshan.happycash.view.loan.view.impl;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseFragment;
import com.yinshan.happycash.framework.MessageEvent;
import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.view.login.LoginActivity;
import com.yinshan.happycash.view.main.MainActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


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

                    EventBus.getDefault().post(new MessageEvent());

                }else {
                    mStartActivity( LoginActivity.class);
                }
                break;
        }
    }
}