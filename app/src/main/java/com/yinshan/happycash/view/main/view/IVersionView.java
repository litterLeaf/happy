package com.yinshan.happycash.view.main.view;

import com.yinshan.happycash.view.main.model.ProfileBean; /**
 * Created by huxin on 2018/9/6.
 */
public interface IVersionView {


    void getVersionOk(ProfileBean value);

    void getVersionFail();
}
