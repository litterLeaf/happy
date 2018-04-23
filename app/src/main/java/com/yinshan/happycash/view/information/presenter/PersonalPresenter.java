package com.yinshan.happycash.view.information.presenter;

import android.content.Context;

import com.yinshan.happycash.view.information.view.IPersonalView;

/**
 * Created by huxin on 2018/4/19.
 */

public class PersonalPresenter {

    Context mContext;
    IPersonalView mView;

    public PersonalPresenter(Context context,IPersonalView view){
        mContext = context;
        mView = view;
    }
}
