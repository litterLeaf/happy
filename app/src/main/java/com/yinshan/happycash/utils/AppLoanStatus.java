package com.yinshan.happycash.utils;

/**
 * Created by admin on 2018/2/1.
 */

public interface AppLoanStatus {
        String  UNLOAN = "UNLOAN";
        String  REVIEW = "REVIEW";
        String  REPAYMENT = "REPAYMENT";
        String  OVERDUE = "OVERDUE";//暂时不用
        String  REJECT = "REJECTED";
        String REVIEW_SUPPLEMENT = "REVIEW_SUPPLEMENT";

        String SUBMITTED = "SUBMITTED";
}
