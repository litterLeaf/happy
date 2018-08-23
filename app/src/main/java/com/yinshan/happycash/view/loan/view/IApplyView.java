package com.yinshan.happycash.view.loan.view;

import com.yinshan.happycash.view.me.model.LoanDetailBean; /**
 * Created by huxin on 2018/4/3.
 */

public interface IApplyView {

    void showDetailOk(LoanDetailBean value);

    void showDetailFail(String displayMessage);

    void cancelOk();

    void cancelFail();
}
