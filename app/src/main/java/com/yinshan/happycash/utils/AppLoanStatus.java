package com.yinshan.happycash.utils;

/**
 * Created by admin on 2018/2/1.
 */

public interface AppLoanStatus {
        // 未贷款
        String  UNLOAN = "UNLOAN";
        // 检查
        String  REVIEW = "REVIEW";
        // 还款
        String  REPAYMENT = "REPAYMENT";
        // 逾期
        String  OVERDUE = "OVERDUE";//暂时不用
        // 拒绝
        String  REJECT = "REJECTED";
        // 补充复审
        String REVIEW_SUPPLEMENT = "REVIEW_SUPPLEMENT";
        // 递交
        String SUBMITTED = "SUBMITTED";
}
