package com.yinshan.happycash.utils;

import com.yinshan.happycash.framework.BaseLoanAppBean;

/**
 * Created by admin on 2018/2/1.
 */

public class StatusManagementUtils {

    private String mTempStatus=null;

    private String showLoanInfoFragment(BaseLoanAppBean bean) {
        String status = bean.getStatus();
        if ( ServiceLoanStatus.OVERDUE.equals(status)) {
            mTempStatus = ServiceLoanStatus.OVERDUE;
        }  else if (ServiceLoanStatus.SUBMITTED.equals(status)) {
            mTempStatus = ServiceLoanStatus.UNLOAN;
        } else if(ServiceLoanStatus.SUPPLEMENT.equals(status)){
            mTempStatus = ServiceLoanStatus.SUPPLEMENT;
        } else if(ServiceLoanStatus.PRE_REVIEW.equals(status)){
            mTempStatus = ServiceLoanStatus.PRE_REVIEW;
        } else if(ServiceLoanStatus.FIRST_REVIEW.equals(status)){
            mTempStatus = ServiceLoanStatus.FIRST_REVIEW;
        } else if(ServiceLoanStatus.SECOND_REVIEW.equals(status)){
            mTempStatus = ServiceLoanStatus.SECOND_REVIEW;
        } else if(ServiceLoanStatus.FINAL_REVIEW.equals(status)){
            mTempStatus = ServiceLoanStatus.FINAL_REVIEW;
        }else if(ServiceLoanStatus.CURRENT.equals(status)){
            mTempStatus = ServiceLoanStatus.CURRENT;
        }else if(ServiceLoanStatus.PAID_OFF.equals(status)){
            mTempStatus = ServiceLoanStatus.PAID_OFF;
        } else if(ServiceLoanStatus.CLOSED.equals(status)){
            mTempStatus = ServiceLoanStatus.CLOSED;
        } else if(ServiceLoanStatus.REJECTED.equals(status)){
            mTempStatus = ServiceLoanStatus.REJECTED;
        }
        return mTempStatus;
    }

}
