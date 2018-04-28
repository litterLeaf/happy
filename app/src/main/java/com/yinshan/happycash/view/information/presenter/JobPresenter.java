package com.yinshan.happycash.view.information.presenter;

import android.content.Context;

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
        mApi.getEmploymentInfo(TokenManager.getInstance().getToken())
                .compose(RxTransformer.io_main())
                .subscribe(new BaseObserver<EmploymentBean>(new SoftReference(mContext)) {
                    @Override
                    public void onNext(EmploymentBean bean) {
                        mView.showInfo(bean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void getCityTel(int cityId){
        mRegionApi.getTelephoneAreaCode(cityId)
                .compose(RxTransformer.io_main())
                .subscribe(new BaseObserver<List<String>>(new SoftReference(mContext)){
                    @Override
                    public void onNext(List<String> strs) {
                        mView.showTelList(strs);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void getRegion(String level,int id,final int index){
        mRegionApi.getRegion(level,id)
                .compose(RxTransformer.io_main())
                .subscribe(new BaseObserver<RegionsBean>(new SoftReference(mContext)) {
                    @Override
                    public void onNext(RegionsBean bean) {
                        mView.showRegion(bean,index);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void submitJobInfo(EmploymentBean bean){
        mApi.submitEmploymentInfo(bean.getCompanyName(),bean.getCompanyProvince(),bean.getCompanyCity(),bean.getCompanyDistrict()
                ,bean.getCompanyArea(),bean.getCompanyAddress(),bean.getCompanyPhone(),bean.getProfession(),bean.getSalary(),bean.getSalaryDay(),
                TokenManager.getInstance().getToken())
                .compose(RxTransformer.io_main())
                .subscribe(new BaseObserver<ResponseBody>(new SoftReference(mContext)){
                    @Override
                    public void onNext(ResponseBody value) {
                        super.onNext(value);
                        mView.submitOk();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                    }
                });
    }
}
