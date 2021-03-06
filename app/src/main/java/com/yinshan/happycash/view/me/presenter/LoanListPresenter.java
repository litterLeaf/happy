package com.yinshan.happycash.view.me.presenter;

import android.content.Context;

import com.yinshan.happycash.application.AppException;
import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.network.api.LoanApi;
import com.yinshan.happycash.network.api.UserApi;
import com.yinshan.happycash.network.common.RxHttpUtils;
import com.yinshan.happycash.network.common.base.ApiException;
import com.yinshan.happycash.network.common.base.BaseObserver;
import com.yinshan.happycash.network.common.base.RxTransformer;
import com.yinshan.happycash.view.me.model.LoanItem;
import com.yinshan.happycash.view.me.view.ILoanListView;

import java.lang.ref.SoftReference;
import java.util.List;

/**
 * Created by huxin on 2018/3/20.
 */

public class LoanListPresenter {

    Context mContext;
    ILoanListView mView;

    public LoanListPresenter(Context context,ILoanListView view){
        mContext = context;
        mView = view;
    }

    public void getList(){
        mView.showLoadingDialog();
        RxHttpUtils.getInstance().createApi(LoanApi.class)
                .getLoanList(TokenManager.getInstance().getToken())
                .compose(RxTransformer.io_main())
                .subscribe(new BaseObserver<List<LoanItem>>(new SoftReference(mContext)){
                    @Override
                    public void onNext(List<LoanItem> value) {
                        super.onNext(value);
                        mView.dismissLoadingDialog();
                        mView.showList(value);
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


