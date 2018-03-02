package com.yinshan.happycash.utils;

/**
 * Created by admin on 2018/2/1.
 */

public interface FieldParams {
    class LoanStatus {
        public static final String SUBMITTED = "SUBMITTED";
        public static final String SUPPLEMENT = "SUPPLEMENT";
        public static final String CURRENT   = "CURRENT";
        public static final String WITHDRAWN = "WITHDRAWN";
        public static final String OVERDUE   = "OVERDUE";
        public static final String PAID_OFF  = "PAID_OFF";
        public static final String REJECTED     = "REJECTED";
        public static final String ISSUING     = "ISSUING";
        public static final String CLOSED     = "CLOSED";
        public static final String PRE_REVIEW = "PRE_REVIEW";
        public static final String FIRST_REVIEW = "FIRST_REVIEW";
        public static final String SECOND_REVIEW = "SECOND_REVIEW";
        public static final String FINAL_REVIEW = "FINAL_REVIEW";
    }
}
