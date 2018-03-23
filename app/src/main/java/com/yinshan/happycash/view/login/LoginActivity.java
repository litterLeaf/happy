package com.yinshan.happycash.view.login;

import android.os.Bundle;
import android.view.View;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseActivity;
import com.yinshan.happycash.view.login.presenter.LoginPresenter;

/**
 * Created by admin on 2018/1/31.
 */

public class LoginActivity extends BaseActivity{

    LoginPresenter mPresenter;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mPresenter = new LoginPresenter(this);
        mPresenter.signIn("aaa","aaa","aaa","aaa","aaa","aaa");
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void secondLayout() {

    }

    @Override
    protected void secondInit() {

    }
}
