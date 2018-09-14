package com.yinshan.happycash.network.common.base;


import android.util.Log;

import com.google.gson.Gson;
import com.yinshan.happycash.network.common.support.QualityErrorBean;

import java.io.EOFException;
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
            Log.v("huxin","ApiException");
        }else if(e instanceof HttpException) {
            Log.v("huxin","HttpException ");
            HttpException httpException = (HttpException) e;
            String errorBody = null;
            try {
                errorBody = httpException.response().errorBody().string();
                int i = 3;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            Log.v("huxin","HttpException "+((HttpException) e).code());
            if (((HttpException) e).code() == 201)
                onError(new ApiException(e, CodeException.E_201_ERROR, "201错误"));
            else
                onError(new ApiException(e, ((HttpException) e).code(),((HttpException) e).message()));
        }else if(e instanceof EOFException){
            Log.v("huxin","EOFException");
            onError(new ApiException(e, CodeException.E_EOF_ERROR, "EOF错误"));
        }else{
            Log.v("huxin","other exception");
            onError(new ApiException(e,CodeException.UNKNOWN_ERROR,e.getMessage()));
        }
    }

    /**
     * 错误回调
     */
    protected abstract void onError(ApiException ex);
}