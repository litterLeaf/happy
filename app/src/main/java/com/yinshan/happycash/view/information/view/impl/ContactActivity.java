package com.yinshan.happycash.view.information.view.impl;

import android.app.Activity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseSingleActivity;
import com.yinshan.happycash.view.information.model.ContactBean;
import com.yinshan.happycash.view.information.model.RelationStatus;
import com.yinshan.happycash.view.information.presenter.ContactPresenter;
import com.yinshan.happycash.view.information.view.IContactView;
import com.yinshan.happycash.widget.dialog.ListDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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

    @OnClick({R.id.btnRelative1})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.btnRelative1:
                List<String> relativeList1 = new ArrayList<>();
                relativeList1.add(RelationStatus.PARENT.getValue());
                relativeList1.add(RelationStatus.SPOUSE.getValue());
                relativeList1.add(RelationStatus.SIBLING.getValue());
                ListDialog listDialog = new ListDialog(this,R.style.DialogTheme,relativeList1){
                    @Override
                    public void clickIndex(int index) {
                        showRelative1(relativeList1.get(index));
                    }
                };
                listDialog.show();
                DisplayMetrics dm = getResources().getDisplayMetrics();
                int displayWidth = dm.widthPixels;
                android.view.WindowManager.LayoutParams lp = listDialog.getWindow().getAttributes(); //获取对话框当前的参数值
                lp.width = displayWidth;
                listDialog.getWindow().setAttributes(lp);
                listDialog.getWindow().setGravity(Gravity.CENTER);
                break;
        }
    }

    @Override
    public void showContactInfo(List<ContactBean> list) {
        if(list==null)
            return;
        if(list.size()>=1){
            ContactBean contactBean = list.get(0);
            if(!TextUtils.isEmpty(contactBean.getRelation())){
                showRelative1(contactBean.getRelation());
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

    private void showRelative1(String str){
        mHintRelative1.setVisibility(View.INVISIBLE);
        mRelative1.setVisibility(View.VISIBLE);
        mRelative1.setText(str);
    }
}
