package com.yinshan.happycash.view.main.presenter;

import android.content.Context;

import com.yinshan.happycash.application.AppException;
import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.network.api.LoanApi;
import com.yinshan.happycash.network.api.VersionApi;
import com.yinshan.happycash.network.common.RxHttpUtils;
import com.yinshan.happycash.network.common.base.ApiException;
import com.yinshan.happycash.network.common.base.BaseObserver;
import com.yinshan.happycash.network.common.base.RxTransformer;
import com.yinshan.happycash.view.main.model.ProfileBean;
import com.yinshan.happycash.view.main.view.IVersionView;

import java.lang.ref.SoftReference;

/**
 * Created by huxin on 2018/9/6.
 */
public class VersionPresenter {

    Context mContext;
    IVersionView mView;
    VersionApi mApi;

    public VersionPresenter(Context context,IVersionView view){
        mContext = context;
        mView = view;
        mApi =  RxHttpUtils.getInstance().createApi(VersionApi.class);
    }

    public void getVersionInfo(String version){
        mApi.getVersionInfo(version, TokenManager.getInstance().getToken())
                .compose(RxTransformer.io_main())
                .subscribe(new BaseObserver<ProfileBean>(new SoftReference(mContext)){
                    @Override
                    public void onNext(ProfileBean value) {
                        super.onNext(value);
                        mView.getVersionOk(value);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        mView.getVersionFail();
                        AppException.handleException(mContext,ex.getCode(),ex.getMessage());
                    }
                });

    }
}
