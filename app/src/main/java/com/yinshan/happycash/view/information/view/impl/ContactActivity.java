package com.yinshan.happycash.view.information.view.impl;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseSingleActivity;
import com.yinshan.happycash.view.information.model.ContactBean;
import com.yinshan.happycash.view.information.presenter.ContactPresenter;
import com.yinshan.happycash.view.information.view.IContactView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by huxin on 2018/4/2.
 */

public class ContactActivity extends BaseSingleActivity implements IContactView{

    @BindView(R.id.hintRelative1)
    TextView mHintRelative1;
    @BindView(R.id.textRelative1)
    TextView mRelative1;
    @BindView(R.id.hintContact1)
    TextView mHintContact1;
    @BindView(R.id.textContact1)
    TextView mContact1;
    @BindView(R.id.hintPhone1)
    TextView mHintPhone1;
    @BindView(R.id.textPhone1)
    TextView mPhone1;
    @BindView(R.id.hintRelative2)
    TextView mHintRelative2;
    @BindView(R.id.textRelative2)
    TextView mRelative2;
    @BindView(R.id.hintContact2)
    TextView mHintContact2;
    @BindView(R.id.textContact2)
    TextView mContact2;
    @BindView(R.id.hintPhone2)
    TextView mHintPhone2;
    @BindView(R.id.textPhone2)
    TextView mPhone2;

    ContactPresenter mPresenter;

    @Override
    protected String bindTitle() {
        return getResources().getString(R.string.contact_info);
    }

    @Override
    protected int bindDownLayout() {
        return R.layout.activity_contact;
    }

    @Override
    protected void secondInit() {
        mPresenter = new ContactPresenter(this,this);
        mPresenter.getContactInfo();
    }

    @Override
    public void showContactInfo(List<ContactBean> list) {
        if(list==null)
            return;
        if(list.size()>=1){
            ContactBean contactBean = list.get(0);
            if(!TextUtils.isEmpty(contactBean.getRelation())){
                mHintRelative1.setVisibility(View.INVISIBLE);
                mRelative1.setVisibility(View.VISIBLE);
                mRelative1.setText(contactBean.getRelation());
            }
            if(!TextUtils.isEmpty(contactBean.getName())){
                mHintContact1.setVisibility(View.INVISIBLE);
                mContact1.setVisibility(View.VISIBLE);
                mContact1.setText(contactBean.getName());
            }
            if(!TextUtils.isEmpty(contactBean.getMobile())){
                mHintPhone1.setVisibility(View.INVISIBLE);
                mPhone1.setVisibility(View.VISIBLE);
                mPhone1.setText(contactBean.getMobile());
            }
        }
        if(list.size()>=2){
            ContactBean contactBean = list.get(1);
            if(!TextUtils.isEmpty(contactBean.getRelation())){
                mHintRelative2.setVisibility(View.INVISIBLE);
                mRelative2.setVisibility(View.VISIBLE);
                mRelative2.setText(contactBean.getRelation());
            }
            if(!TextUtils.isEmpty(contactBean.getName())){
                mHintContact2.setVisibility(View.INVISIBLE);
                mContact2.setVisibility(View.VISIBLE);
                mContact2.setText(contactBean.getName());
            }
            if(!TextUtils.isEmpty(contactBean.getMobile())){
                mHintPhone2.setVisibility(View.INVISIBLE);
                mPhone2.setVisibility(View.VISIBLE);
                mPhone2.setText(contactBean.getMobile());
            }
        }
    }
}
