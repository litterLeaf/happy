package com.yinshan.happycash.view.me.model;

/**
 * Created by huxin on 2018/3/27.
 */

public class StageBean {

    private double defaultAccr;
    private double defaultPaid;
    private String dueDate;
    private double interestAccr;
    private double interestPaid;
    private double principalAccr;
    private double principalPaid;

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

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
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
}
