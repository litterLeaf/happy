package com.yinshan.happycash.view.me.presenter;

import android.content.Context;

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
        RxHttpUtils.getInstance().createApi(LoanApi.class)
                .getLoanList("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwODk1Mjc2MzcwNTA2NjYiLCJleHAiOjE1MjM2MTExNTl9.m30aWJmeJfD_jRgDRstYC8O5aS97Z4YvPGAsSMh5a7bzdFIuqQIWtTzNomTSl7M-DqvT6F8CkZMlVdKxygszbA")
                .compose(RxTransformer.io_main())
                .subscribe(new BaseObserver<List<LoanItem>>(new SoftReference(mContext)){
                    @Override
                    public void onNext(List<LoanItem> value) {
                        super.onNext(value);
                        mView.showList(value);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                    }
                });
    }
}


