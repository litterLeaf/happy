package com.yinshan.happycash.view.information.view.impl;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
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

    TextView mHintRelative1;
    TextView mRelative1;
    TextView mHintContact1;
    TextView mContact1;
    TextView mHintPhone1;
    TextView mPhone1;
    TextView mHintRelative2;
    TextView mRelative2;
    TextView mHintContact2;
    TextView mContact2;
    TextView mHintPhone2;
    TextView mPhone2;
    RelativeLayout mBtnSubmit;

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
        initUI();
        isCanSubmit();
        mPresenter = new ContactPresenter(this,this);
        mPresenter.getContactInfo();
    }

    @OnClick({R.id.btnRelative1,R.id.btnContact1,R.id.btnRelative2,R.id.btnContact2,R.id.btnSubmit})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.btnRelative1:
                List<String> relativeList1 = new ArrayList<>();
                relativeList1.add(getResources().getString(RelationStatus.PARENT.getShowString()));
                relativeList1.add(getResources().getString(RelationStatus.SPOUSE.getShowString()));
                relativeList1.add(getResources().getString(RelationStatus.SIBLING.getShowString()));
                ListDialog listDialog = new ListDialog(this,R.style.DialogTheme,relativeList1){
                    @Override
                    public void clickIndex(int index) {
                        showRelative1(relativeList1.get(index));
                    }
                };
                listDialog.show();
