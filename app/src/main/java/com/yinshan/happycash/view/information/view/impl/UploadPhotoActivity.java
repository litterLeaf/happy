package com.yinshan.happycash.view.information.view.impl;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yinshan.happycash.R;
import com.yinshan.happycash.application.FieldParams;
import com.yinshan.happycash.framework.BaseSingleActivity;
import com.yinshan.happycash.utils.FileUtil;
import com.yinshan.happycash.utils.SPKeyUtils;
import com.yinshan.happycash.utils.SPUtils;
import com.yinshan.happycash.view.information.view.impl.support.FileStatus;
import com.yinshan.happycash.view.information.view.impl.support.FileUploadType;
import com.yinshan.happycash.view.information.view.impl.support.UploadJobPhotoDialog;
import com.yinshan.happycash.view.information.view.impl.support.UploadKtpPhotoDialog;
import com.yinshan.happycash.widget.camera.TakePhotoActivity;
import com.yinshan.happycash.widget.common.CommonClickListener;

import java.io.File;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by huxin on 2018/3/14.
 */

public class UploadPhotoActivity extends BaseSingleActivity{

    @BindView(R.id.ktpImage)
    ImageView mKtpImage;
    @BindView(R.id.jobImage)
    ImageView mJobImage;
    @BindView(R.id.btnInfoSubmit)
    RelativeLayout mBtnInfoSubmit;

    private File mKTPFile;
    private File mJobFile;
    private HashMap<FileUploadType,FileStatus> mFileStatus;

    UploadKtpPhotoDialog mKtpDialog;
    UploadJobPhotoDialog mJobDialog;

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
        loadAndSetPhoto();
    }

    private void loadAndSetPhoto(){
        File cacheFile = FileUtil.getCacheFile(this);

        File ktpFile = new File(cacheFile,"ktp.jpg");
        if(ktpFile.exists()){
            changeImage(mKtpImage,ktpFile);
            mKTPFile = ktpFile;
            mFileStatus.put(FileUploadType.KTP_PHOTO,FileStatus.FILE_ADDED);
        }

        File jobFile = new File(cacheFile,"job.jpg");
        if(jobFile.exists()){
            changeImage(mJobImage,jobFile);
            mJobFile = jobFile;
            mFileStatus.put(FileUploadType.JOB_PHOTO,FileStatus.FILE_ADDED);
        }

        setButtonClickableState();
    }

    @OnClick({R.id.btnKtp,R.id.btnJob})
    public void onCLick(View view){
        switch (view.getId()){
            case R.id.btnKtp:
                takePhotoKtp();
                break;
            case R.id.btnJob:
                showJobDialog();
                break;
        }
    }

    private void takePhotoKtp() {
        showKtpDialog();
    }

    private void showKtpDialog() {
        if(mKtpDialog ==null)
            mKtpDialog = new UploadKtpPhotoDialog(this, R.style.DialogTheme, new CommonClickListener() {
                @Override
                public void onClick() {
                    changeTo(TakePhotoActivity.class, true);
                }
            });
        mKtpDialog.show();
    }

    private void showJobDialog(){
        if(mJobDialog ==null)
            mJobDialog = new UploadJobPhotoDialog(this,R.style.DialogTheme);
        mJobDialog.show();
    }

    private void changeImage(ImageView image,File file){
        Glide.with(this)
                .load(file)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(image);
    }

    private void setButtonClickableState(){
        if(SPUtils.get(SPKeyUtils.IS_KTP_PHOTO_OK,false)&&SPUtils.get(SPKeyUtils.IS_WORK_PHOTO_OK,false)){
            mBtnInfoSubmit.setEnabled(true);
            mBtnInfoSubmit.setAlpha(1.0f);
        }else{
            mBtnInfoSubmit.setEnabled(false);
            mBtnInfoSubmit.setAlpha(0.3f);
        }
    }

    private void changeTo(Class clazz,boolean isKTP){
        Intent intent = new Intent(this, clazz);
        intent.putExtra(FieldParams.PHOTO_TYPE, isKTP?1:2);
        startActivity(intent);
    }
}
