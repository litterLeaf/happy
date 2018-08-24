package com.yinshan.happycash.view.loan.view.impl;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseActivity;
import com.yinshan.happycash.utils.PaymentMethodManager;
import com.yinshan.happycash.view.loan.view.impl.support.BankPaymentAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by huxin on 2018/8/17.
 */
public class BankPaymentActivity extends BaseActivity{

    @BindView(R.id.money)
    TextView mMoney;
    @BindView(R.id.va)
    TextView mVa;

    @BindView(R.id.listStep)
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
        if(RepaymentFragment.depositRB!=null) {
            mMoney.setText(String.valueOf(RepaymentFragment.depositRB.getPrice()));
            mVa.setText(RepaymentFragment.depositRB.getPaymentCode());
        }

        mAdapter = new BankPaymentAdapter(this, PaymentMethodManager.getPaymentStepsLayout(RepaymentFragment.depositRB));
        mListStep.setAdapter(mAdapter);
        setListViewHeightBasedOnChildren(mListStep);
    }
}
