package com.yinshan.happycash.view.information.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.yinshan.happycash.application.AppException;
import com.yinshan.happycash.application.HappyAppSP;
import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.network.api.RecordApi;
import com.yinshan.happycash.network.api.RegionApi;
import com.yinshan.happycash.network.common.RxHttpUtils;
import com.yinshan.happycash.network.common.base.ApiException;
import com.yinshan.happycash.network.common.base.BaseObserver;
import com.yinshan.happycash.network.common.base.RxTransformer;
import com.yinshan.happycash.utils.SPUtils;
import com.yinshan.happycash.view.information.model.PersonalBean;
import com.yinshan.happycash.view.information.model.RegionsBean;
import com.yinshan.happycash.view.information.view.IPersonalView;

import java.lang.ref.SoftReference;

import okhttp3.ResponseBody;

/**
 * Created by huxin on 2018/4/19.
 */

public class PersonalPresenter {

    Context mContext;
    IPersonalView mView;
    RecordApi mApi;
    RegionApi mRegionApi;

    public PersonalPresenter(Context context,IPersonalView view){
        mContext = context;
        mView = view;
        mApi = RxHttpUtils.getInstance().createApi(RecordApi.class);
        mRegionApi = RxHttpUtils.getInstance().createApi(RegionApi.class);
    }

    public void getPersonInfo() {
        mView.showLoadingDialog();
        mApi.getPersonalInfo(TokenManager.getInstance().getToken())
                .compose(RxTransformer.io_main())
                .subscribe(new BaseObserver<PersonalBean>(new SoftReference(mContext)) {
                    @Override
                    public void onNext(PersonalBean personalBean) {
                        mView.showInfo(personalBean);
                        if(personalBean!=null&& !TextUtils.isEmpty(personalBean.getCredentialNo())){
                            SPUtils.getInstance().setUserKtp(personalBean.getCredentialNo());
                        }
                        if(personalBean!=null&&!TextUtils.isEmpty(personalBean.getFullName())){
                            SPUtils.getInstance().setUsername(personalBean.getFullName());
                        }
                        mView.dismissLoadingDialog();
                    }

                    @Override
                    public void onError(ApiException ex) {
                        mView.dismissLoadingDialog();
                        AppException.handleException(mContext,ex.getCode(),ex.getMessage());
                    }
                });
    }

    public void getRegion(String level,int id,final int index){
        mView.showLoadingDialog();
        mRegionApi.getRegion(level,id)
                .compose(RxTransformer.io_main())
                .subscribe(new BaseObserver<RegionsBean>(new SoftReference(mContext)) {
                    @Override
                    public void onNext(RegionsBean bean) {
                        mView.showRegion(bean,index);
                        mView.dismissLoadingDialog();
                    }

                    @Override
                    public void onError(ApiException e) {
                        mView.dismissLoadingDialog();
                        AppException.handleException(mContext,e.getCode(),e.getMessage());
                    }
                });
    }

    public void submitPersonalInfo(final PersonalBean personalBean){
        mView.showLoadingDialog();
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
                        SPUtils.getInstance().setUsername(personalBean.getFullName());
                        if(!TextUtils.isEmpty(personalBean.getCredentialNo()))
                            SPUtils.getInstance().setUserKtp(personalBean.getCredentialNo());
                        mView.submitPersonOk();
                        mView.dismissLoadingDialog();
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
