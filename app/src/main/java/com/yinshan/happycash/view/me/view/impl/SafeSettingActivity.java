package com.yinshan.happycash.view.me.view.impl;

import android.support.v4.content.ContextCompat;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseSingleNoScrollActivity;

/**
 * Created by huxin on 2018/3/20.
 */

public class SafeSettingActivity extends BaseSingleNoScrollActivity{

    @Override
    protected String bindTitle() {
        return getResources().getString(R.string.safe_setting);
    }

    @Override
    protected int bindDownLayout() {
        return R.layout.activity_safe_setting;
    }

    @Override
    protected void secondInit() {
    }
}
