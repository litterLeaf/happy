package com.yinshan.happycash.view.information.presenter;

import android.content.Context;
import android.util.Pair;

import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.network.common.base.RxTransformer;
import com.yinshan.happycash.network.common.network.FileUploadUtil;
import com.yinshan.happycash.view.information.view.IUploadPhotoView;
import com.yinshan.happycash.view.information.view.impl.support.FileUploadType;

import java.io.File;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by huxin on 2018/4/18.
 */

public class UploadPhotoPresenter {

    Context mContext;
    IUploadPhotoView mView;

    public UploadPhotoPresenter(Context context,IUploadPhotoView view){
        mContext = context;
        mView = view;
    }

    public void upload(File myImage, final FileUploadType fileUploadType){

    }
}
