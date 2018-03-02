package com.yinshan.happycash.utils;

/**
 * Created by admin on 2018/2/1.
 */

public enum LoanStatus {
    UNLOAN,//没有贷款
    SUBMITTED
    ,FIRST_REVIEW
    ,SECOND_REVIEW
    ,FINAL_REVIEW
    ,PRE_REVIEW
    , SUPPLEMENT//补建
    ,CURRENT
    ,OVERDUE
    ,PAID_OFF
    ,CLOSED
    ,REJECTED
}
