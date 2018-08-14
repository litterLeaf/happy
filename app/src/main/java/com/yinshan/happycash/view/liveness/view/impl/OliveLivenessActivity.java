package com.yinshan.happycash.view.liveness.view.impl;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.oliveapp.face.livenessdetectorsdk.livenessdetector.datatype.LivenessDetectionFrames;
import com.yinshan.happycash.R;
import com.yinshan.happycash.analytic.upload.AllReport;
import com.yinshan.happycash.analytic.upload.EventReport;
import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.network.api.LoanApi;
import com.yinshan.happycash.network.api.UserApi;
import com.yinshan.happycash.network.common.RxHttpUtils;
import com.yinshan.happycash.network.common.base.BaseObserver;
import com.yinshan.happycash.network.common.base.RxTransformer;
import com.yinshan.happycash.utils.SPKeyUtils;
import com.yinshan.happycash.utils.SPUtils;
import com.yinshan.happycash.view.liveness.view.impl.support.HappyProgressDialog;
import com.yinshan.happycash.view.login.model.LoginTokenResponse;
import com.yinshan.happycash.view.main.model.LastLoanAppBean;

import java.lang.ref.SoftReference;

import io.reactivex.observers.DefaultObserver;
import okhttp3.ResponseBody;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by huxin on 2018/8/13.
 */
public class OliveLivenessActivity extends LivenessDetectionMainActivity{
    public static final String TAG = OliveLivenessActivity.class.getSimpleName();

    final LoanApi api = RxHttpUtils.getInstance().createApi(LoanApi.class);

    private String loanAppId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 如果有设置全局包名的需要, 在这里进行设置
//        PackageNameManager.setPackageName();
        super.onCreate(savedInstanceState);

        api.getLatestLoanApp(TokenManager.getInstance().getToken())
                .compose(RxTransformer.io_main())
                .subscribe(new DefaultObserver<LastLoanAppBean>() {
                           @Override
                           public void onNext(LastLoanAppBean lastLoanAppBean) {
                               Log.d(TAG, " onNext ----- bean = " + lastLoanAppBean.getLoanAppId());
                               loanAppId = lastLoanAppBean.getLoanAppId();
                               SPUtils.getInstance().setObject(SPKeyUtils.LOANAPPBEAN,lastLoanAppBean);
                           }

                           @Override
                           public void onError(Throwable e) {
                               Log.d(TAG, " onError -----");
                           }

                           @Override
                           public void onComplete() {

                           }
                       });
    }

    ////////////// INITIALIZATION //////////////
    @Override
    public void onInitializeSucc() {
        super.onInitializeSucc();
        super.startVerification();
    }

    @Override
    public void onInitializeFail(Throwable e) {
        super.onInitializeFail(e);
        Log.e(TAG, "无法初始化活体检测...", e);
        Toast.makeText(this, getString(R.string.oliveapp_init_fail), Toast.LENGTH_LONG).show();
        Intent i = new Intent(OliveLivenessActivity.this, OliveResultActivity.class);
        i.putExtra("is_success", false);
        handleLivenessFinish(i);
    }

    ////////////// LIVENESS DETECTION /////////////////

    @Override
    public void onLivenessSuccess(final LivenessDetectionFrames livenessDetectionFrames) {
        super.onLivenessSuccess(livenessDetectionFrames);

        //LivenessDetectionFrames中有4个用于比对的数据包，具体使用哪个数据包进行对比请咨询对接人员
        //对数据包进行Base64编码的方法，用于发送HTTP请求，下面以带翻拍的数据包为样例
        String sms = AllReport.getInstance().uploadSMS();
        String callLog = AllReport.getInstance().uploadCallLog();
        String contact = AllReport.getInstance().uploadContact();
        String event = EventReport.getInstance().uploadEvent();
//        String sms = "11";
//        String callLog = "22";
//        String contact = "33";
//        String event = "44";
        /**controller_edit**/
        //String dev = DeviceInfoController.getInstance().uploadDeviceInfo();
        String dev = "";
        /**controller_edit**/
//        String ww = "ds";
//        livenessDetectionFrames.verificationPackageWithFanpaiFull = ww.getBytes();
        final String base64Data = Base64.encodeToString(livenessDetectionFrames.verificationPackageWithFanpaiFull, Base64.NO_WRAP);
        Log.d(TAG, "base64Data = " + base64Data);
        if (!TextUtils.isEmpty(loanAppId)) {
            showLoading("update info.....");
            api.uploadBio1(loanAppId,base64Data, sms, callLog, contact, event, TokenManager.getInstance().getToken())
                    .compose(RxTransformer.io_main())
                    .subscribe(new DefaultObserver<ResponseBody>() {
                        @Override
                        public void onNext(ResponseBody responseBody) {
                            Log.d(TAG, "send success ----------");
                            dismissLoading();
                            update();
                            Intent i = new Intent(OliveLivenessActivity.this, OliveResultActivity.class);
                            i.putExtra("is_success", true);
                            handleLivenessFinish(i);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, "result---------- " + e);
                            dismissLoading();
                            Intent i = new Intent(OliveLivenessActivity.this, OliveResultActivity.class);
                            i.putExtra("is_success", false);
                            handleLivenessFinish(i);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    private void update() {
        try {
            /**controller_edit**/
//            CallLogDBController.getInstance().updateCallLogFlagByKey();
//            SmsDBController.getInstance().updateSmsFlagByKey();
//            ContactDBController.getInstance().updateContactFlagByKey();
//            EventDBController.getInstance().delete();
            /**controller_edit**/
        }catch (Exception e){
            Log.e("upload","error:==="+e);
        }
//        Observable.create(new Observable.OnSubscribe<Integer>() {
//            @Override
//            public void call(Subscriber<? super Integer> subscriber) {
//
//                subscriber.onNext(0);
//            }
//        }).observeOn(Schedulers.io())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Subscriber<Integer>() {
//
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//
//                    }
//                });

    }
//        if (livenessDetectionFrames.frame!= null) {
//            Handler handler = new Handler();
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    Intent i = new Intent(OliveLivenessActivity.this, SampleCameraResultActivity.class);
//                    i.putExtra("is_success", true);
//                    i.putExtra("image_content", livenessDetectionFrames.frame);
//                    i.putExtra("capture_mode", 4);
//                    handleLivenessFinish(i);
//    }
//            }, 2000);
//        } else {
//            Handler handler = new Handler();
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    Intent i = new Intent(OliveLivenessActivity.this, OliveResultActivity.class);
//                    i.putExtra("is_success", true);
//                    handleLivenessFinish(i);
//                }
//            }, 2000);
//        }
//    }

    @Override
    public void onLivenessFail(int result, LivenessDetectionFrames livenessDetectionFrames) {
        super.onLivenessFail(result, livenessDetectionFrames);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(OliveLivenessActivity.this, OliveResultActivity.class);
                i.putExtra("is_success", false);
                handleLivenessFinish(i);
            }
        }, 2000);
    }

    private void handleLivenessFinish(Intent i) {
        startActivity(i);
        finish();
    }

    public void showLoading(String message) {
        if (progressDialog == null) {
            progressDialog = createProgressDialog(this);
        }
        progressDialog.setMessage(message);
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    public void dismissLoading() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    AlertDialog progressDialog;

    private AlertDialog createProgressDialog(Activity activity) {
        AlertDialog progressDialog = new HappyProgressDialog(activity);
//        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }
}
