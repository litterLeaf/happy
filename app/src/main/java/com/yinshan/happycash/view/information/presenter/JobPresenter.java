package com.yinshan.happycash.view.information.presenter;

import android.content.Context;

import com.yinshan.happycash.application.AppException;
import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.network.api.RecordApi;
import com.yinshan.happycash.network.api.RegionApi;
import com.yinshan.happycash.network.common.RxHttpUtils;
import com.yinshan.happycash.network.common.base.ApiException;
import com.yinshan.happycash.network.common.base.BaseObserver;
import com.yinshan.happycash.network.common.base.RxTransformer;
import com.yinshan.happycash.view.information.model.EmploymentBean;
import com.yinshan.happycash.view.information.model.RegionsBean;
import com.yinshan.happycash.view.information.view.IJobView;

import java.lang.ref.SoftReference;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * Created by huxin on 2018/4/24.
 */

public class JobPresenter {

    Context mContext;
    IJobView mView;
    RecordApi mApi;
    RegionApi mRegionApi;

    public JobPresenter(Context context,IJobView view){
        mContext = context;
        mView = view;
        mApi = RxHttpUtils.getInstance().createApi(RecordApi.class);
        mRegionApi = RxHttpUtils.getInstance().createApi(RegionApi.class);
    }

    public void getJobInfo(){
        mView.showLoadingDialog();
        mApi.getEmploymentInfo(TokenManager.getInstance().getToken())
                .compose(RxTransformer.io_main())
                .subscribe(new BaseObserver<EmploymentBean>(new SoftReference(mContext)) {
                    @Override
                    public void onNext(EmploymentBean bean) {
                        mView.dismissLoadingDialog();
                        mView.showInfo(bean);
                    }

                    @Override
                    public void onError(ApiException e) {
                        mView.dismissLoadingDialog();
                        AppException.handleException(mContext,e.getCode(),e.getMessage());
                    }
                });
    }

    public void getCityTel(int cityId){
        mView.showLoadingDialog();
        mRegionApi.getTelephoneAreaCode(cityId)
                .compose(RxTransformer.io_main())
                .subscribe(new BaseObserver<List<String>>(new SoftReference(mContext)){
                    @Override
                    public void onNext(List<String> strs) {
                        mView.showTelList(strs);
                        mView.dismissLoadingDialog();
                    }

                    @Override
                    public void onError(ApiException e) {
                        mView.dismissLoadingDialog();
                        AppException.handleException(mContext,e.getCode(),e.getMessage());
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
                        mView.dismissLoadingDialog();
                        mView.showRegion(bean,index);
                    }

                    @Override
                    public void onError(ApiException e) {
                        mView.dismissLoadingDialog();
                        AppException.handleException(mContext,e.getCode(),e.getMessage());
                    }
                });
    }

    public void submitJobInfo(EmploymentBean bean){
        mView.showLoadingDialog();
        mApi.submitEmploymentInfo(bean.getCompanyName(),bean.getCompanyProvince(),bean.getCompanyCity(),bean.getCompanyDistrict()
                ,bean.getCompanyArea(),bean.getCompanyAddress(),bean.getCompanyPhone(),bean.getProfession(),bean.getSalary(),bean.getSalaryDay(),
                TokenManager.getInstance().getToken())
                .compose(RxTransformer.io_main())
                .subscribe(new BaseObserver<ResponseBody>(new SoftReference(mContext)){
                    @Override
                    public void onNext(ResponseBody value) {
                        super.onNext(value);
                        mView.dismissLoadingDialog();
                        mView.submitOk();
                    }

                    @Override
                    protected void onError(ApiException e) {
                        super.onError(e);
                        mView.dismissLoadingDialog();
                        AppException.handleException(mContext,e.getCode(),e.getMessage());
                    }
                });
    }
}
