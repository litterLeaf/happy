package com.yinshan.happycash.view.me.model;

import java.util.List;

/**
 * Created by huxin on 2018/3/27.
 */

public class LoanDetailBean {

    private String bankCode;
    private String cardNo;
    private String createTime;
    private String credentialNo;
//    private String dueDate;
    private String issueDate;
    private long loanAppId;
    private String paidOffDate;
    private String paidOffMode;
    private int period;
    private String periodUnit;
    private double principalAmount;
    private long reapplyCounterDown;
    private String  status;
    private boolean c;
    boolean qualification;
    List<StageBean> lpayDtoList;


    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCredentialNo() {
        return credentialNo;
    }

    public void setCredentialNo(String credentialNo) {
        this.credentialNo = credentialNo;
    }

//    public String getDueDate() {
//        return dueDate;
//    }

//    public void setDueDate(String dueDate) {
//        this.dueDate = dueDate;
//    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public long getLoanAppId() {
        return loanAppId;
    }

    public void setLoanAppId(long loanAppId) {
        this.loanAppId = loanAppId;
    }

    public String getPaidOffDate() {
        return paidOffDate;
    }

    public void setPaidOffDate(String paidOffDate) {
        this.paidOffDate = paidOffDate;
    }

    public String getPaidOffMode() {
        return paidOffMode;
    }

    public void setPaidOffMode(String paidOffMode) {
        this.paidOffMode = paidOffMode;
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

    public List<StageBean> getLpayDtoList() {
        return lpayDtoList;
    }

    public void setLpayDtoList(List<StageBean> lpayDtoList) {
        this.lpayDtoList = lpayDtoList;
    }

    public String getPeriodUnit() {
        return periodUnit;
    }

    public void setPeriodUnit(String periodUnit) {
        this.periodUnit = periodUnit;
    }

    public long getReapplyCounterDown() {
        return reapplyCounterDown;
    }

    public void setReapplyCounterDown(long reapplyCounterDown) {
        this.reapplyCounterDown = reapplyCounterDown;
    }

    public boolean isQualification() {
        return qualification;
    }

    public void setQualification(boolean qualification) {
        this.qualification = qualification;
    }
}
