package com.yinshan.happycash.network.common.base;

import android.content.Context;
import android.widget.Toast;

import com.yinshan.happycash.widget.logger.LogUtil;

import java.lang.ref.SoftReference;

import io.reactivex.disposables.Disposable;

/**
 * Created by admin on 2018/3/7.
 */

public class BaseObserver<T> extends ErrorObserver<T> {

    private SoftReference<Context> context;

    public BaseObserver(SoftReference context) {
        this.context = context;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T value) {

    }

    @Override
    protected void onError(ApiException ex) {
        Toast.makeText(context.get(), ex.getDisplayMessage(), Toast.LENGTH_SHORT).show();
        LogUtil.getInstance().e(ex.getDisplayMessage());
    }

    @Override
    public void onComplete() {

    }
}
