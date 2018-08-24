package com.yinshan.happycash.view.loan.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by huxin on 2018/4/19.
 */

public class DepositMethodsBean implements Serializable{

    private List<String> depositMethods;

    public List<String> getDepositMethods() {
        return depositMethods;
    }

    public void setDepositMethods(List<String> depositMethods) {
        this.depositMethods = depositMethods;
    }

    @Override
    public String toString() {
        return "DepositMethodsBean{" +
                "depositMethods=" + depositMethods +
                '}';
    }
}
