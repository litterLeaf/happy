package com.yinshan.happycash.view.me.model;

/**
 * Created by huxin on 2018/3/26.
 */

public class LoanItem {

    private long loanAppId;
    private String createTime;
    private int period;
    private String periodUnit;// M month
    private double principalAmount;
    private String status;
    private String paidOffMode;

    public long getLoanAppId() {
        return loanAppId;
    }

    public void setLoanAppId(long loanAppId) {
        this.loanAppId = loanAppId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
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

    public String getPaidOffMode() {
        return paidOffMode;
    }

    public void setPaidOffMode(String paidOffMode) {
        this.paidOffMode = paidOffMode;
    }

    public String getPeriodUnit() {
        return periodUnit;
    }

    public void setPeriodUnit(String periodUnit) {
        this.periodUnit = periodUnit;
    }

    @Override
    public String toString() {
        return "LoanAppBriefDto{" +
                "loanAppId=" + loanAppId +
                ", createTime='" + createTime + '\'' +
                ", period=" + period +
                ", principalAmount=" + principalAmount +
                ", status='" + status + '\'' +
                ", paidOffMode='" + paidOffMode + '\'' +
                '}';
    }
}
