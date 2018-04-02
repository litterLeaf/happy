package com.yinshan.happycash.view.information.view.impl;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseSingleActivity;

/**
 * Created by huxin on 2018/4/2.
 */

public class ContactActivity extends BaseSingleActivity{

    @Override
    protected String bindTitle() {
        return getResources().getString(R.string.contact_info);
    }

    @Override
    protected int bindDownLayout() {
        return R.layout.activity_contact;
    }

    @Override
    protected void secondInit() {

    }
}
