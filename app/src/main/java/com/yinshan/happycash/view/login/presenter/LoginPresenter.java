package com.yinshan.happycash.view.login.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.yinshan.happycash.application.AppContext;
import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.network.api.UserApi;
import com.yinshan.happycash.network.common.RxHttpUtils;
import com.yinshan.happycash.network.common.base.RxTransformer;
import com.yinshan.happycash.network.common.network.RetrofitClient;
import com.yinshan.happycash.utils.MachineUtils;
import com.yinshan.happycash.view.login.contract.LoginContract;
import com.yinshan.happycash.view.login.model.LoginTokenResponse;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

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
        api.login(smsCode,
                captchaSid,
                captcha,
                mobile,
                inviteCode,
                andridId,
                MachineUtils.getIPAddress(AppContext.getContext()),
                ""
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<LoginTokenResponse>() {
                    @Override
                    public void onNext(LoginTokenResponse loginTokenResponse) {
                        String token = loginTokenResponse.getToken();
                        TokenManager.getInstance().setToken(token);
                        mvpView.signInSuccess(loginTokenResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                            mvpView.signInError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void sendSms(String mobile) {
        api.sendSms(mobile)
                .compose(RxTransformer.io_main())
                .subscribe(new DefaultObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        mvpView.getSMSCodeSuccess(responseBody);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


}
