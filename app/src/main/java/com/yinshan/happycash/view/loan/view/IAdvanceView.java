package com.yinshan.happycash.view.loan.view;

import com.yinshan.happycash.view.me.model.PrePaymentBean;
import com.yinshan.happycash.widget.inter.IBaseView;

/**
 * Created by huxin on 2018/9/28.
 */
public interface IAdvanceView extends IBaseView{


    void showAdvanceFail(String displayMessage);

    void showAdvance(PrePaymentBean bean);
}
