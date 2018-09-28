package com.yinshan.happycash.view.me.model;

import java.math.BigDecimal;

/**
 * Created by huxin on 2018/9/27.
 */
public class PreBean {

    private double principalAccr;
    private double principalPaid;

    private double defaultAccr;
    private double defaultPaid;

    private double interestAccr;
    private double interestPaid;

    private double advanceFeeAccr;
    private double advanceFeePaid;

    private int currentPeriod;
    private String paidOffMode;

    private String dueDate;
    private String status;

    public double getPrincipalAccr() {
        return principalAccr;
    }

    public void setPrincipalAccr(double principalAccr) {
        this.principalAccr = principalAccr;
    }

    public double getPrincipalPaid() {
        return principalPaid;
    }

    public void setPrincipalPaid(double principalPaid) {
        this.principalPaid = principalPaid;
    }

    public double getDefaultAccr() {
        return defaultAccr;
    }

    public void setDefaultAccr(double defaultAccr) {
        this.defaultAccr = defaultAccr;
    }

    public double getDefaultPaid() {
        return defaultPaid;
    }

    public void setDefaultPaid(double defaultPaid) {
        this.defaultPaid = defaultPaid;
    }

    public double getInterestAccr() {
        return interestAccr;
    }

    public void setInterestAccr(double interestAccr) {
        this.interestAccr = interestAccr;
    }

    public double getInterestPaid() {
        return interestPaid;
    }

    public void setInterestPaid(double interestPaid) {
        this.interestPaid = interestPaid;
    }

    public double getAdvanceFeeAccr() {
        return advanceFeeAccr;
    }

    public void setAdvanceFeeAccr(double advanceFeeAccr) {
        this.advanceFeeAccr = advanceFeeAccr;
    }

    public double getAdvanceFeePaid() {
        return advanceFeePaid;
    }

    public void setAdvanceFeePaid(double advanceFeePaid) {
        this.advanceFeePaid = advanceFeePaid;
    }

    public int getCurrentPeriod() {
        return currentPeriod;
    }

    public void setCurrentPeriod(int currentPeriod) {
        this.currentPeriod = currentPeriod;
    }

    public String getPaidOffMode() {
        return paidOffMode;
    }

    public void setPaidOffMode(String paidOffMode) {
        this.paidOffMode = paidOffMode;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
