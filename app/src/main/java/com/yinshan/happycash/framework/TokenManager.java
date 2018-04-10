package com.yinshan.happycash.framework;

import com.yinshan.happycash.application.AppApplication;
import com.yinshan.happycash.network.common.RxHttpUtils;


/**
 * Created by admin on 2018/3/26.
 */

public class TokenManager {
    private static String TOKEN_CACHE_KEY         = "token_cache_key";
    public static String REFRESH_TOKEN_CACHE_KEY = "refresh_token_cahce_key";
    public static boolean isExpired = false;
    private static TokenManager instance;
    private ACache cache;
    private TokenManager(){
        cache = ACache.get(AppApplication.appContext);
    }
    public static TokenManager getInstance() {
        if (instance == null) {
            synchronized (RxHttpUtils.class) {
                if (instance == null) {
                    instance = new TokenManager();
                }
            }
        }
        return instance;
    }

    public boolean hasLogin(){
        return getToken() != null&&!isExpired;
    }

    public String getToken() {
//        return "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwODk1Mjc2MzcwNTA2NjYiLCJleHAiOjE1MjM2MTExNTl9.m30aWJmeJfD_jRgDRstYC8O5aS97Z4YvPGAsSMh5a7bzdFIuqQIWtTzNomTSl7M-DqvT6F8CkZMlVdKxygszbA";
        return cache.getAsString(TOKEN_CACHE_KEY);
    }

    public void setToken(String token, int time){
        if(time <= 0){
            cache.put(TOKEN_CACHE_KEY, token);
        }else{
            cache.put(TOKEN_CACHE_KEY, token, time);
        }
//        mLoginStatus.mChangTime = l;
//        mLoginTime = l;
//        mLoginStatus.mLoginStatus = LoginStatusEnum.UNLOGIN_LOGIN;
        isExpired = false;
    }
}
