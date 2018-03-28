package com.yinshan.happycash.utils;

/**
 * Created by admin on 2018/2/1.
 */

public interface ServiceLoanStatus {
      String SUBMITTED = "SUBMITTED";
      String SUPPLEMENT = "SUPPLEMENT";
      String CURRENT   = "CURRENT";
      String WITHDRAWN = "WITHDRAWN";
      String OVERDUE   = "OVERDUE";
      String PAID_OFF  = "PAID_OFF";
      String REJECTED     = "REJECTED";
      String ISSUING     = "ISSUING";
      String CLOSED     = "CLOSED";
      String PRE_REVIEW = "PRE_REVIEW";
      String FIRST_REVIEW = "FIRST_REVIEW";
      String SECOND_REVIEW = "SECOND_REVIEW";
      String FINAL_REVIEW = "FINAL_REVIEW";
}
