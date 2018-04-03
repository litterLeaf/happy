package com.yinshan.happycash.view.loan.view.impl;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseFragment;
import com.yinshan.happycash.widget.pullrefresh.MyRefreshHeader;
import com.yinshan.happycash.widget.pullrefresh.RefreshLayout;

import butterknife.BindView;

/**
 * Created by huxin on 2018/4/3.
 */

public class ApplyFragment extends BaseFragment{

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.progressView)
    View progressView;
    @BindView(R.id.progressList)
    ListView progressList;

    @Override
    protected void initView() {
        refreshLayout.setRefreshListener(new RefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        MyRefreshHeader header = new MyRefreshHeader(getActivity());
        refreshLayout.setRefreshHeader(header);
        //refreshLayout.autoRefresh();

//        ViewGroup.LayoutParams layoutParams = progressView.getLayoutParams();
//        progressView.setLayoutParams(new ViewGroup.LayoutParams(299,4));
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_apply;
    }

    private void refresh(){
        refreshLayout.refreshComplete();
    }
}
