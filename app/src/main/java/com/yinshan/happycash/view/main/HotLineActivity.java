package com.yinshan.happycash.view.main;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseSingleNoScrollActivity;

/**
 * Created by admin on 2018/3/23.
 */

public class HotLineActivity extends BaseSingleNoScrollActivity {
    @Override
    protected String bindTitle() {
        return "HotLine";
    }

    @Override
    protected int bindDownLayout() {
        return R.layout.activity_loan_list;
    }

    @Override
    protected void secondInit() {

    }
}
