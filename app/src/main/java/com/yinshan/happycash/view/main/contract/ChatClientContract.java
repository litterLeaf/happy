package com.yinshan.happycash.view.main.contract;

import com.yinshan.happycash.framework.MvpBasePresenter;
import com.yinshan.happycash.view.main.model.HXBean;
import com.yinshan.happycash.view.main.model.LastLoanAppBean;
import com.yinshan.happycash.view.main.model.YWUser;

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

public class ChatClientContract {

    public  interface View{
        void getMessageSuccess(HXBean hxBean);

        void getMessageError(String message);

        void getChatAccountSuccess(YWUser ywUser, int flag);

        void getChatAccountFailure(String message);
    }

    public interface Presenter extends MvpBasePresenter<ChatClientContract.View>{
        void getMessage(String  tenantId);
        void getChatClientAccount(String token,int flag);
    }
}
