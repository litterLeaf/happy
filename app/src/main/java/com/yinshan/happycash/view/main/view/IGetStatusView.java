package com.yinshan.happycash.view.main.view;

import com.yinshan.happycash.view.main.model.LastLoanAppBean; /**
 * Created by huxin on 2018/8/16.
 */
public interface IGetStatusView {
    void getStatusSuccess(LastLoanAppBean value);

    void getStatusError(String displayMessage);
}
