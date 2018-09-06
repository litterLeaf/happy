package com.yinshan.happycash.utils;

import com.yinshan.happycash.R;
import com.yinshan.happycash.application.AppContext;

/**
 * Created by huxin on 2018/8/30.
 */
public class StatusManagerUtil {


    public static String getExplainStatus(String status,String mode){
        String explain = "";
        if("PAID_OFF".equals(status)&&"ROLLOVER".equals(mode)){
            explain = AppContext.getContext().getString(R.string.rollover);
        }
        return explain;
    }

    public static String getExplainStatus(String status){
        String explain = "";
        if("FIRST_REVIEW".equals(status)){
            explain = AppContext.getContext().getString(R.string.FIRST_REVIEW);
        }else if("SECOND_REVIEW".equals(status)){
            explain = AppContext.getContext().getString(R.string.FIRST_REVIEW);
        }else if("FINAL_REVIEW".equals(status)){
            explain = AppContext.getContext().getString(R.string.FIRST_REVIEW);
        }else if("APPROVED".equals(status)){
            explain = AppContext.getContext().getString(R.string.Approved);
        }else if("REJECTED".equals(status)){
            explain = AppContext.getContext().getString(R.string.Rejected);
        }else if("CLOSED".equals(status)){
            explain = AppContext.getContext().getString(R.string.Closed);
        }else if("WITHDRAWN".equals(status)){
            explain = AppContext.getContext().getString(R.string.Withdrawn);
        }else if("READY_TO_ISSUE".equals(status)){
            explain = AppContext.getContext().getString(R.string.Ready_to_issue);
        }else if("ISSUING".equals(status)){
            explain = AppContext.getContext().getString(R.string.Issuing);
        }else if("CURRENT".equals(status)){
            explain = AppContext.getContext().getString(R.string.Current);
        }else if("ISSUE_FAILED".equals(status)){
            explain = AppContext.getContext().getString(R.string.Issue_Failed);
        }else if("PAID_OFF".equals(status)){
            explain = AppContext.getContext().getString(R.string.Paid_Off);
        }else if("GRACE_PERIOD".equals(status)){
            explain = AppContext.getContext().getString(R.string.Grace_Period);
        }else if("OVERDUE".equals(status)){
            explain = AppContext.getContext().getString(R.string.Overdue);
        }else if("BAD_DEBIT".equals(status)){
            explain = AppContext.getContext().getString(R.string.bad_debt);
        }
        return explain;
    }
}
