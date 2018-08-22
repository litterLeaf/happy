package com.yinshan.happycash.widget.camera;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseActivity;
import com.yinshan.happycash.support.takepicture.CameraManager;
import com.yinshan.happycash.support.takepicture.CameraView;
import com.yinshan.happycash.support.takepicture.PictureData;
import com.yinshan.happycash.support.takepicture.SquareCameraContainer;
import com.yinshan.happycash.support.takepicture.bean.PhotoInfo;
import com.yinshan.happycash.utils.SPKeyUtils;
import com.yinshan.happycash.utils.Util;
import com.yinshan.happycash.view.information.view.impl.support.FileUploadType;
import com.yinshan.happycash.view.main.view.impl.MainActivity;
import com.yinshan.happycash.widget.HappySnackBar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TakePhotoActivity extends BaseActivity {

    /**
     *takepicture  换过控件版的
     */
    RelativeLayout relativeLayout;
    private String ACTION = "picture";// 广播的标识
    private ImageButton btnCapture = null;
    Button mbtnCancel;
    CameraView cameraView;
    FileUploadType mFileUploadType;
    private int photoType;
    private CameraManager mCameraManager;
    private SquareCameraContainer mCameraContainer;
    protected static final int  REQUEST_STORAGE_PERMISSION = 100010;
    private int mFinishCount;

    public void onTaken(final byte[] data) {
        showLoadingDialog();
        Observable.create(new Observable.OnSubscribe<PhotoInfo>(){
            @Override
            public void call(Subscriber<? super PhotoInfo> subscriber) {
                BitmapFactory.Options opts = new BitmapFactory.Options();
                opts.inPreferredConfig = Bitmap.Config.RGB_565;
                opts.inSampleSize = 2;
                opts.inTempStorage = new byte[16*1024];
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length,opts);
                Bitmap cameraBitmap;
                if (bitmap.getWidth() < bitmap.getHeight()) {
                    cameraBitmap = rotateBitmap(bitmap);
                    bitmap.recycle();
                    bitmap = null;
                    System.gc();
                } else {
                    cameraBitmap = bitmap;
                }

                File storagePath;


                if (TextUtils.equals(Environment.MEDIA_MOUNTED, Environment.getExternalStorageState())) {
                    storagePath = new File(Environment.getExternalStorageDirectory() + Util.cacheDir);
                    if (!storagePath.exists()) {
                        storagePath.mkdirs();
                    }
                } else {

                    storagePath = getCacheDir();
                }

                File myImage;
                if(photoType==1){
                     myImage = new File(storagePath, Util.ktpFile);
                }else if(photoType==2){
                     myImage = new File(storagePath,
                             Util.jobFile);
                }else if(photoType==3){
                    myImage = new File(storagePath,
                            Util.buildUpFile);
                }else{
                    myImage = new File(storagePath,
                            Util.otherFile);
                }

                try {
                    FileOutputStream out = new FileOutputStream(myImage);
                    cameraBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                    cameraBitmap.recycle();
                    cameraBitmap = null;
                    System.gc();
                    out.flush();
                    out.close();
                } catch (FileNotFoundException e) {
                    dismissLoadingDialog();
                    Log.d("In Saving File", e + "");
                    subscriber.onError(e);
                } catch (IOException e) {
                    dismissLoadingDialog();
                    Log.d("In Saving File", e + "");
                    subscriber.onError(e);
                }
                PhotoInfo photoinfo = new PhotoInfo();
                photoinfo.photoType = photoType;
                photoinfo.mFile = myImage;
                subscriber.onNext(photoinfo);

            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<PhotoInfo>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
//                         ToastManager.showToast(getString(R.string.show_generate_wrong));
                        HappySnackBar.showSnackBar(mbtnCancel,R.string.show_generate_wrong, SPKeyUtils.SNACKBAR_TYPE_ERROR);
                        dismissLoadingDialog();
                        finish();
                    }
                    @Override
                    public void onNext(PhotoInfo photoInfo) {
                        RxBus.get().post(photoInfo);
                        dismissLoadingDialog();
                        finish();
                    }
                });
    }

    //旋转图片-90度
    @NonNull
    private Bitmap rotateBitmap(Bitmap bitmap) {
//        return rotateBitmap(bitmap, 0 - 90);
        return  adjustPhotoRotation(bitmap,90);
    }

   private Bitmap adjustPhotoRotation(Bitmap bm, final int orientationDegree) {

        Matrix m = new Matrix();
        m.setRotate(orientationDegree, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
        float targetX, targetY;
        if (orientationDegree == 90) {
            targetX = bm.getHeight();
            targetY = 0;
        } else {
            targetX = bm.getHeight();
            targetY = bm.getWidth();
        }

        final float[] values = new float[9];
        m.getValues(values);

        float x1 = values[Matrix.MTRANS_X];
        float y1 = values[Matrix.MTRANS_Y];

        m.postTranslate(targetX - x1, targetY - y1);

        Bitmap bm1 = Bitmap.createBitmap(bm.getHeight(), bm.getWidth(), Bitmap.Config.ARGB_8888);

        Paint paint = new Paint();
        Canvas canvas = new Canvas(bm1);
        canvas.drawBitmap(bm, m, paint);

        return bm1;
    }
//    @NonNull
//    private Bitmap rotateBitmap(Bitmap bitmap, float degree) {
//        Bitmap cameraBitmap = Bitmap.createBitmap(bitmap.getHeight(), bitmap.getWidth(), Bitmap.Config.RGB_565);
//        Canvas canvas = new Canvas(cameraBitmap);
//        Matrix matrix = new Matrix();
//        matrix.setTranslate(bitmap.getHeight() / 2 - bitmap.getWidth() / 2, bitmap.getWidth() / 2 - bitmap.getHeight() / 2);
//        matrix.postRotate(degree, bitmap.getHeight() / 2, bitmap.getWidth() / 2);
//
//        canvas.drawBitmap(bitmap, matrix, new Paint());
//        return cameraBitmap;
//    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPermission();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        cameraView.stop();
        if (mCameraContainer != null) {
            mCameraContainer.onStop();
        }
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        RxBus.get().register(this);
        Intent intent = getIntent();
        photoType = intent.getIntExtra(SPKeyUtils.PHOTO_TYPE, 1);
        //mFileUploadType = mIsKTP ? FileUploadType.KTP_PHOTO : FileUploadType.EMPLOYMENT_PHOTO;
        relativeLayout = (RelativeLayout) findViewById(R.id.containerImg);

        mCameraContainer = (SquareCameraContainer) findViewById(R.id.cameraContainer);
        mCameraContainer.bindActivity(this);

        mCameraManager = CameraManager.getInstance(this);

        mbtnCancel = (Button) findViewById(R.id.button_cancel);
        mbtnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCameraContainer != null) {
                    mCameraContainer.onStop();
                }
                finish();
            }
        });

        btnCapture = (ImageButton) findViewById(R.id.button_shoot);
        btnCapture.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCapture.setClickable(false);
                showLoadingDialog();
                //showLoadingDialog(getString(R.string.loading_storing_img));
                mCameraContainer.takePicture();

            }
        });
    }

    @Override
    protected int bindLayout() {
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return  R.layout.activity_take_photo_new;
    }

    @Override
    protected void secondLayout() {

    }

    @Override
    protected void secondInit() {

    }

    @Override
    protected void onDestroy() {
        RxBus.get().unregister(this);
        mCameraManager.releaseActivityCamera();
        super.onDestroy();
    }

    private final static int PERMISSION_CODE = 110;

    private void checkPermission() {
        final List<String> permissionsList = new ArrayList<>();
        final List<String> permissionsNeeded = new ArrayList<>();
        boolean hasPermission = MainActivity.hasSelfPermission(TakePhotoActivity.this, Manifest.permission.CAMERA);
        if (!hasPermission) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(TakePhotoActivity.this, Manifest.permission.CAMERA)) {
                permissionsNeeded.add(getString(R.string.CAMERA));
            }
            permissionsList.add(Manifest.permission.CAMERA);
        } else {
            if (mCameraContainer != null) {
                mCameraContainer.onStart();
            }
        }

        hasPermission = MainActivity.hasSelfPermission(TakePhotoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (!hasPermission) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(TakePhotoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
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
                        ActivityCompat.requestPermissions(TakePhotoActivity.this,
                                permissionsList.toArray(new String[permissionsList.size()]),
                                PERMISSION_CODE);
                    }
                });
            } else {
                ActivityCompat.requestPermissions(TakePhotoActivity.this,
                        permissionsList.toArray(new String[permissionsList.size()]),
                        PERMISSION_CODE);
            }
        }
    }

    private void showMessageOK(String message, DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(TakePhotoActivity.this)
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
                            HappySnackBar.showSnackBar(mbtnCancel,R.string.CAMERA_INVALID, SPKeyUtils.SNACKBAR_TYPE_WORN);
                            finish();
                            return;
                        }

                        if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permission)) {
                            HappySnackBar.showSnackBar(mbtnCancel,R.string.STORAGE_INVALID, SPKeyUtils.SNACKBAR_TYPE_WORN);
                            finish();
                            return;
                        }
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }

//    /**
//     * 对一些参数重置
//     */
//    public void rest() {
//        mFinishCount = 2;
//    }
    /**
     * 照相按钮点击
     */
    public void onTakePhotoClicked(View view) {
        mCameraContainer.takePicture();
    }

    @Subscribe
    public void getPictureData(PictureData event) {
       if(event!=null&&event.getData().length>0){
           onTaken(event.getData());
       }
    }

}
