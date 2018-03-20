package com.yinshan.happycash.view.me.view.impl;

import android.widget.ListView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseActivity;
import com.yinshan.happycash.framework.BaseSingleActivity;
import com.yinshan.happycash.framework.BaseSingleNoScrollActivity;
import com.yinshan.happycash.view.me.view.impl.support.HelpCenterAdapter;

import butterknife.BindView;

/**
 * Created by huxin on 2018/3/19.
 */

public class HelpCenterActivity extends BaseSingleNoScrollActivity{

    @BindView(R.id.listView)
    ListView mListView;
    HelpCenterAdapter mAdapter;

    @Override
    protected String bindTitle() {
        return getResources().getString(R.string.help_center);
    }

    @Override
    protected int bindDownLayout() {
        return R.layout.activity_help_center;
    }

    @Override
    protected void secondInit() {
        lowestBg.setBackgroundColor(getResources().getColor(R.color.app_white));
        mAdapter = new HelpCenterAdapter();
        mListView.setAdapter(mAdapter);
        setListViewHeightBasedOnChildren(mListView);
    }
}
