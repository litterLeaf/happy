package com.yinshan.happycash.view.information.presenter;

import android.app.Activity;
import android.content.Context;
import android.util.Pair;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.network.common.base.RxTransformer;
import com.yinshan.happycash.network.common.network.FileUploadUtil;
import com.yinshan.happycash.utils.SPKeyUtils;
import com.yinshan.happycash.utils.SPUtils;
import com.yinshan.happycash.view.information.view.IUploadPhotoView;
import com.yinshan.happycash.view.information.view.impl.UploadPhotoActivity;
import com.yinshan.happycash.view.information.view.impl.support.FileStatus;
import com.yinshan.happycash.view.information.view.impl.support.FileUploadType;
import com.yinshan.happycash.widget.HappySnackBar;

import java.io.File;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 *        ┃　　　┃   兽神保佑
 *        ┃　　　┃   代码无BUG！
 *        ┃　　　┗━━━┓
 *        ┃　　　　　　　┣┓
 *        ┃　　　　　　　┏┛
 *        ┗┓┓┏━┳┓┏┛
 *           ┃┫┫　┃┫┫
 *           ┗┻┛　┗┻┛
 *
 *    描述：
 *    创建人：     admin
 *    创建时间：2018/9/14
 *
 */
public class UploadPhotoPresenter {

    Context mContext;
    IUploadPhotoView mView;

    public UploadPhotoPresenter(Context context,IUploadPhotoView view){
        mContext = context;
        mView = view;
    }

    public void upload(File myImage, final FileUploadType fileUploadType){
        mView.showLoadingDialog();
        FileUploadUtil.uploadPhotoFile(
                fileUploadType,
                myImage,
                TokenManager.getInstance().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Pair<Call<ResponseBody>, Response<ResponseBody>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                      mView.dismissLoadingDialog();
                      mView.uploadFailed(e,fileUploadType);
                    }

                    @Override
                    public void onNext(Pair<Call<ResponseBody>, Response<ResponseBody>> callResponsePair) {
                        mView.dismissLoadingDialog();
                        mView.uploadPhoto(callResponsePair,fileUploadType);
                    }
                });
    }
}
