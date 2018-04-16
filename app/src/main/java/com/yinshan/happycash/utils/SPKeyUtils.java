package com.yinshan.happycash.utils;


/**
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 *        ┃　　　┃   神兽保佑
 *        ┃　　　┃   代码无BUG！
 *        ┃　　　┗━━━┓
 *        ┃　　　　　　　┣┓
 *        ┃　　　　　　　┏┛
 *        ┗┓┓┏━┳┓┏┛
 *           ┃┫┫　┃┫┫
 *           ┗┻┛　┗┻┛
 *  用来存储SharedPreference 的所有key
 *  @author  admin
 *  on 2018/1/11
 *
 */
public interface SPKeyUtils {
    /**
     * 保存cookie
     */
    String COOKIE = "cookie";
    /**
     * 服务器相应时间
     */
    String DATE = "date";
    String APP_STATUES = "loan_app_status";
    String SERVER_STATUES = "loan_server_status";
    String LOANAPPBEAN = "loanAppBean";
    String UNLOAN_FRAG="unLoanFrag";
    String LOANING_FRAG="loaningFrag";
    String PROCESS_FRAG="processFrag";
    String BUILDUP_FRAG="buildUpFrag";
    String REJECT_FRAG="rejectFrag";
    String REPAYMENT_FRAG = "repayment_fragment";
    String CERTIFICATION_TAB="certificationFrag";
    String ME_TAB="meFrag";



    String IS_KTP_PHOTO_OK = "is_ktp_photo_ok";
    String IS_WORK_PHOTO_OK = "is_work_photo_ok";
}
