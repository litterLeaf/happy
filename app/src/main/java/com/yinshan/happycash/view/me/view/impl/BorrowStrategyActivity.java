package com.yinshan.happycash.view.me.view.impl;

import android.support.v4.content.ContextCompat;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseSingleActivity;

/**
 * Created by huxin on 2018/3/30.
 */

public class BorrowStrategyActivity extends BaseSingleActivity{

    @Override
    protected String bindTitle() {
        return getResources().getString(R.string.help_center);
    }

    @Override
    protected int bindDownLayout() {
        return R.layout.activity_borrow_strategy;
    }

    @Override
    protected void secondInit() {
        lowestBg.setBackgroundColor(ContextCompat.getColor(this,R.color.white));
    }
}
