package com.yinshan.happycash.framework;

/**
 * Created by hpw on 2016/8/9.
 */

import android.support.annotation.NonNull;

/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MvpView type thatnts to be attached with.
 */
public interface MvpBasePresenter<T extends MvpBaseView> {
    void attachView(@NonNull T mvpView);

    void detachView();
}