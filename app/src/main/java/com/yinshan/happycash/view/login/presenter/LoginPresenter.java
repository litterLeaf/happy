package com.yinshan.happycash.view.login.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.yinshan.happycash.view.login.contract.LoginContract;

/**
 * Created by admin on 2018/1/31.
 */

public class LoginPresenter implements LoginContract.Presenter {
    private Context context;
    private LoginContract.View mvpView;

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
    public void signIn(String userName, String password) {

    }
}
