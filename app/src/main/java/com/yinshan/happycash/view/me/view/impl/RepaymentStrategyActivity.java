package com.yinshan.happycash.view.me.view.impl;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseSingleActivity;

/**
 * Created by huxin on 2018/3/30.
 */

public class RepaymentStrategyActivity extends BaseSingleActivity{

    @Override
    protected String bindTitle() {
        return getResources().getString(R.string.how_to_return_the_loan);
    }

    @Override
    protected int bindDownLayout() {
        return R.layout.activity_repayment_strategy;
    }

    @Override
    protected void secondInit() {

    }
}
