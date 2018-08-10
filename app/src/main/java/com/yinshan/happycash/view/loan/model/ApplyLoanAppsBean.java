package com.yinshan.happycash.view.loan.model;

/**
 * Created by huxin on 2018/4/19.
 */

public class ApplyLoanAppsBean {

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getValidCount() {
        return validCount;
    }

    public void setValidCount(int failCount) {
        this.validCount = failCount;
    }

    private String result;
    private String message;
    private int validCount  ;

    @Override
    public String toString() {
        return "ApplyLoanAppBean{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", vaildCount=" + validCount +
                '}';
    }

}
