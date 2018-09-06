package com.yinshan.happycash.view.loan.model;

/**
 * Created by huxin on 2018/4/20.
 */

public class DepositResponseBean {

    /**
     * currency : IDR
     * depositChannel : BLUEPAY
     * depositId : 0
     * depositMethod : ALFAMART
     * operatorId : string
     * paymentCode : string
     * price : 0
     * productId : string
     */

    private String currency;
    private String depositChannel;
    private String depositId;
    private String depositMethod;
    private String operatorId;
    private String paymentCode;
    private long amount;
    private String productId;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDepositChannel() {
        return depositChannel;
    }

    public void setDepositChannel(String depositChannel) {
        this.depositChannel = depositChannel;
    }

    public String getDepositId() {
        return depositId;
    }

    public void setDepositId(String depositId) {
        this.depositId = depositId;
    }

    public String getDepositMethod() {
        return depositMethod;
    }

    public void setDepositMethod(String depositMethod) {
        this.depositMethod = depositMethod;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "DepositResponseBean{" +
                "currency='" + currency + '\'' +
                ", depositChannel='" + depositChannel + '\'' +
                ", depositId='" + depositId + '\'' +
                ", depositMethod='" + depositMethod + '\'' +
                ", operatorId='" + operatorId + '\'' +
                ", paymentCode='" + paymentCode + '\'' +
                ", amount=" + amount +
                ", productId='" + productId + '\'' +
                '}';
    }
}
