package com.yinshan.happycash.view.information.view.impl;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseSingleActivity;
import com.yinshan.happycash.utils.SPKeyUtils;
import com.yinshan.happycash.utils.StringUtil;
import com.yinshan.happycash.view.information.model.ContactBean;
import com.yinshan.happycash.view.information.model.RelationStatus;
import com.yinshan.happycash.view.information.presenter.ContactPresenter;
import com.yinshan.happycash.view.information.view.IContactView;
import com.yinshan.happycash.widget.HappySnackBar;
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

    TextView mHintRelative3;
    TextView mRelative3;
    TextView mHintContact3;
    TextView mContact3;
    TextView mHintPhone3;
    TextView mPhone3;

    TextView mHintRelative4;
    TextView mRelative4;
    TextView mHintContact4;
    TextView mContact4;
    TextView mHintPhone4;
    TextView mPhone4;

    TextView mHintRelative5;
    TextView mRelative5;
    TextView mHintContact5;
    TextView mContact5;
    TextView mHintPhone5;
    TextView mPhone5;
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

    @OnClick({R.id.btnRelative1,R.id.btnContact1,R.id.btnRelative2,R.id.btnContact2,
            R.id.btnRelative3,R.id.btnContact3,R.id.btnRelative4,R.id.btnContact4,
            R.id.btnRelative5,R.id.btnContact5, R.id.btnSubmit})
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
            case R.id.btnRelative3:
                List<String> relativeList3 = new ArrayList<>();
                relativeList3.add(getResources().getString(RelationStatus.PARENT.getShowString()));
                relativeList3.add(getResources().getString(RelationStatus.SPOUSE.getShowString()));
                relativeList3.add(getResources().getString(RelationStatus.SIBLING.getShowString()));
                ListDialog listDialog3 = new ListDialog(this,R.style.DialogTheme,relativeList3){
                    @Override
                    public void clickIndex(int index) {
                        showRelative3(relativeList3.get(index));
                    }
                };
                listDialog3.show();
                break;
            case R.id.btnContact3:
                getContactInfo(2);
                break;
            case R.id.btnRelative4:
                List<String> relativeList4 = new ArrayList<>();
                relativeList4.add(getResources().getString(RelationStatus.CLASSMATE.getShowString()));
                relativeList4.add(getResources().getString(RelationStatus.COLLEAGUE.getShowString()));
                relativeList4.add(getResources().getString(RelationStatus.FRIEND.getShowString()));
                ListDialog listDialog4 = new ListDialog(this,R.style.DialogTheme,relativeList4){
                    @Override
                    public void clickIndex(int index) {
                        showRelative4(relativeList4.get(index));
                    }
                };
                listDialog4.show();
                break;
            case R.id.btnContact4:
                getContactInfo(3);
                break;
            case R.id.btnRelative5:
                List<String> relativeList5 = new ArrayList<>();
                relativeList5.add(getResources().getString(RelationStatus.CLASSMATE.getShowString()));
                relativeList5.add(getResources().getString(RelationStatus.COLLEAGUE.getShowString()));
                relativeList5.add(getResources().getString(RelationStatus.FRIEND.getShowString()));
                ListDialog listDialog5 = new ListDialog(this,R.style.DialogTheme,relativeList5){
                    @Override
                    public void clickIndex(int index) {
                        showRelative5(relativeList5.get(index));
                    }
                };
                listDialog5.show();
                break;
            case R.id.btnContact5:
                getContactInfo(4);
                break;

            case R.id.btnSubmit:
                String firstRelation = showRelationStatus(mRelative1.getText().toString());
                String secondRelation = showRelationStatus(mRelative2.getText().toString());
                String thirdRelation = showRelationStatus(mRelative3.getText().toString());
                String fourthRelation = showRelationStatus(mRelative4.getText().toString());
                String fifthRelation = showRelationStatus(mRelative5.getText().toString());
                mPresenter.submitContactInfo(mContact1.getText().toString(),mPhone1.getText().toString(),firstRelation,
                        mContact2.getText().toString(),mPhone2.getText().toString(),secondRelation,
                        mContact3.getText().toString(),mPhone3.getText().toString(),thirdRelation,
                        mContact4.getText().toString(),mPhone4.getText().toString(),fourthRelation,
                        mContact5.getText().toString(),mPhone5.getText().toString(),fifthRelation
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

        if(list.size()>=3){
            ContactBean contactBean = list.get(2);
            if(!TextUtils.isEmpty(contactBean.getRelation())){
                showRelative3(contactBean.getRelation());
            }
            showContact3(contactBean.getName(),contactBean.getMobile());
        }
        if(list.size()>=4){
            ContactBean contactBean = list.get(3);
            if(!TextUtils.isEmpty(contactBean.getRelation())){
                showRelative4(contactBean.getRelation());
            }
            showContact4(contactBean.getName(),contactBean.getMobile());
        }
        if(list.size()>=5){
            ContactBean contactBean = list.get(4);
            if(!TextUtils.isEmpty(contactBean.getRelation())){
                showRelative5(contactBean.getRelation());
            }
            showContact5(contactBean.getName(),contactBean.getMobile());
        }
    }

    @Override
    public void submitContactOk() {
        ContactActivity.this.setResult(Activity.RESULT_OK);
        finish();
    }

    @Override
    public void getContactInfoError(String message) {
        HappySnackBar.showSnackBar(mBtnSubmit,message, SPKeyUtils.SNACKBAR_TYPE_WORN);
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
                            if(isContactSame(name, phone, mContact2.getText().toString(), mPhone2.getText().toString())
                                    ||isContactSame(name, phone, mContact3.getText().toString(), mPhone3.getText().toString())
                                    ||isContactSame(name, phone, mContact4.getText().toString(), mPhone4.getText().toString())
                                    ||isContactSame(name, phone, mContact5.getText().toString(), mPhone5.getText().toString())
                                )
                                showContact1("","");
                            else
                                showContact1(name,phone);
                        }else if(requestCode==1){
                            if(isContactSame(name, phone, mContact1.getText().toString(), mPhone1.getText().toString())
                                    ||isContactSame(name, phone, mContact3.getText().toString(), mPhone3.getText().toString())
                                    ||isContactSame(name, phone, mContact4.getText().toString(), mPhone4.getText().toString())
                                    ||isContactSame(name, phone, mContact5.getText().toString(), mPhone5.getText().toString())
                                    )
                                showContact2("","");
                            else
                                showContact2(name,phone);
                        } else if(requestCode==2){
                            if(isContactSame(name, phone, mContact1.getText().toString(), mPhone1.getText().toString())
                                    ||isContactSame(name, phone, mContact2.getText().toString(), mPhone2.getText().toString())
                                    ||isContactSame(name, phone, mContact4.getText().toString(), mPhone4.getText().toString())
                                    ||isContactSame(name, phone, mContact5.getText().toString(), mPhone5.getText().toString())
                                    )
                                showContact3("","");
                            else
                                showContact3(name,phone);
                        } else if(requestCode==3){
                            if(isContactSame(name, phone, mContact1.getText().toString(), mPhone1.getText().toString())
                                    ||isContactSame(name, phone, mContact2.getText().toString(), mPhone2.getText().toString())
                                    ||isContactSame(name, phone, mContact3.getText().toString(), mPhone3.getText().toString())
                                    ||isContactSame(name, phone, mContact5.getText().toString(), mPhone5.getText().toString())
                                    )
                                showContact4("","");
                            else
                                showContact4(name,phone);
                        } else if(requestCode==4){
                            if(isContactSame(name, phone, mContact1.getText().toString(), mPhone1.getText().toString())
                                    ||isContactSame(name, phone, mContact2.getText().toString(), mPhone2.getText().toString())
                                    ||isContactSame(name, phone, mContact3.getText().toString(), mPhone3.getText().toString())
                                    ||isContactSame(name, phone, mContact4.getText().toString(), mPhone4.getText().toString())
                                    )
                                showContact5("","");
                            else
                                showContact5(name,phone);
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
    private void showRelative3(String str){
        mHintRelative3.setVisibility(View.INVISIBLE);
        mRelative3.setVisibility(View.VISIBLE);
        mRelative3.setText(str);
        isCanSubmit();
    }
    private void showRelative4(String str){
        mHintRelative4.setVisibility(View.INVISIBLE);
        mRelative4.setVisibility(View.VISIBLE);
        mRelative4.setText(str);
        isCanSubmit();
    }
    private void showRelative5(String str){
        mHintRelative5.setVisibility(View.INVISIBLE);
        mRelative5.setVisibility(View.VISIBLE);
        mRelative5.setText(str);
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

    private void showContact3(String contact,String mobile){
        if(!TextUtils.isEmpty(contact)){
            mHintContact3.setVisibility(View.INVISIBLE);
            mContact3.setVisibility(View.VISIBLE);
            mContact3.setText(contact);
        }
        if(!TextUtils.isEmpty(mobile)){
            mHintPhone3.setVisibility(View.INVISIBLE);
            mPhone3.setVisibility(View.VISIBLE);
            mPhone3.setText(mobile);
        }
        isCanSubmit();
    }
    private void showContact4(String contact,String mobile){
        if(!TextUtils.isEmpty(contact)){
            mHintContact4.setVisibility(View.INVISIBLE);
            mContact4.setVisibility(View.VISIBLE);
            mContact4.setText(contact);
        }
        if(!TextUtils.isEmpty(mobile)){
            mHintPhone4.setVisibility(View.INVISIBLE);
            mPhone4.setVisibility(View.VISIBLE);
            mPhone4.setText(mobile);
        }
        isCanSubmit();
    }
    private void showContact5(String contact,String mobile){
        if(!TextUtils.isEmpty(contact)){
            mHintContact5.setVisibility(View.INVISIBLE);
            mContact5.setVisibility(View.VISIBLE);
            mContact5.setText(contact);
        }
        if(!TextUtils.isEmpty(mobile)){
            mHintPhone5.setVisibility(View.INVISIBLE);
            mPhone5.setVisibility(View.VISIBLE);
            mPhone5.setText(mobile);
        }
        isCanSubmit();
    }


    private void isCanSubmit(){
        if(!TextUtils.isEmpty(mRelative1.getText().toString())&&!TextUtils.isEmpty(mContact1.getText().toString())&&!TextUtils.isEmpty(mPhone1.getText().toString())
                &&!TextUtils.isEmpty(mRelative2.getText().toString())&&!TextUtils.isEmpty(mContact2.getText().toString())&&!TextUtils.isEmpty(mPhone2.getText().toString())
                &&!TextUtils.isEmpty(mRelative3.getText().toString())&&!TextUtils.isEmpty(mContact3.getText().toString())&&!TextUtils.isEmpty(mPhone3.getText().toString())
                &&!TextUtils.isEmpty(mRelative4.getText().toString())&&!TextUtils.isEmpty(mContact4.getText().toString())&&!TextUtils.isEmpty(mPhone4.getText().toString())
                &&!TextUtils.isEmpty(mRelative5.getText().toString())&&!TextUtils.isEmpty(mContact5.getText().toString())&&!TextUtils.isEmpty(mPhone5.getText().toString())
                ){
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

        mHintRelative3 = (TextView)findViewById(R.id.hintRelative3);
        mRelative3 = (TextView)findViewById(R.id.textRelative3);
        mHintContact3 = (TextView)findViewById(R.id.hintContact3);
        mContact3 = (TextView)findViewById(R.id.textContact3);
        mHintPhone3 = (TextView)findViewById(R.id.hintPhone3);
        mPhone3 = (TextView)findViewById(R.id.textPhone3);

        mHintRelative4 = (TextView)findViewById(R.id.hintRelative4);
        mRelative4 = (TextView)findViewById(R.id.textRelative4);
        mHintContact4 = (TextView)findViewById(R.id.hintContact4);
        mContact4 = (TextView)findViewById(R.id.textContact4);
        mHintPhone4 = (TextView)findViewById(R.id.hintPhone4);
        mPhone4 = (TextView)findViewById(R.id.textPhone4);

        mHintRelative5 = (TextView)findViewById(R.id.hintRelative5);
        mRelative5 = (TextView)findViewById(R.id.textRelative5);
        mHintContact5 = (TextView)findViewById(R.id.hintContact5);
        mContact5 = (TextView)findViewById(R.id.textContact5);
        mHintPhone5 = (TextView)findViewById(R.id.hintPhone5);
        mPhone5 = (TextView)findViewById(R.id.textPhone5);
        mBtnSubmit = (RelativeLayout)findViewById(R.id.btnSubmit);
    }


    private boolean isContactSame(String name, String phone, String compareName, String comparePhone) {
        phone = phone.trim().replace(" ","");
        comparePhone = comparePhone.trim().replace(" ","");

        if (!StringUtil.isNullOrEmpty(name) && !StringUtil.isNullOrEmpty(phone) && !StringUtil.isNullOrEmpty(compareName) && !StringUtil.isNullOrEmpty(comparePhone)) {
            if (name.equals(compareName)) {
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("Warning: ");
                alertDialogBuilder.setNeutralButton("Got it", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialogBuilder.setMessage(getString(R.string.same_name));
                AlertDialog alertDialog = alertDialogBuilder.create();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        alertDialogBuilder.show();
                    }
                });
                return true;
            }

            if (phone.equals(comparePhone)) {
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("Warning: ");
                alertDialogBuilder.setNeutralButton("Got it", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialogBuilder.setMessage(getString(R.string.same_number));
                AlertDialog alertDialog = alertDialogBuilder.create();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        alertDialogBuilder.show();
                    }
                });
                return true;
            }

//            if(NameUtil.isMatch(name,compareName)){
//                final AlertDialog.Builder similarDialog = new AlertDialog.Builder(this);
//                similarDialog.setTitle("Warning: ");
//                similarDialog.setNeutralButton("Got it", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//                similarDialog.setMessage(getString(R.string.similar_name));
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        similarDialog.show();
//                    }
//                });
//                return true;
//            }
        }
        return false;
    }
}
