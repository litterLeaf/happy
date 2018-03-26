package com.yinshan.happycash.view.main.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.yinshan.happycash.network.api.UserApi;
import com.yinshan.happycash.network.common.RxHttpUtils;
import com.yinshan.happycash.network.common.base.BaseObserver;
import com.yinshan.happycash.network.common.base.RxSchedulers;
import com.yinshan.happycash.utils.ToastUtils;
import com.yinshan.happycash.view.login.contract.LoginContract;
import com.yinshan.happycash.view.main.SplashActivity;
import com.yinshan.happycash.view.main.contract.SplashContract;
import com.yinshan.happycash.view.main.model.LastLoanAppBean;

import java.lang.ref.SoftReference;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 2018/1/31.
 */

public class SplashPresenter implements SplashContract.Presenter {
    private Context context;
    private SplashContract.View mvpView;

    public SplashPresenter(Context ctx) {
        //construct
        this.context = ctx;
    }

    @Override
    public void attachView(@NonNull SplashContract.View mvpView) {
        this.mvpView=mvpView;

    }
    @Override
    public void detachView() {
        this.mvpView = null;
    }

    @Override
    public void getLastLoanAppBean(String userName, String password) {
        RxHttpUtils.getInstance().createApi(UserApi.class)
                .getLatestLoanApp("")
               .compose(RxSchedulers.io_main())
                .subscribe(new BaseObserver<LastLoanAppBean>(new SoftReference(context)){
                    @Override
                    public void onNext(LastLoanAppBean value) {
                        super.onNext(value);
                        ToastUtils.showShort("success");
                    }
                });
    }
}
