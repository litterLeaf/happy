package com.yinshan.happycash.network.api;

import com.yinshan.happycash.view.login.model.VersionBean;

import retrofit2.http.GET;
import retrofit2.http.Header;
import rx.Observable;

/**
 * Created by huxin on 2018/4/20.
 */

public interface VersionApi {

    @GET("version/latest")
    Observable<VersionBean> getVersionInfo();
}
