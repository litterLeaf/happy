package com.yinshan.happycash.widget.inter;

/**
 * Created by huxin on 2018/8/22.
 */
public interface IBaseView {

    /**
     * 是否处理请求返回的数据
     */
    boolean isShouldHandleResponseData();

    //显示Loading界面
    void showLoadingDialog();

    //隐藏Loading界面
    void dismissLoadingDialog();
}
