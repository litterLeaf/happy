package com.yinshan.happycash.network.common.support;

/**
 * Created by huxin on 2018/5/10.
 */

public class QualityErrorBean {

    /**
     * error : err.auth.access.denied
     * message : 验证失败
     */

    private String error;
    private String message;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
