package com.yinshan.happycash.network.api;

import com.yinshan.happycash.view.loan.model.ApplyLoanAppsBean;
import com.yinshan.happycash.view.loan.model.ApplyPurpose;
import com.yinshan.happycash.view.loan.model.ApplyResponseBean;
import com.yinshan.happycash.view.loan.model.DepositMethodsBean;
import com.yinshan.happycash.view.main.model.LastLoanAppBean;
import com.yinshan.happycash.view.me.model.LoanDetailBean;
import com.yinshan.happycash.view.me.model.LoanItem;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

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
    rx.Observable<ApplyLoanAppsBean> applyLoanApp(@Field("loanType") String loanType,
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

    @PUT("loanapps/{loanAppId}")
    rx.Observable<ResponseBody> resubmitLoanApp(@Path("loanAppId") long loanAppId,
                                                @Header("X-AUTH-TOKEN") String token);
}
