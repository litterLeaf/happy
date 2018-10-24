package com.yinshan.happycash.network.common.base;

import com.yinshan.happycash.application.AppContext;

/**
 * Created by huxin on 2018/3/5.
 */

public class BaseURL {

    //很重要
    public static String UPLOADDATA = "www.seanplus.com";

    /**
     * HTTP 404 错误
     */
    public static final int HTTP_404 = 404;
    /**
     * HTTP 401 错误
     */
    public static final int HTTP_401 = 401;
    /**
     * 业务成功代码：0<br>
     */
    public static final int APP_BUSINESS_SUCCESS = 0;

    public static String getBaseURL(){
        return AppContext.getAppEnv().getApiUrl();
    }
}
