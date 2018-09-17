package com.yinshan.happycash.utils;

import com.yinshan.happycash.framework.BaseLoanAppBean;

/**
 * Created by admin on 2018/2/1.
 */

public class StatusManagementUtils {

    private static String mTempStatus=null;

    public static String loanStatusClassify(BaseLoanAppBean bean) {
        String status = bean.getStatus();
        if ( ServiceLoanStatus.OVERDUE.equals(status)) {
            mTempStatus = AppLoanStatus.REPAYMENT;
        }  else if (ServiceLoanStatus.SUBMITTED.equals(status)) {
            mTempStatus = AppLoanStatus.UNLOAN;
        } else if(ServiceLoanStatus.SUPPLEMENT.equals(status)){
            mTempStatus = AppLoanStatus.REVIEW_SUPPLEMENT;
        } else if(ServiceLoanStatus.PRE_REVIEW.equals(status)){
            mTempStatus = AppLoanStatus.REVIEW;
        } else if(ServiceLoanStatus.FIRST_REVIEW.equals(status)){
            mTempStatus = AppLoanStatus.REVIEW;
        } else if(ServiceLoanStatus.SECOND_REVIEW.equals(status)){
            mTempStatus = AppLoanStatus.REVIEW;
        } else if(ServiceLoanStatus.FINAL_REVIEW.equals(status)){
            mTempStatus = AppLoanStatus.REVIEW;
        }else if(ServiceLoanStatus.ISSUING.equals(status)){
            mTempStatus = AppLoanStatus.REVIEW;
        } else if(ServiceLoanStatus.CURRENT.equals(status)){
            mTempStatus = AppLoanStatus.REPAYMENT;
        }else if(ServiceLoanStatus.PAID_OFF.equals(status)){
            mTempStatus = AppLoanStatus.UNLOAN;
        } else if(ServiceLoanStatus.CLOSED.equals(status)){
            mTempStatus = AppLoanStatus.UNLOAN;
        } else if(ServiceLoanStatus.REJECTED.equals(status)){
            mTempStatus = AppLoanStatus.REJECT;
        }else {
            mTempStatus = AppLoanStatus.UNLOAN;
        }
        return mTempStatus;
    }


    public static String loanBtnStatus(BaseLoanAppBean bean) {
        String status = bean.getStatus();
        if ( ServiceLoanStatus.OVERDUE.equals(status)) {
            mTempStatus = AppLoanStatus.REPAYMENT;
        } else if(ServiceLoanStatus.SUPPLEMENT.equals(status)){
            mTempStatus = AppLoanStatus.REVIEW_SUPPLEMENT;
        } else if(ServiceLoanStatus.PRE_REVIEW.equals(status)){
            mTempStatus = AppLoanStatus.REVIEW;
        } else if(ServiceLoanStatus.FIRST_REVIEW.equals(status)){
            mTempStatus = AppLoanStatus.REVIEW;
        } else if(ServiceLoanStatus.SECOND_REVIEW.equals(status)){
            mTempStatus = AppLoanStatus.REVIEW;
        } else if(ServiceLoanStatus.FINAL_REVIEW.equals(status)){
            mTempStatus = AppLoanStatus.REVIEW;
        }else if(ServiceLoanStatus.ISSUING.equals(status)){
            mTempStatus = AppLoanStatus.REVIEW;
        } else if(ServiceLoanStatus.CURRENT.equals(status)){
            mTempStatus = AppLoanStatus.REPAYMENT;
        }else if(ServiceLoanStatus.PAID_OFF.equals(status)){
            mTempStatus = AppLoanStatus.UNLOAN;
        } else if(ServiceLoanStatus.CLOSED.equals(status)){
            mTempStatus = AppLoanStatus.UNLOAN;
        } else if(ServiceLoanStatus.REJECTED.equals(status)){
            mTempStatus = AppLoanStatus.REJECT;
        }else {
            mTempStatus = AppLoanStatus.UNLOAN;
        }
        return mTempStatus;
    }
}
