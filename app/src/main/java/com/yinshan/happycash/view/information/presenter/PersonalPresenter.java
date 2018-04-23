package com.yinshan.happycash.view.information.presenter;

import android.content.Context;

import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.network.api.RecordApi;
import com.yinshan.happycash.network.common.RxHttpUtils;
import com.yinshan.happycash.network.common.base.ApiException;
import com.yinshan.happycash.network.common.base.BaseObserver;
import com.yinshan.happycash.network.common.base.RxTransformer;
import com.yinshan.happycash.view.information.model.PersonalBean;
import com.yinshan.happycash.view.information.view.IPersonalView;

import java.lang.ref.SoftReference;

import io.reactivex.internal.observers.BlockingBaseObserver;
import okhttp3.ResponseBody;

/**
 * Created by huxin on 2018/4/19.
 */

public class PersonalPresenter {

    Context mContext;
    IPersonalView mView;
    RecordApi mApi;

    public PersonalPresenter(Context context,IPersonalView view){
        mContext = context;
        mView = view;
        mApi = RxHttpUtils.getInstance().createApi(RecordApi.class);
    }

    public void getPersonInfo() {
        mApi.getPersonalInfo(TokenManager.getInstance().getToken())
                .compose(RxTransformer.io_main())
                .subscribe(new BaseObserver<PersonalBean>(new SoftReference(mContext)) {
                    @Override
                    public void onNext(PersonalBean personalBean) {
                        mView.showInfo(personalBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void submitPersonalInfo(PersonalBean personalBean){
        mApi.submitPersonalInfo(personalBean.getFullName(),personalBean.getCredentialNo(),personalBean.getFamilyNameInLaw(),
                personalBean.getGender(),personalBean.getProvince(),personalBean.getCity(),personalBean.getDistrict(),personalBean.getArea(),
                personalBean.getAddress(),personalBean.getLastEducation(),personalBean.getMaritalStatus(),personalBean.getChildrenNumber(),
                personalBean.getResidenceDuration(),personalBean.getFacebookId(),TokenManager.getInstance().getToken()
                )
                .compose(RxTransformer.io_main())
                .subscribe(new BaseObserver<ResponseBody>(new SoftReference(mContext)){
                    @Override
                    public void onNext(ResponseBody value) {
                        super.onNext(value);
                        mView.submitPersonOk();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                    }
                });
    }
}
