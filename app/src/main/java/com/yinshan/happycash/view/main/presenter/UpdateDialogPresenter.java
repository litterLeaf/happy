package com.yinshan.happycash.view.main.presenter;

import android.content.Context;

import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.network.api.LoanApi;
import com.yinshan.happycash.network.common.RxHttpUtils;
import com.yinshan.happycash.network.common.base.ApiException;
import com.yinshan.happycash.network.common.base.BaseObserver;
import com.yinshan.happycash.network.common.base.RxTransformer;
import com.yinshan.happycash.view.main.view.IUpdateDialogView;

import java.lang.ref.SoftReference;

import okhttp3.ResponseBody;

/**
 * Created by huxin on 2018/9/11.
 */
public class UpdateDialogPresenter {

    private Context mContext;
    private IUpdateDialogView mView;
    LoanApi mApi;

    public UpdateDialogPresenter(Context context,IUpdateDialogView view){
        mContext = context;
        mView = view;
        mApi = RxHttpUtils.getInstance().createApi(LoanApi.class);
    }

    public void updateDialog(long appId,final String type){
        mApi.updateAppMsgShownStataus(TokenManager.getInstance().getToken(),appId,type)
                .compose(RxTransformer.io_main())
                .subscribe(new BaseObserver<ResponseBody>(new SoftReference(mContext)){
                       @Override
                       public void onNext(ResponseBody value) {
                           super.onNext(value);
                           mView.updateDialogSuccess(type);
                       }

                       @Override
                       protected void onError(ApiException ex) {
                           super.onError(ex);
                       }
                   }
                );
    }
}
