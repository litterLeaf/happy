package com.yinshan.happycash.view.loan.view.impl;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseActivity;
import com.yinshan.happycash.utils.PaymentMethodManager;
import com.yinshan.happycash.view.loan.view.impl.support.BankPaymentAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by huxin on 2018/8/17.
 */
public class BankPaymentActivity extends BaseActivity{

    TextView mMoney;
    TextView mVa;

    ListView mListStep;

    BankPaymentAdapter mAdapter;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_bank_payment;
    }

    @Override
    protected void secondLayout() {

    }

    @Override
    protected void secondInit() {
        init();
    }



    private void init(){
        initUI();
        if(RepaymentFragment.depositRB!=null) {
            mMoney.setText(String.valueOf(RepaymentFragment.depositRB.getAmount()));
            mVa.setText(RepaymentFragment.depositRB.getPaymentCode());
        }

        mAdapter = new BankPaymentAdapter(this, PaymentMethodManager.getPaymentStepsLayout(RepaymentFragment.depositRB));
        mListStep.setAdapter(mAdapter);
        setListViewHeightBasedOnChildren(mListStep);
    }

    @OnClick({R.id.viewATM,R.id.viewOnline,R.id.viewBanking})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.viewATM:
//                mAdapter.setNewArray();
//                setListViewHeightBasedOnChildren(mListStep);
                break;
            case R.id.viewOnline:
                break;
            case R.id.viewBanking:
                break;
        }
    }

    private void initUI(){
        mMoney = (TextView)findViewById(R.id.money);
        mVa = (TextView)findViewById(R.id.va);
        mListStep = (ListView)findViewById(R.id.listStep);
    }
}
