package com.yinshan.happycash.view.loan.view.impl;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseActivity;
import com.yinshan.happycash.view.loan.view.impl.support.BankPaymentAdapter;

import butterknife.BindView;

/**
 * Created by huxin on 2018/8/17.
 */
public class BankPaymentActivity extends BaseActivity{

    @BindView(R.id.listStep)
    ListView mListStep;

    BankPaymentAdapter mAdapter;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        init();
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

    }

    private void init(){
        mAdapter = new BankPaymentAdapter(this);
        mListStep.setAdapter(mAdapter);

        setListViewHeightBasedOnChildren(mListStep);
    }
}
