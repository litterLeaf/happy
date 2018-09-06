package com.yinshan.happycash.view.me.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.config.inner.AppInnerConfig;
import com.yinshan.happycash.framework.BaseActivity;
import com.yinshan.happycash.framework.BaseSingleActivity;
import com.yinshan.happycash.framework.BaseSingleNoScrollActivity;
import com.yinshan.happycash.view.me.model.LoanItem;
import com.yinshan.happycash.view.me.presenter.LoanListPresenter;
import com.yinshan.happycash.view.me.view.ILoanListView;
import com.yinshan.happycash.view.me.view.impl.support.LoanAdapter;
import com.yinshan.happycash.view.me.view.impl.support.LoanDetailAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by huxin on 2018/3/14.
 */

public class LoanListActivity extends BaseSingleNoScrollActivity implements ILoanListView{

    ListView mListView;
    LoanAdapter mLoanAdapter;

    LoanListPresenter mPresenter;

    @Override
    protected String bindTitle() {
        return getResources().getString(R.string.my_loan);
    }

    @Override
    protected int bindDownLayout() {
        return R.layout.activity_loan_list;
    }

    @Override
    protected void secondInit() {
        mListView = (ListView)findViewById(R.id.listView);
        mLoanAdapter = new LoanAdapter(this);
        mListView.setAdapter(mLoanAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LoanListActivity.this, LoanDetailActivity.class);
                intent.putExtra(AppInnerConfig.LONG,((LoanItem)mLoanAdapter.getItem(position)).getLoanAppId());
                startActivity(intent);
            }
        });
        mPresenter = new LoanListPresenter(this,this);
        mPresenter.getList();
    }

    @Override
    public void showList(List<LoanItem> loanList) {
        mLoanAdapter.addList(loanList);
        mLoanAdapter.notifyDataSetChanged();
    }
}
