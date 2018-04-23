package com.yinshan.happycash.framework;

import com.yinshan.happycash.application.AppApplication;
import com.yinshan.happycash.network.common.RxHttpUtils;
import com.yinshan.happycash.utils.SPKeyUtils;
import com.yinshan.happycash.utils.SPUtils;
import com.yinshan.happycash.utils.SPKeyUtils;
import com.yinshan.happycash.utils.SPUtils;


/**
 * Created by admin on 2018/3/26.
 */

public class TokenManager {
    //    private static String TOKEN_CACHE_KEY         = "token_cache_key";
//    public static String REFRESH_TOKEN_CACHE_KEY = "refresh_token_cahce_key";
    public static boolean isExpired = false;
    private static TokenManager instance;
    //    private ACache cache;
//    private TokenManager(){
//        cache = ACache.get(AppApplication.appContext);
//    }
    public static TokenManager getInstance() {
        if (instance == null) {
            synchronized (TokenManager.class) {
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
        return "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwODk1Mjc2MzcwNTA2NjYiLCJleHAiOjE1MjU0Mjk0ODR9.UAmaP3N_61ZcP9gvxfz215JWCVADDrpEg4LKelHtEgYkHuyINn56DtgVX_WohYQFrSUdDH9ruhL9IZZ6EyyFrQ";
//        return cache.getAsString(TOKEN_CACHE_KEY);
//        return "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwODk1Mjc2MzcwNTA2NjYiLCJleHAiOjE1MjM2MTExNTl9.m30aWJmeJfD_jRgDRstYC8O5aS97Z4YvPGAsSMh5a7bzdFIuqQIWtTzNomTSl7M-DqvT6F8CkZMlVdKxygszbA";
//        return SPUtils.get(SPKeyUtils.TOKEN_KEY,"");
    }

    public void setToken(String token){
        SPUtils.put(SPKeyUtils.TOKEN_KEY, token);
        isExpired = false;
    }
}
