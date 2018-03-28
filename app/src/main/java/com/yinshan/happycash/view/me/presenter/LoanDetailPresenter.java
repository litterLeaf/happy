package com.yinshan.happycash.view.me.presenter;

import android.content.Context;

import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.network.api.LoanApi;
import com.yinshan.happycash.network.common.RxHttpUtils;
import com.yinshan.happycash.view.me.view.ILoanDetailView;

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
                .getLoanDetail(TokenManager.getInstance().getToken(),12);
    }
}
