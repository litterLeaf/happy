package com.yinshan.happycash.framework;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * @作者:My
 * @创建日期: 2017/3/29 16:53
 * @描述:${TODO}
 * @更新者:${Author}$
 * @更新时间:${Date}$
 * @更新描述:${TODO}
 */

public class BaseLoanAppBean implements Parcelable,Serializable {

    private float amount;
    private String bankCode;
    private String cardNo;
    private float cost;
    private String createTime;
    private String credentialNo;
    private String dueDate;
    private String loanAppId;
    private float paidAmount;
    private int period;
    private String periodUnit;
    private float remainAmount;
    private String status;
    private float totalAmount;
    private List<BaseStatusLogsBean> statusLogs;
    private int remainingDays;
    private String comments;
    private String paidOffMode;
    private float loanCostAmount;
    private String loanType;
    private String appCurrentShownStatus;
    private String appPaidoffShownStatus;

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public float getLoanCostAmount() {
        return loanCostAmount;
    }
    public void setLoanCostAmount(float loanCostAmount) {
        this.loanCostAmount = loanCostAmount;
    }


    public String getPaidOffMode() {
        return paidOffMode;
    }

    public void setPaidOffMode(String paidOffMode) {
        this.paidOffMode = paidOffMode;
    }

    public int getRemainingDays() {
        return remainingDays;
    }

    public void setRemainingDays(int remainingDays) {
        this.remainingDays = remainingDays;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
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

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
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

    public String getLoanAppId() {
        return loanAppId;
    }

    public void setLoanAppId(String loanAppId) {
        this.loanAppId = loanAppId;
    }

    public float getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(float paidAmount) {
        this.paidAmount = paidAmount;
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

    public float getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(float remainAmount) {
        this.remainAmount = remainAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<BaseStatusLogsBean> getStatusLogs() {
        return statusLogs;
    }

    public void setStatusLogs(List<BaseStatusLogsBean> statusLogs) {
        this.statusLogs = statusLogs;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getAppCurrentShownStatus() {
        return appCurrentShownStatus;
    }

    public void setAppCurrentShownStatus(String appCurrentShownStatus) {
        this.appCurrentShownStatus = appCurrentShownStatus;
    }

    public String getAppPaidoffShownStatus() {
        return appPaidoffShownStatus;
    }

    public void setAppPaidoffShownStatus(String appPaidoffShownStatus) {
        this.appPaidoffShownStatus = appPaidoffShownStatus;
    }

    //    @Override
//    public String toString() {
//        return "LoanAppBeanFather{" +
//                "loanAppId='" + loanAppId + '\'' +
//                ", status='" + status + '\'' +
//                '}';
//    }

    @Override
    public String toString() {
        return "LoanAppBeanFather{" +
                "amount=" + amount +
                ", bankCode='" + bankCode + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", cost=" + cost +
                ", createTime='" + createTime + '\'' +
                ", credentialNo='" + credentialNo + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", loanAppId='" + loanAppId + '\'' +
                ", paidAmount=" + paidAmount +
                ", period=" + period +
                ", periodUnit='" + periodUnit + '\'' +
                ", remainAmount=" + remainAmount +
                ", status='" + status + '\'' +
                ", totalAmount=" + totalAmount +
                ", statusLogs=" + statusLogs +
                ", remainingDays=" + remainingDays +
                ", comments='" + comments + '\'' +
                ", paidOffMode='" + paidOffMode + '\'' +
                ", loanCostAmount=" + loanCostAmount +
                ", loanType='" + loanType + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.amount);
        dest.writeString(this.bankCode);
        dest.writeString(this.cardNo);
        dest.writeFloat(this.cost);
        dest.writeString(this.createTime);
        dest.writeString(this.credentialNo);
        dest.writeString(this.dueDate);
        dest.writeString(this.loanAppId);
        dest.writeFloat(this.paidAmount);
        dest.writeInt(this.period);
        dest.writeString(this.periodUnit);
        dest.writeFloat(this.remainAmount);
        dest.writeString(this.status);
        dest.writeFloat(this.totalAmount);
        dest.writeTypedList(this.statusLogs);
        dest.writeInt(this.remainingDays);
        dest.writeString(this.comments);
        dest.writeString(this.paidOffMode);
        dest.writeFloat(this.loanCostAmount);
    }

    public BaseLoanAppBean() {
    }

    protected BaseLoanAppBean(Parcel in) {
        this.amount = in.readFloat();
        this.bankCode = in.readString();
        this.cardNo = in.readString();
        this.cost = in.readFloat();
        this.createTime = in.readString();
        this.credentialNo = in.readString();
        this.dueDate = in.readString();
        this.loanAppId = in.readString();
        this.paidAmount = in.readFloat();
        this.period = in.readInt();
        this.periodUnit = in.readString();
        this.remainAmount = in.readFloat();
        this.status = in.readString();
        this.totalAmount = in.readFloat();
        this.statusLogs = in.createTypedArrayList(BaseStatusLogsBean.CREATOR);
        this.remainingDays = in.readInt();
        this.comments = in.readString();
        this.paidOffMode = in.readString();
        this.loanCostAmount = in.readFloat();
    }

    public static final Parcelable.Creator<BaseLoanAppBean> CREATOR = new Parcelable.Creator<BaseLoanAppBean>() {
        @Override
        public BaseLoanAppBean createFromParcel(Parcel source) {
            return new BaseLoanAppBean(source);
        }

        @Override
        public BaseLoanAppBean[] newArray(int size) {
            return new BaseLoanAppBean[size];
        }
    };
}
