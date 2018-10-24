package com.yinshan.happycash.view.me.view.impl.support;

import android.content.Context;

import com.yinshan.happycash.application.AppException;
import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.network.api.RecordApi;
import com.yinshan.happycash.network.common.RxHttpUtils;
import com.yinshan.happycash.network.common.base.ApiException;
import com.yinshan.happycash.network.common.base.BaseObserver;
import com.yinshan.happycash.network.common.base.RxTransformer;
import com.yinshan.happycash.view.information.model.PersonalBean;
import com.yinshan.happycash.view.me.view.IGetPersonView;

import java.lang.ref.SoftReference;

/**
 * Created by huxin on 2018/8/29.
 */
public class GetPersonInfoPresenter {

    Context mContext;
    IGetPersonView mView;
    RecordApi mApi;

    public GetPersonInfoPresenter(Context context,IGetPersonView view){
        mContext = context;
        mView = view;
        mApi = RxHttpUtils.getInstance().createApi(RecordApi.class);
    }

    public void getPersonInfo() {
        mView.showLoadingDialog();
        mApi.getPersonalInfo(TokenManager.getInstance().getToken())
                .compose(RxTransformer.io_main())
                .subscribe(new BaseObserver<PersonalBean>(new SoftReference(mContext)) {
                    @Override
                    public void onNext(PersonalBean personalBean) {
                        mView.showInfo(personalBean);
                        mView.dismissLoadingDialog();
                    }

                    @Override
                    public void onError(ApiException e) {
                        mView.dismissLoadingDialog();
                        mView.showPersonInfoError();
                        AppException.handleException(mContext,e.getCode(),e.getMessage());
                    }
                });
    }
}
