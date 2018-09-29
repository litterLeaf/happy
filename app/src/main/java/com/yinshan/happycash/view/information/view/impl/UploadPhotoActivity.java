package com.yinshan.happycash.view.information.view.impl;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseSingleActivity;
import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.network.common.network.FileUploadUtil;
import com.yinshan.happycash.support.takepicture.bean.PhotoInfo;
import com.yinshan.happycash.utils.FileUtil;
import com.yinshan.happycash.utils.SPKeyUtils;
import com.yinshan.happycash.utils.SPUtils;
import com.yinshan.happycash.utils.Util;
import com.yinshan.happycash.view.information.presenter.UploadPhotoPresenter;
import com.yinshan.happycash.view.information.view.IUploadPhotoView;
import com.yinshan.happycash.view.information.view.impl.support.FileStatus;
import com.yinshan.happycash.view.information.view.impl.support.FileUploadType;
import com.yinshan.happycash.view.information.view.impl.support.UploadJobPhotoDialog;
import com.yinshan.happycash.view.information.view.impl.support.UploadKtpPhotoDialog;
import com.yinshan.happycash.view.main.view.impl.MainActivity;
import com.yinshan.happycash.widget.HappySnackBar;
import com.yinshan.happycash.widget.ZQImageViewRoundOval;
import com.yinshan.happycash.widget.camera.TakePhotoActivity;
import com.yinshan.happycash.widget.common.CommonClickListener;
import com.yinshan.happycash.widget.common.ToastManager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by huxin on 2018/3/14.
 */

public class UploadPhotoActivity extends BaseSingleActivity implements IUploadPhotoView,View.OnClickListener{

    ZQImageViewRoundOval mKtpImage;
    ZQImageViewRoundOval mJobImage;
    RelativeLayout mBtnInfoSubmit;

    private File mKTPFile;
    private File mJobFile;
    private HashMap<FileUploadType,FileStatus> mFileStatus;

    UploadKtpPhotoDialog mKtpDialog;
    UploadJobPhotoDialog mJobDialog;
    UploadPhotoPresenter uploadPhotoPresenter;
    private final static int PERMISSION_CODE = 110;
    private RelativeLayout mJobRL;
    private RelativeLayout mKTPRL;
    @Override
    protected void onResume() {
        super.onResume();
        checkPermission();
    }

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
        RxBus.get().register(this);
        uploadPhotoPresenter = new UploadPhotoPresenter(this,this);
        mKtpImage = findViewById(R.id.ktpImage);
        mJobImage = findViewById(R.id.jobImage);
        mBtnInfoSubmit = findViewById(R.id.upload_btnInfoSubmit);
        mKTPRL = findViewById( R.id.upload_btnKtp);
        mJobRL = findViewById( R.id.upload_btnJob);
        mBtnInfoSubmit.setOnClickListener(this);
        mKTPRL.setOnClickListener(this);
        mJobRL.setOnClickListener(this);
        mKtpImage.setType(ZQImageViewRoundOval.TYPE_ROUND);
        mJobImage.setType(ZQImageViewRoundOval.TYPE_ROUND);

        mFileStatus = new HashMap<>(2);

