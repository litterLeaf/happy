package com.yinshan.happycash.view.login.contract;

import com.yinshan.happycash.framework.MvpBasePresenter;
import com.yinshan.happycash.view.login.model.LoginTokenResponse;

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
        void signInSuccess(LoginTokenResponse tokenResponse);

        void signInError(String message);
    }

    public interface Presenter extends MvpBasePresenter<View>{
        void signIn(String smsCode, String captchaSid, String captcha,String mobile,String inviteCode,String andridId);
    }
}
