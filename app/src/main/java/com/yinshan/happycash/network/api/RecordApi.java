package com.yinshan.happycash.network.api;

import com.yinshan.happycash.view.bindcard.model.BandCardBean;
import com.yinshan.happycash.view.information.model.ContactBean;
import com.yinshan.happycash.view.information.model.EmploymentBean;
import com.yinshan.happycash.view.information.model.PersonalBean;
import com.yinshan.happycash.view.information.model.ProgressBean;
import com.yinshan.happycash.view.information.model.RatingBean;
import com.yinshan.happycash.view.information.model.RecordFilesResponse;


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

    @GET("record/bankcard")
    Observable<BandCardBean> getBindCard(@Header("X-AUTH-TOKEN") String token);

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

    @GET("record/employment")
    Observable<EmploymentBean> getEmploymentInfo(@Header("X-AUTH-TOKEN") String token);

    @FormUrlEncoded
    @PUT("record/employment")
    Observable<ResponseBody> submitEmploymentInfo(@Field("companyName") String companyName,
                                                     @Field("companyProvince") String companyProvince,
                                                     @Field("companyCity") String companyCity,
                                                     @Field("companyDistrict") String companyDistrict,
                                                     @Field("companyArea") String companyArea,
                                                     @Field("companyAddress") String companyAddress,
                                                     @Field("companyPhone") String companyPhone,
                                                     @Field("profession") String profession,
                                                     @Field("salary") String salary,
                                                    @Field("salaryDay") String workEmail,
                                                     @Header("X-AUTH-TOKEN") String token);

    @GET("record/files")
    Observable<RecordFilesResponse> recordFiles(@Header("X-AUTH-TOKEN") String token);

    @Multipart
    @PUT("record/files")
    Call<ResponseBody> uploadPhoto(
            @Part MultipartBody.Part photoFile,
            @Query("fileType") String fileType,
            @Header("X-AUTH-TOKEN") String token
    );

    @GET("record/personalinfo")
    Observable<PersonalBean> getPersonalInfo(@Header("X-AUTH-TOKEN") String token);

    @FormUrlEncoded
    @PUT("record/personalinfo")
    Observable<ResponseBody> submitPersonalInfo(@Field("fullName") String fullName,
                                                @Field("credentialNo") String credentialNo,
                                                @Field("familyNameInLaw") String familyNameInLaw,
                                                @Field("gender") String gender,
                                                @Field("province") String province,
                                                @Field("city") String city,
                                                @Field("district") String district,
                                                @Field("area") String area,
                                                @Field("address") String address,
                                                @Field("lastEducation") String lastEducation,
                                                @Field("maritalStatus") String maritalStatus,
                                                @Field("childrenNumber") String childrenNumber,
                                                @Field("residenceDuration") String residenceDuration,
                                                @Field("facebookId") String facebookId,
                                                @Header("X-AUTH-TOKEN") String token);

    @GET("record/progress")
    Observable<ProgressBean> progress(@Header("X-AUTH-TOKEN") String token);

    @GET("record/rating")
    Observable<RatingBean> getRatingInfo(@Header("X-AUTH-TOKEN") String token);

    @FormUrlEncoded
    @PUT("record/bpjsInit")
    Observable<ResponseBody> initBpjs(@Header("X-AUTH-TOKEN") String token,@Field("thirdpartyTaskID") String taskID);
}
