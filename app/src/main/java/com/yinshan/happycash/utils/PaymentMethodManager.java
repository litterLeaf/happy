package com.yinshan.happycash.utils;

import android.support.annotation.NonNull;

import com.yinshan.happycash.R;
import com.yinshan.happycash.view.loan.model.DepositResponseBean;

import org.apache.commons.collections.map.HashedMap;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    private   static HashMap<String, Integer> maps ;
    public static  PaymentMethodManager instance;


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
        for (String key : getHashMaps().keySet()) {
            if(key.equals(method)){
                return getHashMaps().get(key);
            }
        }
        return 0;
    }

    /**
     * ALFAMART, MANDIRI, BNI, BRI, OTHERS
     * @return  maps
     */
    private static HashMap<String,Integer> getHashMaps(){
        maps =new HashMap();
        maps.put("OTHERS",R.array.other_bank_new_atm);
        maps.put("ALFAMART",R.array.fas_pay_in_alfamart);
        maps.put("MANDIRI",R.array.other_bank_new_atm);
        maps.put("BNI",R.array.bni_atm);
        maps.put("BRI",R.array.bri_atm);
        return  maps;
    }
}
