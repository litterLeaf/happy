package com.yinshan.happycash.view.bindcard.contract;

import com.yinshan.happycash.framework.MvpBasePresenter;
import com.yinshan.happycash.view.bindcard.model.BindCardBean;

/**
 * Created by admin on 2018/3/13.
 */

public class BindCardContract {
    public  interface View  {
        void bindCardSuccess(BindCardBean bean);

        void bindCardError(String message);
    }

    public interface Presenter extends MvpBasePresenter<View> {
        void bindCard(String userName, String password);
    }
}
