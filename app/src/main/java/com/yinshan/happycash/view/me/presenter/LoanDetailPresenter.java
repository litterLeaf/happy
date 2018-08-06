package com.yinshan.happycash.view.me.presenter;

import android.content.Context;

import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.network.api.LoanApi;
import com.yinshan.happycash.network.common.RxHttpUtils;
import com.yinshan.happycash.network.common.base.ApiException;
import com.yinshan.happycash.network.common.base.BaseObserver;
import com.yinshan.happycash.network.common.base.RxTransformer;
import com.yinshan.happycash.view.me.model.LoanDetailBean;
import com.yinshan.happycash.view.me.view.ILoanDetailView;

import java.lang.ref.SoftReference;

/**
 * Created by huxin on 2018/3/28.
 */

public class LoanDetailPresenter {

    Context mContext;
    ILoanDetailView mView;

    public LoanDetailPresenter(Context context,ILoanDetailView view){
        mContext = context;
        mView = view;
    }

    public void getDetail(long loanId){
        RxHttpUtils.getInstance().createApi(LoanApi.class)
                .getLoanDetail(TokenManager.getInstance().getToken(),loanId)
                .compose(RxTransformer.io_main())
                .subscribe(new BaseObserver<LoanDetailBean>(new SoftReference(mContext)){
                    @Override
                    public void onNext(LoanDetailBean value) {
                        super.onNext(value);
                        mView.showDetail(value);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                    }
                });
    }
}
