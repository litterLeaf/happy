package com.yinshan.happycash.framework;

/**
 * Created by admin on 2018/3/26.
 */

public class TokenManager {
    private static String TOKEN_CACHE_KEY         = "token_cache_key";
    public static String REFRESH_TOKEN_CACHE_KEY = "refresh_token_cahce_key";
    private static boolean isExpired = false;
    private ACache cache;
    public static TokenManager getInstance() {
        return Holder.INSTANCE;
    }
    private static class Holder{
        private static final TokenManager INSTANCE = new TokenManager();
    }
    public boolean hasLogin(){
        return getToken() != null&&!isExpired;
    }

    public String getToken() {
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
