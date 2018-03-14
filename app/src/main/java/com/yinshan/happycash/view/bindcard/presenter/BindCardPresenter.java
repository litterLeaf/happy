package com.yinshan.happycash.view.bindcard.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.yinshan.happycash.view.bindcard.contract.BindCardContract;

/**
 * Created by admin on 2018/3/13.
 */

public class BindCardPresenter implements BindCardContract.Presenter {
    private Context context;
    private BindCardContract.View mvpView;

    public BindCardPresenter(Context ctx) {
        //construct
        this.context = ctx;
    }

    @Override
    public void attachView(@NonNull BindCardContract.View mvpView) {
        this.mvpView=mvpView;

    }
    @Override
    public void detachView() {
        this.mvpView = null;
    }

    @Override
    public void bindCard(String userName, String password) {

    }
}
