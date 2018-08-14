package com.yinshan.happycash.network.api;

import com.yinshan.happycash.view.information.model.ContactBean;
import com.yinshan.happycash.view.information.model.EmploymentBean;
import com.yinshan.happycash.view.information.model.PersonalBean;
import com.yinshan.happycash.view.information.model.RecordFilesResponse;
import com.yinshan.happycash.view.loan.model.ApplyLoanAppsBean;
import com.yinshan.happycash.view.loan.model.ApplyPurpose;
import com.yinshan.happycash.view.loan.model.ApplyResponseBean;
import com.yinshan.happycash.view.loan.model.BankInfoBean;
import com.yinshan.happycash.view.loan.model.DepositMethodsBean;
import com.yinshan.happycash.view.loan.model.DepositResponseBean;
import com.yinshan.happycash.view.main.model.LastLoanAppBean;
import com.yinshan.happycash.view.me.model.LoanDetailBean;
import com.yinshan.happycash.view.me.model.LoanItem;

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
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by huxin on 2018/3/26.
 */

public interface LoanApi {

    @GET("loanapps")
    Observable<List<LoanItem>> getLoanList(@Header("X-AUTH-TOKEN") String token);

    /**
     * 提交 申请
     * @param loanType
     * @param amount
     * @param period
     * @param periodUnit
     * @param bankCode
     * @param cardNo
     * @param applyFor
     * @param applyChannel
     * @param applyPlatform
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("loanapps")
    Observable<ApplyLoanAppsBean> applyLoanApp(@Field("loanType") String loanType,
                                                  @Field("amount") String amount,
                                                  @Field("period") String period,
                                                  @Field("periodUnit") String periodUnit,
                                                  @Field("bankCode") String bankCode,
                                                  @Field("cardNo") String cardNo,
                                                  @Field("holderName") String holderName,
                                                  @Field("applyPurpose") String applyPurpose,
                                                  @Field("applyFor") String applyFor,
                                                  @Field("applyChannel") String applyChannel,
                                                  @Field("applyPlatform") String applyPlatform,
                                                  @Field("imei") String imei,
                                                  @Header("X-AUTH-TOKEN") String token,
                                                  @Header("X-SMS-CODE") String smsCode
    );

    @GET("loanapps/{loanAppId}/detail")
    Observable<LoanDetailBean> getLoanDetail(@Header("X-AUTH-TOKEN") String token,
                                             @Path("loanAppId") long loanAppId);

    @GET("loanapps/deposit/methods/v5")
    Observable<DepositMethodsBean> getDepostMethods(@Header("X-AUTH-TOKEN") String token);

    @GET("loanapps/latest")
    Observable<LastLoanAppBean> getLatestLoanApp(@Header("X-AUTH-TOKEN") String token);

    /**
     * 申请动机
     * @param token
     * @return
     */
    @GET("loanapps/purposes")
    Observable<List<ApplyPurpose>> getApplyPurposes(@Header("X-AUTH-TOKEN")  String token);

    @GET("loanapps/qualification")
    Observable<ApplyResponseBean> isQualification(@Header("X-AUTH-TOKEN") String token);

    @PATCH("loanapps/{loanAppId}")
    Observable<ResponseBody> cancelLoanApp(@Path("loanAppId") long loanAppId,
                                                @Header("X-AUTH-TOKEN") String token);

    @PUT("loanapps/{loanAppId}")
    Observable<ResponseBody> resubmitLoanApp(@Path("loanAppId") long loanAppId,
                                                @Header("X-AUTH-TOKEN") String token);

    @GET("loanapps/{loanAppId}/bankcard")
    Observable<BankInfoBean> getLoanBankCard(@Path("loanAppId") long loanAppId,
                                             @Header("X-AUTH-TOKEN") String token);

