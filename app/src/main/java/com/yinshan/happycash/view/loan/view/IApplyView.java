package com.yinshan.happycash.view.loan.view;

import com.yinshan.happycash.view.me.model.LoanDetailBean;
import com.yinshan.happycash.widget.inter.IBaseView;

/**
 * Created by huxin on 2018/4/3.
 */

public interface IApplyView extends IBaseView{

    void showDetailOk(LoanDetailBean value);

    void showDetailFail(String displayMessage);

    void cancelOk();

    void cancelFail();
}
