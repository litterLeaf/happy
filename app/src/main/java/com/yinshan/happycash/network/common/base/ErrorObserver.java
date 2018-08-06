package com.yinshan.happycash.network.common.base;


import com.google.gson.Gson;
import com.yinshan.happycash.network.common.support.QualityErrorBean;

import java.io.IOException;

import io.reactivex.Observer;
import retrofit2.HttpException;

/**
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 *        ┃　　　┃   神兽保佑
 *        ┃　　　┃   代码无BUG！
 *        ┃　　　┗━━━┓
 *        ┃　　　　　　　┣┓
 *        ┃　　　　　　　┏┛
 *        ┗┓┓┏━┳┓┏┛
 *           ┃┫┫　┃┫┫
 *           ┗┻┛　┗┻┛
 *
 *  @author  admin
 *  on 2018/3/7
 *
 *  错误观察者封装
 */
public abstract class ErrorObserver<T> implements Observer<T> {
    @Override
    public void onError(Throwable e) {
        if(e instanceof ApiException) {
            onError((ApiException) e);
        }else if(e instanceof HttpException){
            HttpException httpException = (HttpException) e;
            String errorBody = null;
            try{
                errorBody = httpException.response().errorBody().string();
                int i=3;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            onError(new ApiException(e,CodeException.E_201_ERROR,"201错误"));
        }else{
            onError(new ApiException(e,CodeException.UNKNOWN_ERROR,"未知错误"));
        }
    }

    /**
     * 错误回调
     */
    protected abstract void onError(ApiException ex);
}