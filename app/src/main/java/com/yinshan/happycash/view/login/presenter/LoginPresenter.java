package com.yinshan.happycash.view.login.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.yinshan.happycash.network.api.UserApi;
import com.yinshan.happycash.network.common.RxHttpUtils;
import com.yinshan.happycash.network.common.network.RetrofitClient;
import com.yinshan.happycash.view.login.contract.LoginContract;

/**
 * Created by admin on 2018/1/31.
 */

public class LoginPresenter implements LoginContract.Presenter {
    private Context context;
    private LoginContract.View mvpView;
    final UserApi api = RxHttpUtils.getInstance().createApi(UserApi.class);

    public LoginPresenter(Context ctx) {
        //construct
        this.context = ctx;
    }

    @Override
    public void attachView(@NonNull LoginContract.View mvpView) {
        this.mvpView=mvpView;

    }
    @Override
    public void detachView() {
        this.mvpView = null;
    }

    @Override
    public void signIn(String smsCode, String captchaSid, String captcha, String mobile, String inviteCode, String andridId) {
        //api.login()
    }
}
