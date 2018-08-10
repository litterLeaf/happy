package com.yinshan.happycash.view.login.contract;

import com.yinshan.happycash.framework.MvpBasePresenter;
import com.yinshan.happycash.view.login.model.LoginTokenResponse;

import okhttp3.ResponseBody;

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
 *  @author  admin
 *  on 2018/1/31
 *
 */

public class LoginContract {

   public  interface View {
        void signInSuccess(String mobile, LoginTokenResponse tokenResponse);

        void signInError(String message);

       void getSMSCodeSuccess(ResponseBody responseBody);
   }

    public interface Presenter extends MvpBasePresenter<View>{
        void signIn(String smsCode, String captchaSid, String captcha,String mobile,String inviteCode,String andridId);
        void sendSms(String mobile);
   }
}
