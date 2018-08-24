package com.yinshan.happycash.utils;

import com.yinshan.happycash.R;
import com.yinshan.happycash.view.loan.model.DepositResponseBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 *         ┃　　　┃   兽神保佑
 *         ┃　　　┃   代码无BUG！
 *         ┃　　　┗━━━┓
 *         ┃　　　　　　　┣┓
 *         ┃　　　　　　　┏┛
 *         ┗┓┓┏━┳┓┏┛
 *             ┃┫┫　┃┫┫
 *             ┗┻┛　┗┻┛
 *
 * 文件描述：还款方式管理类
 * 创建人：    admin
 * 创建时间：2018/8/24
 */
public class PaymentMethodManager {
    private  static String method;
    private  static String channel;
    private   static List<HashMap<String, Integer>> hashMaps  ;

    public  static int getPaymentStepsLayout(DepositResponseBean bean){
        if(bean==null){
            return 0;
        }
        method = bean.getDepositMethod();
        channel = bean.getDepositChannel();

        if(SPKeyUtils.CHANNEL_FASPAY.equals(channel)){
            return getlayout(method);
        }else if(SPKeyUtils.CHANNEL_XENDIT.equals(channel)){
            return   getlayout(method);
        }

        return  0;
    }


    private static int getlayout(String  method){
//        int layout = R.array.other_bank_new_atm;


        return R.array.other_bank_new_atm;
    }

}
