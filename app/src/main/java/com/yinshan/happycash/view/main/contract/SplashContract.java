package com.yinshan.happycash.view.main.contract;

import com.yinshan.happycash.framework.MvpBasePresenter;
import com.yinshan.happycash.view.main.model.LastLoanAppBean;

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
 *
 *  @author  admin
 *  on 2018/1/31
 *
 */

public class SplashContract {

   public  interface View{
        void getStatusSuccess(LastLoanAppBean latestLoanAppBean);

        void getStatusError(String message);
    }

    public interface Presenter extends MvpBasePresenter<View>{
        void getLastLoanAppBean(String token);
    }
}
