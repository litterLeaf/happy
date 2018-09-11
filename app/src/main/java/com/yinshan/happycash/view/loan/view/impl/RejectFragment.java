package com.yinshan.happycash.view.loan.view.impl;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseFragment;
import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.utils.SPKeyUtils;
import com.yinshan.happycash.utils.SPUtils;
import com.yinshan.happycash.view.main.model.LastLoanAppBean;
import com.yinshan.happycash.view.main.view.impl.MainActivity;
import com.yinshan.happycash.view.me.model.LoanDetailBean;
import com.yinshan.happycash.view.me.presenter.LoanDetailPresenter;
import com.yinshan.happycash.view.me.view.ILoanDetailView;

/**
 * Created by admin on 2018/3/20.
 */

public class RejectFragment extends BaseFragment implements ILoanDetailView{

    TextView mDay;
    TextView mHour;
    TextView mMinute;
    TextView mSecond;

    LoanDetailPresenter mDetailPresenter;
    private long reapplyCounterDown;
    private CountDownTimer timer;

    @Override
    protected void initView() {
        mDetailPresenter = new LoanDetailPresenter(getActivity(),this);
        LastLoanAppBean bean = SPUtils.getInstance().getObject(SPKeyUtils.LOANAPPBEAN,LastLoanAppBean.class);
        if(bean!=null&& !TextUtils.isEmpty(bean.getLoanAppId()))
            mDetailPresenter.getDetail(Long.valueOf(bean.getLoanAppId()));
    }

    @Override
    protected void initUIValue(View view) {
        mDay = (TextView)view.findViewById(R.id.day);
        mHour = (TextView)view.findViewById(R.id.hour);
        mMinute = (TextView)view.findViewById(R.id.minute);
        mSecond = (TextView)view.findViewById(R.id.second);
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_reject;
    }

    @Override
    public void showDetail(LoanDetailBean bean) {
        if(bean!=null){
            reapplyCounterDown = bean.getReapplyCounterDown();
            if(reapplyCounterDown == 0){
                refresh();
            }else{
                timeCount(reapplyCounterDown);
            }
        }
    }

    //刷新的方法
    private void refresh() {
        ((MainActivity)getActivity()).updateStatus(TokenManager.getInstance().getToken());
    }

    private void timeCount(long time) {
        if (timer == null) {
            timer = new TimerDown(time, 1000).start();
        } else {
            timer.cancel();
            timer = null;
            timer = new TimerDown(time, 1000).start();
        }
    }

    private void reFreshView(long time) {
        long expiredLeftTimeSecond = time / 1000 + 1;
        int second = (int) (expiredLeftTimeSecond % (3600 * 24) % 3600) % 60 % 60;
        int min = (int) (expiredLeftTimeSecond % (3600 * 24) % 3600) / 60;
        int day = (int) expiredLeftTimeSecond / (3600 * 24);
        int hour = (int) (expiredLeftTimeSecond % (3600 * 24)) / 3600;
        mSecond.setText("" + second);
        mMinute.setText("" + min);
        mHour.setText("" + hour);
        mDay.setText("" + day);
    }

    class TimerDown extends CountDownTimer {
        TimerDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            reFreshView(millisUntilFinished);
        }

        @Override
        public void onFinish() {
            refresh();
        }
    }
}
