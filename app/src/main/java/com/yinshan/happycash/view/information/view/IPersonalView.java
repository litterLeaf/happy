package com.yinshan.happycash.view.information.view;

import com.yinshan.happycash.view.information.model.PersonalBean;
import com.yinshan.happycash.view.information.model.RegionsBean;

/**
 * Created by huxin on 2018/4/19.
 */

public interface IPersonalView {

    void showInfo(PersonalBean personalBean);

    void submitPersonOk();

    void showRegion(RegionsBean bean, int index);
}
