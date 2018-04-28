package com.yinshan.happycash.network.api;

import com.yinshan.happycash.view.information.model.RegionsBean;

import java.util.List;

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

    /**
     * 获取区号
     * @param id
     * @return
     */
    @GET("region/areacodes/city/{cityId} ")
    Observable<List<String>> getTelephoneAreaCode(@Path("cityId") int id);
}
