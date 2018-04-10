package com.yinshan.happycash.network.api;

import com.yinshan.happycash.view.information.model.ProgressBean;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by huxin on 2018/4/10.
 */

public interface RecordApi {

    @GET("record/progress")
    Observable<ProgressBean> progress(@Header("X-AUTH-TOKEN") String token);
}
