package com.yinshan.happycash.network.api;

import com.yinshan.happycash.view.information.model.RegionsBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import io.reactivex.Observable;

/**
 * Created by huxin on 2018/4/26.
 */

public interface RegionApi {

    @GET("region/{level}/{id}")
    Observable<RegionsBean> getRegion(@Path("level") String level,
                                      @Path("id") int id);
}
