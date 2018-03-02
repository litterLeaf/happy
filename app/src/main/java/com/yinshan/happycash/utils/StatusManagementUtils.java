package com.yinshan.happycash.utils;

import com.yinshan.happycash.framework.BaseLoanAppBean;

/**
 * Created by admin on 2018/2/1.
 */

public class StatusManagementUtils {

    private LoanStatus mTempStatus=null;

    private LoanStatus showLoanInfoFragment(BaseLoanAppBean bean) {
        String status = bean.getStatus();
        if ( FieldParams.LoanStatus.OVERDUE.equals(status)) {
            mTempStatus = LoanStatus.OVERDUE;
        }  else if (FieldParams.LoanStatus.SUBMITTED.equals(status)) {
            mTempStatus = LoanStatus.UNLOAN;
        } else if(FieldParams.LoanStatus.SUPPLEMENT.equals(status)){
            mTempStatus = LoanStatus.SUPPLEMENT;
        } else if(FieldParams.LoanStatus.PRE_REVIEW.equals(status)){
            mTempStatus = LoanStatus.PRE_REVIEW;
        } else if(FieldParams.LoanStatus.FIRST_REVIEW.equals(status)){
            mTempStatus = LoanStatus.FIRST_REVIEW;
        } else if(FieldParams.LoanStatus.SECOND_REVIEW.equals(status)){
            mTempStatus = LoanStatus.SECOND_REVIEW;
        } else if(FieldParams.LoanStatus.FINAL_REVIEW.equals(status)){
            mTempStatus = LoanStatus.FINAL_REVIEW;
        }else if(FieldParams.LoanStatus.CURRENT.equals(status)){
            mTempStatus = LoanStatus.CURRENT;
        }else if(FieldParams.LoanStatus.PAID_OFF.equals(status)){
            mTempStatus = LoanStatus.PAID_OFF;
        } else if(FieldParams.LoanStatus.CLOSED.equals(status)){
            mTempStatus = LoanStatus.CLOSED;
        } else if(FieldParams.LoanStatus.REJECTED.equals(status)){
            mTempStatus = LoanStatus.REJECTED;
        }
        return mTempStatus;
    }

}
