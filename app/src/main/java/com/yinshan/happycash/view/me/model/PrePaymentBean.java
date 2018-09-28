package com.yinshan.happycash.view.me.model;

import java.util.List;

/**
 * Created by huxin on 2018/9/27.
 */
public class PrePaymentBean {

    private long loanAppId;
    private int period;
    private String periodUnit;
    private double principalAmount;
    private String  status;
    private double prepaymentFeeAmount;

    List<PreBean> lpayDtoList;


    public long getLoanAppId() {
        return loanAppId;
    }

    public void setLoanAppId(long loanAppId) {
        this.loanAppId = loanAppId;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getPeriodUnit() {
        return periodUnit;
    }

    public void setPeriodUnit(String periodUnit) {
        this.periodUnit = periodUnit;
    }

    public double getPrincipalAmount() {
        return principalAmount;
    }

    public void setPrincipalAmount(double principalAmount) {
        this.principalAmount = principalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrepaymentFeeAmount() {
        return prepaymentFeeAmount;
    }

    public void setPrepaymentFeeAmount(double prepaymentFeeAmount) {
        this.prepaymentFeeAmount = prepaymentFeeAmount;
    }

    public List<PreBean> getLpayDtoList() {
        return lpayDtoList;
    }

    public void setLpayDtoList(List<PreBean> lpayDtoList) {
        this.lpayDtoList = lpayDtoList;
    }
}
