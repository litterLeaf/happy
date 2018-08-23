package com.yinshan.happycash.view.loan.view;

import com.yinshan.happycash.view.bindcard.model.BandCardBean;
import com.yinshan.happycash.view.loan.model.ApplyPurpose;

import java.util.List;

/**
 * Created by huxin on 2018/5/2.
 */

public interface ILoaningView {


    void showBindBankCard(BandCardBean bean);

    void showPurpose(List<ApplyPurpose> list);

    void submitLoanOk();

    void submitFail(String displayMessage);
}
