package com.yinshan.happycash.view.loan.view.impl;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseFragment;
import com.yinshan.happycash.view.loan.view.impl.support.ApplyAdapter;
import com.yinshan.happycash.widget.pullrefresh.MyRefreshHeader;
import com.yinshan.happycash.widget.pullrefresh.RefreshLayout;

import butterknife.BindView;

/**
 * Created by huxin on 2018/4/3.
 * 进度界面
 */

public class ApplyFragment extends BaseFragment{

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.progressView)
    View progressView;
    @BindView(R.id.progressList)
    ListView progressList;
    ApplyAdapter mAdapter;

    @Override
    protected void initView() {
        refreshLayout.setRefreshListener(()-> {
            refreshLayout.refreshComplete();
        });
        MyRefreshHeader header = new MyRefreshHeader(getActivity());
        refreshLayout.setRefreshHeader(header);

        mAdapter = new ApplyAdapter(getActivity());
        progressList.setAdapter(mAdapter);
        setListViewHeightBasedOnChildren(progressList);
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_apply;
    }

}
