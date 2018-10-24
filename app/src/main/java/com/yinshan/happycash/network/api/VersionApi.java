package com.yinshan.happycash.network.api;

import com.yinshan.happycash.view.login.model.VersionBean;
import com.yinshan.happycash.view.main.model.ProfileBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import io.reactivex.Observable;

/**
 * Created by huxin on 2018/4/20.
 */

public interface VersionApi {

    @GET("profiles")
    Observable<ProfileBean> getVersionInfo(@Query("version") String version, @Header("X-AUTH-TOKEN") String token);
}
