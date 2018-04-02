package com.yinshan.happycash.view.information.view.impl;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseSingleActivity;

/**
 * Created by admin on 2018/4/2.
 */

public class JobInformation extends BaseSingleActivity {
    @Override
    protected String bindTitle() {
        return getString(R.string.textview_job_info_title);
    }

    @Override
    protected int bindDownLayout() {
        return R.layout.activity_job;
    }

    @Override
    protected void secondInit() {

    }
}
