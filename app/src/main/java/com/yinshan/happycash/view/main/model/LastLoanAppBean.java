package com.yinshan.happycash.view.main.model;

import java.util.List;

/**
 * Created by admin on 2018/3/20.
 */

public class LastLoanAppBean {

    /**
     * amount : 0
     * bankCode : BCA
     * cardNo : string
     * comments : string
     * cost : 0
     * createTime : 2018-03-26T09:05:28.712Z
     * credentialNo : string
     * dueDate : 2018-03-26T09:05:28.712Z
     * loanAppId : 0
     * loanCostAmount : 0
     * loanType : string
     * paidAmount : 0
     * paidOffMode : DEFAULT
     * period : 0
     * periodUnit : D
     * remainAmount : 0
     * remainingDays : 0
     * status : string
     * statusLogs : [{"createTime":"2018-03-26T09:05:28.712Z","status":"string"}]
     * totalAmount : 0
     */

    private int amount;
    private String bankCode;
    private String cardNo;
    private String comments;
    private int cost;
    private String createTime;
    private String credentialNo;
    private String dueDate;
    private int loanAppId;
    private int loanCostAmount;
    private String loanType;
    private int paidAmount;
    private String paidOffMode;
    private int period;
    private String periodUnit;
    private int remainAmount;
    private int remainingDays;
    private String status;
    private int totalAmount;
    private List<StatusLogsBean> statusLogs;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
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

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getLoanAppId() {
        return loanAppId;
    }

    public void setLoanAppId(int loanAppId) {
        this.loanAppId = loanAppId;
    }

    public int getLoanCostAmount() {
        return loanCostAmount;
    }

    public void setLoanCostAmount(int loanCostAmount) {
        this.loanCostAmount = loanCostAmount;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public int getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(int paidAmount) {
        this.paidAmount = paidAmount;
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

    public String getPeriodUnit() {
        return periodUnit;
    }

    public void setPeriodUnit(String periodUnit) {
        this.periodUnit = periodUnit;
    }

    public int getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(int remainAmount) {
        this.remainAmount = remainAmount;
    }

    public int getRemainingDays() {
        return remainingDays;
    }

    public void setRemainingDays(int remainingDays) {
        this.remainingDays = remainingDays;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<StatusLogsBean> getStatusLogs() {
        return statusLogs;
    }

    public void setStatusLogs(List<StatusLogsBean> statusLogs) {
        this.statusLogs = statusLogs;
    }

    public static class StatusLogsBean {
        /**
         * createTime : 2018-03-26T09:05:28.712Z
         * status : string
         */

        private String createTime;
        private String status;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
