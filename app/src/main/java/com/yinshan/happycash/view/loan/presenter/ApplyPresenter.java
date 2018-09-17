package com.yinshan.happycash.view.loan.presenter;

import android.content.Context;

import com.yinshan.happycash.application.AppException;
import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.network.api.LoanApi;
import com.yinshan.happycash.network.common.RxHttpUtils;
import com.yinshan.happycash.network.common.base.ApiException;
import com.yinshan.happycash.network.common.base.BaseObserver;
import com.yinshan.happycash.network.common.base.RxTransformer;
import com.yinshan.happycash.view.loan.view.IApplyView;
import com.yinshan.happycash.view.me.model.LoanDetailBean;

import java.lang.ref.SoftReference;

import okhttp3.ResponseBody;

/**
 * Created by huxin on 2018/8/23.
 */
public class ApplyPresenter {

    Context mContext;
    IApplyView mView;

    public ApplyPresenter(Context context,IApplyView view){
        mContext = context;
        mView = view;
    }

    public void getDetail(long appId){
        mView.showLoadingDialog();
        RxHttpUtils.getInstance().createApi(LoanApi.class)
                .getDetail(appId, TokenManager.getInstance().getToken())
                .compose(RxTransformer.io_main())
                .subscribe(new BaseObserver<LoanDetailBean>(new SoftReference(mContext)){
                    @Override
                    public void onNext(LoanDetailBean value) {
                        super.onNext(value);
                        mView.dismissLoadingDialog();
                        mView.showDetailOk(value);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        mView.dismissLoadingDialog();
                        AppException.handleException(mContext,ex.getCode(),ex.getMessage());
                        mView.showDetailFail(ex.getDisplayMessage());
                    }
                });
    }

    public void cancel(long appId){
        mView.showLoadingDialog();
        RxHttpUtils.getInstance().createApi(LoanApi.class)
                .cancelLoanApp(appId,TokenManager.getInstance().getToken())
                .compose(RxTransformer.io_main())
                .subscribe(new BaseObserver<ResponseBody>(new SoftReference(mContext)){
                    @Override
                    public void onNext(ResponseBody value) {
                        super.onNext(value);
                        mView.dismissLoadingDialog();
                        mView.cancelOk();
                    }

                    @Override
                    protected void onError(ApiException e) {
                        super.onError(e);
                        mView.dismissLoadingDialog();
                        AppException.handleException(mContext,e.getCode(),e.getMessage());
                        mView.cancelFail();
                    }
                });
    }
}
