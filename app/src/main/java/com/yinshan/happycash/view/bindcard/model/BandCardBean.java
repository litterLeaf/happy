package com.yinshan.happycash.view.bindcard.model;

import java.io.Serializable;

/**
 * Created by admin on 2018/3/13.
 */

public class BandCardBean implements Serializable{

    private String bankCode;
    private String holderName;
    private String cardNo;

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}
