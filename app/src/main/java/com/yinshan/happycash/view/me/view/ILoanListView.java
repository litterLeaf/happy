package com.yinshan.happycash.view.me.view;

import com.yinshan.happycash.view.me.model.LoanItem;
import com.yinshan.happycash.widget.inter.IBaseView;

import java.util.List;

/**
 * Created by huxin on 2018/3/16.
 */

public interface ILoanListView extends IBaseView{

    void showList(List<LoanItem> loanList);
}
