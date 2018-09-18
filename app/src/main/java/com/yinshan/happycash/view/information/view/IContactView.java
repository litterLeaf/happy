package com.yinshan.happycash.view.information.view;

import com.yinshan.happycash.view.information.model.ContactBean;
import com.yinshan.happycash.widget.inter.IBaseView;

import java.util.List;

/**
 * Created by huxin on 2018/4/2.
 */

public interface IContactView extends IBaseView{


    void showContactInfo(List<ContactBean> value);

    void submitContactOk();

    void getContactInfoError(String message);
}
