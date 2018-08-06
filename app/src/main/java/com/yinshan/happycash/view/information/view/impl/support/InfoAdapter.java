package com.yinshan.happycash.view.information.view.impl.support;

/**
 * Created by huxin on 2018/4/26.
 */

public interface InfoAdapter {

    interface InfoItem{
        String getInfoStr();
        String getValueStr();
        InfoType getType();
        int getId();
    }
}
