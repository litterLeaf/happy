package com.yinshan.happycash.view.information.presenter;

import android.content.Context;

import com.yinshan.happycash.application.AppException;
import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.network.api.RecordApi;
import com.yinshan.happycash.network.common.RxHttpUtils;
import com.yinshan.happycash.network.common.base.ApiException;
import com.yinshan.happycash.network.common.base.BaseObserver;
import com.yinshan.happycash.network.common.base.RxTransformer;
import com.yinshan.happycash.view.information.view.IBpjsView;

import java.lang.ref.SoftReference;

import okhttp3.ResponseBody;

/**
 * Created by huxin on 2018/9/3.
 */
public class BpjsPresenter {

    Context mContext;
    IBpjsView mView;
    RecordApi mApi;

    public BpjsPresenter(Context context,IBpjsView view) {
        mContext = context;
        mView = view;
        mApi = RxHttpUtils.getInstance().createApi(RecordApi.class);
    }

    public void initBpjs(String taskId){
        mApi.initBpjs(TokenManager.getInstance().getToken(),taskId)
                .compose(RxTransformer.io_main())
                .subscribe(new BaseObserver<ResponseBody>(new SoftReference(mContext)) {
                    @Override
                    public void onNext(ResponseBody value) {
                        super.onNext(value);
                        mView.bpjsOk();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        mView.bpjsFail(ex.getMessage());
                        AppException.handleException(mContext,ex.getCode(),ex.getMessage());
                    }
                });


    }
}
