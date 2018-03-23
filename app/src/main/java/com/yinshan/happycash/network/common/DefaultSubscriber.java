package com.yinshan.happycash.network.common;

import com.yinshan.happycash.network.common.base.BaseURL;

import java.net.SocketTimeoutException;

//import retrofit2.adapter.rxjava.HttpException;
//import rx.Subscriber;

/**
 * Created by huxin on 2018/3/23.
 */

public abstract class DefaultSubscriber<T>
//        extends Subscriber<T>
 {



//    @Override
//    public void onStart() {
//        super.onStart();
//    }
//
//    @Override
//    public void onCompleted() {
//
//    }
//
//    @Override
//    public void onError(Throwable e) {
//        Throwable throwable = e;
//        //获取最根源的异常
//        while (throwable.getCause() != null) {
//            e = throwable;
//            throwable = throwable.getCause();
//        }
//        //无网络连接
//        if (!NetworkUtils.isNetworkAvailable(AppApplication.getInstance())
//                || e instanceof SocketTimeoutException) {
//            onFailure(BaseURL.APP_EXCEPTION_HTTP_TIMEOUT, e.getMessage());
//        } else if (e instanceof BusinessException) {
//            //业务异常
//            BusinessException exception = (BusinessException) e;
//            onFailure(exception.getCode(), exception.getMessage());
//        } else if (e instanceof HttpException) {
//            //网络连接异常
//            HttpException httpException = (HttpException) e;
//            onFailure(httpException.code(),
//                    httpException.getMessage());
//        } else {
//            //其他异常
//            onFailure(BaseURL.APP_EXCEPTION_HTTP_OTHER, e.getMessage());
//        }
//    }
//
//    @Override
//    public void onNext(T t) {
//        onSuccess(t);
//    }

    public abstract void onSuccess(T t);

    public abstract void onFailure(int errorCode, String errorMessage);
}
