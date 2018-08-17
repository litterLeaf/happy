package com.yinshan.happycash.view.loan.view;

import com.yinshan.happycash.network.common.base.ApiException;
import com.yinshan.happycash.view.loan.model.DepositMethodsBean;
import com.yinshan.happycash.view.loan.model.DepositResponseBean; /**
 * Created by huxin on 2018/8/17.
 */
public interface IRepaymentView {

    void getRepaymentListOk(DepositMethodsBean bean);

    void getRepaymentListFail(ApiException ex);

    void getDepositOk(DepositResponseBean bean);

    void getDepositFail(ApiException ex);
}
