package com.yinshan.happycash.view.main.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.yinshan.happycash.network.api.LoanApi;
import com.yinshan.happycash.network.api.UserApi;
import com.yinshan.happycash.network.common.RxHttpUtils;
import com.yinshan.happycash.network.common.base.ApiException;
import com.yinshan.happycash.network.common.base.BaseObserver;
import com.yinshan.happycash.network.common.base.RxTransformer;
import com.yinshan.happycash.view.main.contract.SplashContract;
import com.yinshan.happycash.view.main.model.LastLoanAppBean;

import java.lang.ref.SoftReference;


/**
 * Created by admin on 2018/1/31.
 */

public class SplashPresenter implements SplashContract.Presenter {
    private Context context;
    private SplashContract.View mvpView;

    public SplashPresenter(Context ctx) {
        //construct
        this.context = ctx;
    }

    @Override
    public void attachView(@NonNull SplashContract.View mvpView) {
        this.mvpView=mvpView;

    }
    @Override
    public void detachView() {
        this.mvpView = null;
    }

    @Override
    public void getLastLoanAppBean(String token) {
        RxHttpUtils.getInstance().createApi(LoanApi.class)
                .getLatestLoanApp(token)
                .compose(RxTransformer.io_main())
                .subscribe(new BaseObserver<LastLoanAppBean>(new SoftReference(context)){
                    @Override
                    public void onNext(LastLoanAppBean value) {
                        super.onNext(value);
                        mvpView.getStatusSuccess(value);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        mvpView.getStatusError(ex.getDisplayMessage());
                    }
                });
    }
}
