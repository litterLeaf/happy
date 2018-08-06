package com.yinshan.happycash.view.loan.model;

/**
 * Created by admin on 2017/12/28.
 */

public class BuildUpReasonItem {

    String code;
    String reason;
    String detail;

    public BuildUpReasonItem(String code, String reason, String detail) {
        this.code = code;
        this.reason = reason;
        this.detail = detail;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
