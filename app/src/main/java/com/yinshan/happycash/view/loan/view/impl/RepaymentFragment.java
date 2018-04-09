package com.yinshan.happycash.view.loan.view.impl;

import android.widget.ListView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseFragment;
import com.yinshan.happycash.view.loan.view.impl.support.RepaymentAdapter;

import butterknife.BindView;

/**
 * Created by admin on 2018/2/1.
 */

public class RepaymentFragment extends BaseFragment {

    @BindView(R.id.listView)
    ListView listView;
    RepaymentAdapter mAdapter;

    @Override
    protected void initView() {
        mAdapter = new RepaymentAdapter(getActivity());
        listView.setAdapter(mAdapter);
        setListViewHeightBasedOnChildren(listView);
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_repayment;
    }
}
