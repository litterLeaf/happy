package com.yinshan.happycash.view.me.model;

import java.util.List;

/**
 * Created by huxin on 2018/9/27.
 */
public class PrePaymentBean {

    private long loanAppId;

    private double advanceFeeAmount;

    List<PreBean> lpayDtoList;


    public long getLoanAppId() {
        return loanAppId;
    }

    public void setLoanAppId(long loanAppId) {
        this.loanAppId = loanAppId;
    }

    public List<PreBean> getLpayDtoList() {
        return lpayDtoList;
    }

    public void setLpayDtoList(List<PreBean> lpayDtoList) {
        this.lpayDtoList = lpayDtoList;
    }

    public double getAdvanceFeeAmount() {
        return advanceFeeAmount;
    }

    public void setAdvanceFeeAmount(double advanceFeeAmount) {
        this.advanceFeeAmount = advanceFeeAmount;
    }
}
