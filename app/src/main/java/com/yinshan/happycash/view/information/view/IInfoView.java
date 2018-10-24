package com.yinshan.happycash.view.information.view;

import com.yinshan.happycash.view.information.model.ProgressBean;
import com.yinshan.happycash.widget.inter.IBaseView;

/**
 * Created by huxin on 2018/4/10.
 */

public interface IInfoView extends IBaseView{
    void showProgress(ProgressBean bean);
}
