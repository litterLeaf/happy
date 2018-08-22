package com.yinshan.happycash.view.main.view;

import com.yinshan.happycash.view.main.model.LastLoanAppBean;
import com.yinshan.happycash.widget.inter.IBaseView;

/**
 * Created by huxin on 2018/8/16.
 */
public interface IGetStatusView extends IBaseView{
    void getStatusSuccess(LastLoanAppBean value);

    void getStatusError(String displayMessage);
}
