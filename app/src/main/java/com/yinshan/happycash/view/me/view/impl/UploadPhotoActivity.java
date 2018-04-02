package com.yinshan.happycash.view.me.view.impl;

import android.os.Bundle;
import android.view.View;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseActivity;
import com.yinshan.happycash.framework.BaseSingleActivity;

/**
 * Created by huxin on 2018/3/14.
 */

public class UploadPhotoActivity extends BaseSingleActivity{

    @Override
    protected String bindTitle() {
        return getResources().getString(R.string.upload_photo);
    }

    @Override
    protected int bindDownLayout() {
        return R.layout.activity_upload_photo;
    }

    @Override
    protected void secondInit() {

    }
}
