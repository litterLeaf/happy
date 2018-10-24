package com.yinshan.happycash.view.information.presenter;

import android.content.Context;

import com.yinshan.happycash.application.AppException;
import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.network.api.RecordApi;
import com.yinshan.happycash.network.common.RxHttpUtils;
import com.yinshan.happycash.network.common.base.ApiException;
import com.yinshan.happycash.network.common.base.BaseObserver;
import com.yinshan.happycash.network.common.base.RxTransformer;
import com.yinshan.happycash.view.information.model.ProgressBean;
import com.yinshan.happycash.view.information.view.IInfoView;

import java.lang.ref.SoftReference;

/**
 * Created by huxin on 2018/4/10.
 */

public class InformationPresenter {

    Context mContext;
    IInfoView mView;

    public InformationPresenter(Context context,IInfoView view){
        mContext = context;
        mView = view;
    }

    public void getProgress(){
        mView.showLoadingDialog();
        RxHttpUtils.getInstance().createApi(RecordApi.class)
                .progress(TokenManager.getInstance().getToken())
                .compose(RxTransformer.io_main())
                .subscribe(new BaseObserver<ProgressBean>(new SoftReference(mContext)){
                    @Override
                    public void onNext(ProgressBean bean) {
                        super.onNext(bean);
                        mView.dismissLoadingDialog();
                        mView.showProgress(bean);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        mView.dismissLoadingDialog();
                        AppException.handleException(mContext,ex.getCode(),ex.getMessage());
                    }
                });
    }
}
