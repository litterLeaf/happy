package com.yinshan.happycash.view.information.view;

import com.yinshan.happycash.view.information.model.EmploymentBean;
import com.yinshan.happycash.view.information.model.RegionsBean;
import com.yinshan.happycash.widget.inter.IBaseView;

import java.util.List;

/**
 * Created by huxin on 2018/4/24.
 */

public interface IJobView extends IBaseView{


    void showInfo(EmploymentBean bean);

    void submitOk();

    void showRegion(RegionsBean bean, int index);

    void showTelList(List<String> strs);
}
