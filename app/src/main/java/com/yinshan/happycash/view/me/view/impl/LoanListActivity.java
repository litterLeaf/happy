package com.yinshan.happycash.view.me.view.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseActivity;
import com.yinshan.happycash.framework.BaseSingleActivity;
import com.yinshan.happycash.framework.BaseSingleNoScrollActivity;
import com.yinshan.happycash.view.me.view.impl.support.LoanAdapter;

import butterknife.BindView;

/**
 * Created by huxin on 2018/3/14.
 */

public class LoanListActivity extends BaseSingleNoScrollActivity{

    @BindView(R.id.listView)
    ListView mListView;
    LoanAdapter mLoanAdapter;

    @Override
    protected String bindTitle() {
        return getResources().getString(R.string.borrowed_details);
    }

    @Override
    protected int bindDownLayout() {
        return R.layout.activity_loan_list;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void secondLayout() {
        mLoanAdapter = new LoanAdapter(this);
        mListView.setAdapter(mLoanAdapter);
    }
}
