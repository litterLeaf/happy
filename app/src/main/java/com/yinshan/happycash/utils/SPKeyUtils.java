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

    String UNLOAN_FRAG="unLoanFrag";
    String LOANING_FRAG="loaningFrag";
    String PROCESS_FRAG="processFrag";
    String BUILDUP_FRAG="buildUpFrag";
    String REJECT_FRAG="rejectFrag";
    String REPAYMENT_FRAG = "repayment_fragment";
    String CERTIFICATION_TAB="certificationFrag";
    String ME_TAB="meFrag";
}
