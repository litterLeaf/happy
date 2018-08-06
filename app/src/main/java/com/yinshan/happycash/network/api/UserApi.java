package com.yinshan.happycash.network.api;


import com.yinshan.happycash.view.login.model.LoginTokenResponse;
import com.yinshan.happycash.view.main.model.LastLoanAppBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 *        ┃　　　┃   神兽保佑
 *        ┃　　　┃   代码无BUG！
 *        ┃　　　┗━━━┓
 *        ┃　　　　　　　┣┓
 *        ┃　　　　　　　┏┛
 *        ┗┓┓┏━┳┓┏┛
 *           ┃┫┫　┃┫┫
 *           ┗┻┛　┗┻┛
 *
 *  所有的Api接口
 *  @author  admin
 *  on 2018/1/11
 *
 */
public interface UserApi {

    @FormUrlEncoded
    @POST("auth/login")
    Observable<LoginTokenResponse> login(@Header("X-SMS-CODE") String smsCode,
                                         @Header("X-CAPTCHA-SID") String captchaSid,
                                         @Header("X-CAPTCHA") String captcha,
                                         @Field("mobile") String mobile,
                                         @Field("code") String code,
                                         @Field("channelKey") String channelKey,
                                         @Field("ip") String ip,
                                         @Field("imei") String imei);

    /**
     * 20170726  fix auth/login/sms-->auth/login/sms/v2
     * @param mobile
     * @return
     */
    @FormUrlEncoded
    @POST("auth/login/sms/")
    Observable<ResponseBody> sendSms(@Field("mobile") String mobile);

    /**
     * 20170726  fix auth/login/sms-->auth/login/sms/v2
     * @param mobile
     * @return
     */
    @FormUrlEncoded
    @POST("auth/login/sms/voice")
    Observable<ResponseBody> sendVoice(@Field("mobile") String mobile);

    @FormUrlEncoded
    @POST("auth/login/{mobile}/gesture")
    Observable<LoginTokenResponse> loginWithGesturePwd(@Path("mobile") String mobile,
                                                          @Field("gesturePassword") String gesturePassword);

    @GET("auth/passwords/{mobile}/gesture/exists")
    Observable<Boolean> hasGesturePasswordSet(@Path("mobile") String mobile,
                                                 @Header("X-AUTH-TOKEN") String token);

    @FormUrlEncoded
    @POST("auth/passwords/{mobile}/gesture/reset")
    Observable<Boolean> resetGesturePassword(@Path("mobile") String mobile,
                                                @Field("gesturePassword") String gesturePassword,
                                                @Header("X-AUTH-TOKEN") String token);

    @FormUrlEncoded
    @POST("auth/logout")
    Observable<ResponseBody> logout(@Field("token") String token);
}
