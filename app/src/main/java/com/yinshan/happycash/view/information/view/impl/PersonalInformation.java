package com.yinshan.happycash.view.information.view.impl;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseSingleActivity;

/**
 * Created by admin on 2018/4/2.
 */

public class PersonalInformation extends BaseSingleActivity{
    @Override
    protected String bindTitle() {
        return getString(R.string.title_personal_infor);
    }

    @Override
    protected int bindDownLayout() {
        return R.layout.activity_information;
    }

    @Override
    protected void secondInit() {

    }
}
