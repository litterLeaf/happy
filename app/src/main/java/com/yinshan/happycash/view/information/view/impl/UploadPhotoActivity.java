package com.yinshan.happycash.view.information.view.impl;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseSingleActivity;
import com.yinshan.happycash.view.information.view.impl.support.UploadJobPhotoDialog;

/**
 * Created by huxin on 2018/3/14.
 */

public class UploadPhotoActivity extends BaseSingleActivity{

    UploadJobPhotoDialog mDialog;

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
        mDialog = new UploadJobPhotoDialog(this,R.style.DialogTheme);
        mDialog.show();
    }
}