        loadAndSetPhoto();
    }

    private void loadAndSetPhoto(){
        File cacheFile = FileUtil.getCacheFile(this);

        File ktpFile = new File(cacheFile, Util.ktpFile);
        if(ktpFile.exists()){
            changeImage(mKtpImage,ktpFile);
            mKTPFile = ktpFile;
            mFileStatus.put(FileUploadType.KTP_PHOTO,FileStatus.FILE_ADDED);
        }

        File jobFile = new File(cacheFile,Util.jobFile);
        if(jobFile.exists()){
            changeImage(mJobImage,jobFile);
            mJobFile = jobFile;
            mFileStatus.put(FileUploadType.EMPLOYMENT_PHOTO,FileStatus.FILE_ADDED);
        }

        setButtonClickableState();
    }

    @Override
    public void onClick(View view){
        boolean hasPermission1 = MainActivity.hasSelfPermission(UploadPhotoActivity.this, Manifest.permission.CAMERA);
        boolean hasPermission2 = MainActivity.hasSelfPermission(UploadPhotoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        switch (view.getId()){
            case R.id.upload_btnKtp:
                if(hasPermission1&&hasPermission2)
                    takePhotoKtp();
                else
                    checkPermission();
                break;
            case R.id.upload_btnJob:
                if(hasPermission1&&hasPermission2)
                    showJobDialog();
                else
                    checkPermission();

                break;
            case R.id.upload_btnInfoSubmit:
                uploadImages();
                break;
        }
    }

    private void takePhotoKtp() {
        showKtpDialog();
    }

    private void showKtpDialog() {
        if(mKtpDialog ==null)
            mKtpDialog = new UploadKtpPhotoDialog(this, R.style.DialogTheme,
                    ()-> changeTo(TakePhotoActivity.class, true)
            );
        mKtpDialog.show();
    }

    private void showJobDialog(){
        if(mJobDialog ==null)
            mJobDialog = new UploadJobPhotoDialog(this, R.style.DialogTheme,
                    ()-> changeTo(TakePhotoActivity.class, false)
            );
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
        if(SPUtils.get(SPKeyUtils.IS_KTP_PHOTO_OK,true)&&SPUtils.get(SPKeyUtils.IS_WORK_PHOTO_OK,true)){
            mBtnInfoSubmit.setEnabled(true);
            mBtnInfoSubmit.setAlpha(1.0f);
        }else{
            mBtnInfoSubmit.setEnabled(false);
            mBtnInfoSubmit.setAlpha(0.3f);
        }
    }

    private void changeTo(Class clazz,boolean isKTP){
        Intent intent = new Intent(this, clazz);
        intent.putExtra(SPKeyUtils.PHOTO_TYPE, isKTP?1:2);
        startActivity(intent);
    }

    @Subscribe
    public void onPhotoTaken(PhotoInfo photoInfo){
        if(photoInfo.photoType == 1){
            SPUtils.put(SPKeyUtils.IS_KTP_PHOTO_OK,true);
            changeImage(mKtpImage,photoInfo.mFile);
            mKTPFile = photoInfo.mFile;
            mFileStatus.put(FileUploadType.KTP_PHOTO,FileStatus.FILE_ADDED);
            setButtonClickableState();
        }else if(photoInfo.photoType == 2){
            SPUtils.put(SPKeyUtils.IS_WORK_PHOTO_OK,true);
            changeImage(mJobImage,photoInfo.mFile);
            mJobFile = photoInfo.mFile;
            mFileStatus.put(FileUploadType.EMPLOYMENT_PHOTO,FileStatus.FILE_ADDED);
            setButtonClickableState();
        }
    }

    private void uploadImages(){
        boolean isUpload = false;
        if(mFileStatus.get(FileUploadType.KTP_PHOTO)==FileStatus.FILE_ADDED|| mFileStatus.get(FileUploadType.KTP_PHOTO) == FileStatus.UPLOAD_FAILED){
            mFileStatus.put(FileUploadType.KTP_PHOTO, FileStatus.UPLOADING);
            isUpload = true;
            upload(mKTPFile,FileUploadType.KTP_PHOTO);
        }
        if(mFileStatus.get(FileUploadType.EMPLOYMENT_PHOTO)==FileStatus.FILE_ADDED|| mFileStatus.get(FileUploadType.EMPLOYMENT_PHOTO) == FileStatus.UPLOAD_FAILED){
            mFileStatus.put(FileUploadType.EMPLOYMENT_PHOTO, FileStatus.UPLOADING);
            isUpload = true;
            upload(mJobFile,FileUploadType.EMPLOYMENT_PHOTO);
        }
        if(!isUpload)
            ToastManager.showToast(getResources().getString(R.string.had_upload_these_photo_please_re_photo));
        if (mFileStatus.get(FileUploadType.EMPLOYMENT_PHOTO) == FileStatus.DOWNLOADED && mFileStatus.get(FileUploadType.KTP_PHOTO) == FileStatus.DOWNLOADED) {
            dismissLoadingDialog();
            setResult(RESULT_OK);
            finish();
        }
    }

    private void checkPermission() {
        final List<String> permissionsList = new ArrayList<>();
        final List<String> permissionsNeeded = new ArrayList<>();
        boolean hasPermission = MainActivity.hasSelfPermission(UploadPhotoActivity.this, Manifest.permission.CAMERA);
        if (!hasPermission) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(UploadPhotoActivity.this, Manifest.permission.CAMERA)) {
                permissionsNeeded.add(getString(R.string.CAMERA));
            }
            permissionsList.add(Manifest.permission.CAMERA);
        }
        hasPermission = MainActivity.hasSelfPermission(UploadPhotoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (!hasPermission) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(UploadPhotoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissionsNeeded.add(getString(R.string.WRITE_EXTERNAL_STORAGE));
            }
            permissionsList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (permissionsList.size() > 0) {
            if (permissionsNeeded.size() > 0) {
                StringBuilder sb = new StringBuilder(getString(R.string.permissions_rationale));
                sb.append(permissionsNeeded.get(0));
                for (int i = 1; i < permissionsNeeded.size(); i++) {
                    sb.append(",");
                    sb.append(permissionsNeeded.get(i));
                }

                showMessageOK(sb.toString(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(UploadPhotoActivity.this,
                                permissionsList.toArray(new String[permissionsList.size()]),
                                PERMISSION_CODE);
                    }
                });
            } else {
                ActivityCompat.requestPermissions(UploadPhotoActivity.this,
                        permissionsList.toArray(new String[permissionsList.size()]),
                        PERMISSION_CODE);
            }
        }
    }

    private void showMessageOK(String message, DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(UploadPhotoActivity.this)
                .setMessage(message)
                .setPositiveButton(getString(R.string.ok), listener)
                .setCancelable(false)
                .create()
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_CODE:
                for (int i = 0; i < grantResults.length; i++) {
                    String permission = permissions[i];
                    int grantResult = grantResults[i];
                    if (grantResult != PackageManager.PERMISSION_GRANTED) {
                        if (Manifest.permission.CAMERA.equals(permission)) {

                            return;
                        }

                        if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permission)) {

                            return;
                        }
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }


    /**
     * 上传照片的请求
     * @param mKTPFile
     * @param fileUploadType
     */
    private void upload(File mKTPFile, final FileUploadType fileUploadType) {
        uploadPhotoPresenter.upload(mKTPFile,fileUploadType);
    }


    /**
     * 上传照片成功的回调
     * @param callResponsePair
     */
    @Override
    public void uploadPhoto(Pair<Call<ResponseBody>, Response<ResponseBody>> callResponsePair,  FileUploadType fileUploadType) {
        Log.e("uploadPhoto","success");
        mFileStatus.put(fileUploadType, FileStatus.UPLOAD_SUCCESS);
        if ((mFileStatus.get(FileUploadType.EMPLOYMENT_PHOTO) == FileStatus.DOWNLOADED || mFileStatus.get(FileUploadType.EMPLOYMENT_PHOTO) == FileStatus.UPLOAD_SUCCESS)
                && (mFileStatus.get(FileUploadType.KTP_PHOTO) == FileStatus.UPLOAD_SUCCESS || mFileStatus.get(FileUploadType.KTP_PHOTO) == FileStatus.DOWNLOADED)) {
//                            ToastManager.showToast(getResources().getText(R.string.show_upload_sucess).toString());
            HappySnackBar.showSnackBar(mBtnInfoSubmit,R.string.show_upload_sucess,SPKeyUtils.SNACKBAR_TYPE_INTEENT);
            if(fileUploadType==FileUploadType.KTP_PHOTO)
                mFileStatus.put(FileUploadType.KTP_PHOTO, FileStatus.DOWNLOADED);
            else if(fileUploadType==FileUploadType.EMPLOYMENT_PHOTO)
                mFileStatus.put(FileUploadType.EMPLOYMENT_PHOTO, FileStatus.DOWNLOADED);
            SPUtils.put(SPKeyUtils.IS_KTP_PHOTO_OK,false);
            SPUtils.put(SPKeyUtils.IS_WORK_PHOTO_OK,false);
            UploadPhotoActivity.this.setResult(Activity.RESULT_OK);
            finish();
        } else if (
                mFileStatus.get(FileUploadType.EMPLOYMENT_PHOTO) != FileStatus.UPLOADING &&
                        mFileStatus.get(FileUploadType.KTP_PHOTO) != FileStatus.UPLOADING) {
//                            ToastManager.showToast(getResources().getText(R.string.show_upload_failed).toString());
            HappySnackBar.showSnackBar(mBtnInfoSubmit,R.string.show_upload_failed,SPKeyUtils.SNACKBAR_TYPE_ERROR);
        }
    }

    /**
     * /**
     * 上传照片失败的回调
     * @param e
     */
    @Override
    public void uploadFailed(Throwable e,FileUploadType fileUploadType) {
        Log.e("uploadPhoto","failed"+e.getMessage());
        mFileStatus.put(fileUploadType, FileStatus.UPLOAD_FAILED);
        if (mFileStatus.get(FileUploadType.EMPLOYMENT_PHOTO) != FileStatus.UPLOADING &&
                mFileStatus.get(FileUploadType.KTP_PHOTO) != FileStatus.UPLOADING) {
//                            ToastManager.showToast(getResources().getText(R.string.show_upload_failed).toString());
            HappySnackBar.showSnackBar(mBtnInfoSubmit, R.string.show_upload_failed, SPKeyUtils.SNACKBAR_TYPE_ERROR);
        }
    }

    @Override
    protected void onDestroy() {
        RxBus.get().unregister(this);
        super.onDestroy();
    }
}
