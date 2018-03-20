package com.yinshan.happycash.view.me.view.impl;

import android.widget.ListView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseSingleActivity;
import com.yinshan.happycash.view.me.view.impl.support.LoanDetailAdapter;

import butterknife.BindView;

/**
 * Created by huxin on 2018/3/16.
 */

public class LoanDetailActivity extends BaseSingleActivity{

    @BindView(R.id.listView)
    ListView mListView;
    LoanDetailAdapter mAdapter;

    @Override
    protected String bindTitle() {
        return getResources().getString(R.string.my_loan);
    }

    @Override
    protected int bindDownLayout() {
        return R.layout.activity_loan_detail;
    }

    @Override
    protected void secondInit() {
        lowestBg.setBackgroundColor(getResources().getColor(R.color.app_yellow));
        mAdapter = new LoanDetailAdapter();
        mListView.setAdapter(mAdapter);
        setListViewHeightBasedOnChildren(mListView);
    }
}