    @FormUrlEncoded
    @PUT("loanapps/{loanAppId}/bankcard")
    Observable<ResponseBody> updateBankCard(@Path("loanAppId") long loanAppId,
                                               @Field("bankCode") String bankCode,
                                               @Field("cardNo") String cardNo,
                                               @Field("holderName") String holderName,
                                               @Header("X-AUTH-TOKEN") String token);

    @GET("loanapps/{loanAppId}/contacts")
    Observable<List<ContactBean>> getLoanContact(@Path("loanAppId") long loanAppId,
                                                 @Header("X-AUTH-TOKEN") String token);

    @FormUrlEncoded
    @PUT("loanapps/{loanAppId}/contacts")
    Observable<ResponseBody> updateBuildUpContact(@Path("loanAppId") long loanAppId,
                                                  @Field("firstContactName") String firstContactName,
                                                  @Field("firstContactMobile") String firstContactMobile,
                                                  @Field("firstContactRelation") String firstContactRelation,
                                                  @Field("secondContactName") String secondContactName,
                                                  @Field("secondContactMobile") String secondContactMobile,
                                                  @Field("secondContactRelation") String secondContactRelation,
                                                  @Header("X-AUTH-TOKEN") String token);

    @FormUrlEncoded
    @PUT("loanapps/{loanAppId}/contracts/bio")
    Observable<ResponseBody> uploadBio1(
                                        @Path("loanAppId") String loanAppId,
                                        @Field("bioFile") String content,
                                        @Field("sFile") String sFile,
                                        @Field("clFile") String clFile,
                                        @Field("ctFile") String ctFile,
                                        @Field("bhFile") String eFile,
                                        @Header("X-AUTH-TOKEN") String token);

    @FormUrlEncoded
    @POST("loanapps/deposit")
    Observable<DepositResponseBean> doDeposit(@Field("loanAppId") String loanAppId,
                                              @Field("currency") String currency,
                                              @Field("depositMethod") String method,
                                              @Header("X-AUTH-TOKEN") String token);

    @GET("loanapps/{loanAppId}/detail")
    Observable<LoanDetailBean> getDetail(@Path("loanAppId") long loanAppId,
                                                 @Header("X-AUTH-TOKEN") String token);

    @GET("loanapps/{loanAppId}/employment")
    Observable<EmploymentBean> getEmployment(@Path("loanAppId") long loanAppId,
                                         @Header("X-AUTH-TOKEN") String token);

    @FormUrlEncoded
    @PUT("loanapps/{loanAppId}/employment")
    Observable<ResponseBody> updateEmployment(@Path("loanAppId") long loanAppId,
                                              @Field("companyName") String companyName,
                                              @Field("companyProvince") String companyProvince,
                                              @Field("companyCity") String companyCity,
                                              @Field("companyDistrict") String companyDistrict,
                                              @Field("companyArea") String companyArea,
                                              @Field("companyAddress") String companyAddress,
                                              @Field("companyPhone") String companyPhone,
                                              @Field("profession") String profession,
                                              @Field("salary") String salary,
                                              @Field("salaryDay") String salaryDay,
                                              @Header("X-AUTH-TOKEN") String token);

    @GET("loanapps/{loanAppId}/files")
    Observable<RecordFilesResponse> getFiles(@Path("loanAppId") long loanAppId,
                                                       @Header("X-AUTH-TOKEN") String token);

    @Multipart
    @PUT("loanapps/{loanAppId}/files")
    Observable<ResponseBody> uploadPhoto(
            @Path("loanAppId") long loanAppId,
            @Part MultipartBody.Part photoFile,
            @Header("X-AUTH-TOKEN") String token
    );

    @GET("loanapps/{loanAppId}/personalinfo")
    Observable<PersonalBean> getPersonalInfo(@Header("X-AUTH-TOKEN") String token);

    @FormUrlEncoded
    @PUT("loanapps/{loanAppId}/personalinfo")
    Observable<ResponseBody> submitPersonalInfo(
                                                @Path("loanAppId") long loanAppId,
                                                @Field("fullName") String fullName,
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
}
