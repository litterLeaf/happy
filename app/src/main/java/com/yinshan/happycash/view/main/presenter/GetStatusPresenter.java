package com.yinshan.happycash.view.main.presenter;

import android.content.Context;

import com.yinshan.happycash.application.AppException;
import com.yinshan.happycash.network.api.LoanApi;
import com.yinshan.happycash.network.common.RxHttpUtils;
import com.yinshan.happycash.network.common.base.ApiException;
import com.yinshan.happycash.network.common.base.BaseObserver;
import com.yinshan.happycash.network.common.base.RxTransformer;
import com.yinshan.happycash.view.main.model.LastLoanAppBean;
import com.yinshan.happycash.view.main.view.IGetStatusView;

import java.lang.ref.SoftReference;

/**
 * Created by huxin on 2018/8/16.
 */
public class GetStatusPresenter {

    Context mContext;
    IGetStatusView mView;
    LoanApi mApi;

    public GetStatusPresenter(Context context, IGetStatusView view){
        mContext = context;
        mView = view;
        mApi = RxHttpUtils.getInstance().createApi(LoanApi.class);
    }

    public void getStatusInfo(String token){
        mView.showLoadingDialog();
        mApi.getLatestLoanApp(token)
                .compose(RxTransformer.io_main())
                .subscribe(new BaseObserver<LastLoanAppBean>(new SoftReference(mContext)){
                    @Override
                    public void onNext(LastLoanAppBean value) {
                        mView.dismissLoadingDialog();
                        super.onNext(value);
                        mView.getStatusSuccess(value);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        mView.dismissLoadingDialog();
                        super.onError(ex);
                        mView.getStatusError(ex.getDisplayMessage());
                        AppException.handleException(mContext,ex.getCode(),ex.getMessage());
                    }
                });
    }
}
