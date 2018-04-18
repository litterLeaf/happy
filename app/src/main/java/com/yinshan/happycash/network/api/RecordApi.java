package com.yinshan.happycash.network.api;

import com.yinshan.happycash.view.information.model.ContactBean;
import com.yinshan.happycash.view.information.model.ProgressBean;


import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by huxin on 2018/4/10.
 */

public interface RecordApi {

    @GET("record/progress")
    Observable<ProgressBean> progress(@Header("X-AUTH-TOKEN") String token);

    @GET("record/contact")
    Observable<List<ContactBean>> getContactInfo(@Header("X-AUTH-TOKEN") String token);

    @FormUrlEncoded
    @PUT("record/contact")
    Observable<ResponseBody> submitContactInfo(@Field("firstContactName") String firstContactName,
                                               @Field("firstContactMobile") String firstContactMobile,
                                               @Field("firstContactRelation") String firstContactRelation,
                                               @Field("secondContactName") String secondContactName,
                                               @Field("secondContactMobile") String secondContactMobile,
                                               @Field("secondContactRelation") String secondContactRelation,
                                               @Header("X-AUTH-TOKEN") String token);

    @Multipart
    @PUT("record/files")
    Call<ResponseBody> uploadPhoto(
            @Part MultipartBody.Part photoFile,
            @Query("fileType") String fileType,
            @Header("X-AUTH-TOKEN") String token
    );
}
