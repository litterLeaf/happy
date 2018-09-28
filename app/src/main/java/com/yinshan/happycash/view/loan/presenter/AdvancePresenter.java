package com.yinshan.happycash.view.loan.presenter;

import android.content.Context;

import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.network.api.LoanApi;
import com.yinshan.happycash.network.common.RxHttpUtils;
import com.yinshan.happycash.network.common.base.ApiException;
import com.yinshan.happycash.network.common.base.BaseObserver;
import com.yinshan.happycash.network.common.base.RxTransformer;
import com.yinshan.happycash.view.loan.view.IAdvanceView;
import com.yinshan.happycash.view.me.model.PrePaymentBean;

import java.lang.ref.SoftReference;

/**
 * Created by huxin on 2018/9/28.
 */
public class AdvancePresenter {

    Context mContext;
    IAdvanceView mView;

    public AdvancePresenter(Context context,IAdvanceView view){
        mContext = context;
        mView = view;
    }

    public void getAdvance(long appId){
        mView.showLoadingDialog();
        RxHttpUtils.getInstance().createApi(LoanApi.class)
                .advance(appId, TokenManager.getInstance().getToken())
                .compose(RxTransformer.io_main())
                .subscribe(new BaseObserver<PrePaymentBean>(new SoftReference(mContext)){
                    @Override
                    public void onNext(PrePaymentBean bean) {
                        super.onNext(bean);
                        mView.dismissLoadingDialog();
                        mView.showAdvance(bean);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        mView.dismissLoadingDialog();
                        mView.showAdvanceFail(ex.getDisplayMessage());
                    }
                });
    }
}
