package com.yinshan.happycash.network.api;

import com.yinshan.happycash.config.AppEnvConfig;
import com.yinshan.happycash.network.common.base.BaseURL;

/**
 * Created by huxin on 2018/8/9.
 */
public class CaptchaApi {

    public static String formatCaptchaUrl(String sid){
        return formatCaptchaUrl(sid,200,80);
    }

    public static String formatCaptchaUrl(String sid,int width,int height){
        return String.format(Holder.captchaUrl,sid,width,height);
    }

    private static class Holder{
        private static String captchaUrl = BaseURL.getBaseURL() + "auth/captcha?sid=%1$s&width=%2$d&height=%3$d";
    }


    public static void main(String[] argv){

        System.out.println(formatCaptchaUrl("1345"));
    }
}