//                DisplayMetrics dm = getResources().getDisplayMetrics();
//                int displayWidth = dm.widthPixels;
//                android.view.WindowManager.LayoutParams lp = listDialog.getWindow().getAttributes(); //获取对话框当前的参数值
//                lp.width = displayWidth;
//                listDialog.getWindow().setAttributes(lp);
//                listDialog.getWindow().setGravity(Gravity.CENTER);
                break;
            case R.id.btnContact1:
                getContactInfo(0);
                break;
            case R.id.btnRelative2:
                List<String> relativeList2 = new ArrayList<>();
                relativeList2.add(getResources().getString(RelationStatus.CLASSMATE.getShowString()));
                relativeList2.add(getResources().getString(RelationStatus.COLLEAGUE.getShowString()));
                relativeList2.add(getResources().getString(RelationStatus.FRIEND.getShowString()));
                ListDialog listDialog2 = new ListDialog(this,R.style.DialogTheme,relativeList2){
                    @Override
                    public void clickIndex(int index) {
                        showRelative2(relativeList2.get(index));
                    }
                };
                listDialog2.show();
                break;
            case R.id.btnContact2:
                getContactInfo(1);
                break;
            case R.id.btnSubmit:
                String firstRelation = showRelationStatus(mRelative1.getText().toString());
                String secondRelation = showRelationStatus(mRelative2.getText().toString());
                mPresenter.submitContactInfo(mContact1.getText().toString(),mPhone1.getText().toString(),firstRelation,
                        mContact2.getText().toString(),mPhone2.getText().toString(),secondRelation
                        );
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
            showContact1(contactBean.getName(),contactBean.getMobile());
        }
        if(list.size()>=2){
            ContactBean contactBean = list.get(1);
            if(!TextUtils.isEmpty(contactBean.getRelation())){
                showRelative2(contactBean.getRelation());
            }
            showContact2(contactBean.getName(),contactBean.getMobile());
        }
    }

    @Override
    public void submitContactOk() {
        ContactActivity.this.setResult(Activity.RESULT_OK);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            Uri uri = data.getData();
            if(uri!=null){
                Cursor cursor = getContentResolver()
                        .query(uri,new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME},null,null,null);
                try{
                    while (cursor!=null&&cursor.moveToNext()){
                        String phone = cursor.getString(0);
                        String name = cursor.getString(1);
                        if (requestCode==0){
                            showContact1(name,phone);
                        }else if(requestCode==1){
                            showContact2(name,phone);
                        }
                    }
                }finally {
                    if(cursor!=null)
                        cursor.close();
                }
            }
        }
    }

    private void getContactInfo(int index){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},index);
        }else{
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
            if(intent.resolveActivity(getPackageManager())!=null){
                startActivityForResult(intent,index);
            }
        }
    }

    private void showRelative1(String str){
        mHintRelative1.setVisibility(View.INVISIBLE);
        mRelative1.setVisibility(View.VISIBLE);
        mRelative1.setText(str);
        isCanSubmit();
    }

    private void showRelative2(String str){
        mHintRelative2.setVisibility(View.INVISIBLE);
        mRelative2.setVisibility(View.VISIBLE);
        mRelative2.setText(str);
        isCanSubmit();
    }

    private void showContact1(String contact,String mobile){
        if(!TextUtils.isEmpty(contact)){
            mHintContact1.setVisibility(View.INVISIBLE);
            mContact1.setVisibility(View.VISIBLE);
            mContact1.setText(contact);
        }
        if(!TextUtils.isEmpty(mobile)){
            mHintPhone1.setVisibility(View.INVISIBLE);
            mPhone1.setVisibility(View.VISIBLE);
            mPhone1.setText(mobile);
        }
        isCanSubmit();
    }

    private void showContact2(String contact,String mobile){
        if(!TextUtils.isEmpty(contact)){
            mHintContact2.setVisibility(View.INVISIBLE);
            mContact2.setVisibility(View.VISIBLE);
            mContact2.setText(contact);
        }
        if(!TextUtils.isEmpty(mobile)){
            mHintPhone2.setVisibility(View.INVISIBLE);
            mPhone2.setVisibility(View.VISIBLE);
            mPhone2.setText(mobile);
        }
        isCanSubmit();
    }

    private void isCanSubmit(){
        if(!TextUtils.isEmpty(mRelative1.getText().toString())&&!TextUtils.isEmpty(mContact1.getText().toString())&&!TextUtils.isEmpty(mPhone1.getText().toString())
                &&!TextUtils.isEmpty(mRelative2.getText().toString())&&!TextUtils.isEmpty(mContact2.getText().toString())&&!TextUtils.isEmpty(mPhone2.getText().toString())){
            mBtnSubmit.setClickable(true);
            mBtnSubmit.setAlpha(0.8f);
        } else {
            mBtnSubmit.setClickable(false);
            mBtnSubmit.setAlpha(0.3f);
        }
    }

    private String showRelationStatus(String relation){
        if(relation==null)
            return "";
        String showRelation = "";
        switch (relation){
            case "PARENT":
                showRelation =getResources().getString(RelationStatus.PARENT.getShowString());
                break;
            case "FRIEND":
                showRelation =getResources().getString(RelationStatus.FRIEND.getShowString());
                break;
            case "SPOUSE":
                showRelation =getResources().getString(RelationStatus.SPOUSE.getShowString());
                break;
            case "SIBLING":
                showRelation =getResources().getString(RelationStatus.SIBLING.getShowString());
                break;
            case "COLLEAGUE":
                showRelation =getResources().getString(RelationStatus.COLLEAGUE.getShowString());
                break;
            case "CLASSMATE":
                showRelation =getResources().getString(RelationStatus.CLASSMATE.getShowString());
                break;
            case "ORANGTUA":
                showRelation =RelationStatus.PARENT.getValue();
                break;
            case "TEMAN":
                showRelation =RelationStatus.FRIEND.getValue();
                break;
            case "PASANGAN":
                showRelation =RelationStatus.SPOUSE.getValue();
                break;
            case "SAUDARA":
                showRelation =RelationStatus.SIBLING.getValue();
                break;
            case "REKAN":
                showRelation =RelationStatus.COLLEAGUE.getValue();
                break;
            case "TEMAN KELAS":
                showRelation =RelationStatus.CLASSMATE.getValue();
                break;

        }
        return showRelation;
    }

    private void initUI(){
        mHintRelative1 = (TextView)findViewById(R.id.hintRelative1);
        mRelative1 = (TextView)findViewById(R.id.textRelative1);
        mHintContact1 = (TextView)findViewById(R.id.hintContact1);

        mContact1 = (TextView)findViewById(R.id.textContact1);
        mHintPhone1 = (TextView)findViewById(R.id.hintPhone1);
        mPhone1 = (TextView)findViewById(R.id.textPhone1);
        mHintRelative2 = (TextView)findViewById(R.id.hintRelative2);
        mRelative2 = (TextView)findViewById(R.id.textRelative2);

        mHintContact2 = (TextView)findViewById(R.id.hintContact2);
        mContact2 = (TextView)findViewById(R.id.textContact2);
        mHintPhone2 = (TextView)findViewById(R.id.hintPhone2);
        mPhone2 = (TextView)findViewById(R.id.textPhone2);
        mBtnSubmit = (RelativeLayout)findViewById(R.id.btnSubmit);
    }
}
