package com.yinshan.happycash.view.me.presenter;

import android.content.Context;

import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.network.api.RecordApi;
import com.yinshan.happycash.network.api.UserApi;
import com.yinshan.happycash.network.common.RxHttpUtils;
import com.yinshan.happycash.network.common.base.ApiException;
import com.yinshan.happycash.network.common.base.BaseObserver;
import com.yinshan.happycash.network.common.base.RxTransformer;
import com.yinshan.happycash.view.me.view.ISafeSettingView;
import com.yinshan.happycash.view.me.view.impl.SafeSettingActivity;

import java.lang.ref.SoftReference;

import okhttp3.ResponseBody;

/**
 * Created by huxin on 2018/4/24.
 */

public class SafeSettingPresenter {

    Context mContext;
    ISafeSettingView mView;
    UserApi mApi;

    public SafeSettingPresenter(Context context,ISafeSettingView view){
        mContext = context;
        mView = view;
        mApi = RxHttpUtils.getInstance().createApi(UserApi.class);
    }

    public void logout(){
        mApi.logout(TokenManager.getInstance().getToken())
                .compose(RxTransformer.io_main())
                .subscribe(new BaseObserver<ResponseBody>(new SoftReference(mContext)){
                    @Override
                    public void onNext(ResponseBody body) {
                        super.onNext(body);
                        mView.logoutOk();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                    }
                });
    }
}
