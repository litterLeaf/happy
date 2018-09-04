package com.yinshan.happycash.view.information.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hwangjr.rxbus.RxBus;
import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseFragment;
import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.utils.SPKeyUtils;
import com.yinshan.happycash.view.information.model.ProgressBean;
import com.yinshan.happycash.view.information.presenter.BpjsPresenter;
import com.yinshan.happycash.view.information.presenter.InformationPresenter;
import com.yinshan.happycash.view.information.view.impl.ContactActivity;
import com.yinshan.happycash.view.information.view.impl.JobInformation;
import com.yinshan.happycash.view.information.view.impl.PersonalInformation;
import com.yinshan.happycash.view.information.view.impl.UploadPhotoActivity;
import com.yinshan.happycash.view.information.view.impl.support.InfoUploadEvent;
import com.yinshan.happycash.widget.HappySnackBar;
import com.yinshan.happycash.widget.common.ToastManager;
import com.yinshan.happycash.widget.userdefined.ProfilProgressView;


import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.fraudmetrix.octopus.aspirit.main.OctopusManager;
import cn.fraudmetrix.octopus.aspirit.main.OctopusTaskCallBack;


/**
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * ┃　　　┃   神兽保佑
 * ┃　　　┃   代码无BUG！
 * ┃　　　┗━━━┓
 * ┃　　　　　　　┣┓
 * ┃　　　　　　　┏┛
 * ┗┓┓┏━┳┓┏┛
 * ┃┫┫　┃┫┫
 * ┗┻┛　┗┻┛
 *
 * @author admin
 *         on 2018/1/31
 */

public class InformationFragment extends BaseFragment implements IInfoView,IBpjsView{

    ProfilProgressView mProgressView;
    TextView mProgressText;
    TextView textView;
    RelativeLayout notFinishPerson;
    RelativeLayout finishPerson;
    RelativeLayout notFinishEmploy;
    RelativeLayout finishEmploy;
    RelativeLayout notFinishContact;
    RelativeLayout finishContact;
    RelativeLayout notFinishUploadPhoto;
    RelativeLayout finishUploadPhoto;
    Unbinder unbinder;

    RelativeLayout mSubmit;

    InformationPresenter mPresenter;
    BpjsPresenter mBpjsPresenter;

    private ProgressBean mProgressBean;

    private static final int REQUEST_PERSONAL     = 1100;
    private static final int REQUEST_PROFESSIONAL = 1101;
    private static final int REQUEST_CONTACT      = 1102;
    private static final int REQUEST_PHOTO        = 1103;

    @Override
    protected void initView() {



        resetProgress();

        mPresenter = new InformationPresenter(getActivity(),this);
        mBpjsPresenter = new BpjsPresenter(getActivity(),this);

        if(TokenManager.getInstance().hasLogin())
            mPresenter.getProgress();

        RxBus.get().register(this);
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_information;
    }

    @OnClick({R.id.view_not_finish_person, R.id.view_finish_person, R.id.view_not_finish_employ, R.id.view_finish_employ,
            R.id.view_not_finish_contact,R.id.view_finish_contact,R.id.view_not_finish_upload_photo,R.id.view_finish_upload_photo,R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.view_not_finish_person:
//                doBpjsAction();
                changeToForResult(PersonalInformation.class,REQUEST_PERSONAL,true);
                break;
            case R.id.view_finish_person:
                changeToForResult(PersonalInformation.class,REQUEST_PERSONAL,true);
                break;
            case R.id.view_not_finish_employ:
                changeToForResult(JobInformation.class,REQUEST_PROFESSIONAL,true);
                break;
            case R.id.view_finish_employ:
                changeToForResult(JobInformation.class,REQUEST_PROFESSIONAL,true);
                break;
            case R.id.view_not_finish_contact:
                changeToForResult(ContactActivity.class,REQUEST_CONTACT,true);
                break;
            case R.id.view_finish_contact:
                changeToForResult(ContactActivity.class,REQUEST_CONTACT,true);
                break;
            case R.id.view_not_finish_upload_photo:
                changeToForResult(UploadPhotoActivity.class,REQUEST_PHOTO,true);
                break;
            case R.id.view_finish_upload_photo:
                changeToForResult(UploadPhotoActivity.class,REQUEST_PHOTO,true);
                break;
            case R.id.submit:
                RxBus.get().post(new InfoUploadEvent());
                break;
        }
    }

    @Override
    public void showProgress(ProgressBean bean) {
        mProgressBean = bean;
        updateProgress();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_PERSONAL && resultCode == Activity.RESULT_OK){
            mProgressBean.setPersonalInfoPart(true);
            updateProgress();
        }else if(requestCode == REQUEST_PROFESSIONAL && resultCode == Activity.RESULT_OK){
            mProgressBean.setEmploymentPart(true);
            updateProgress();
        }else if(requestCode == REQUEST_CONTACT && resultCode == Activity.RESULT_OK){
            mProgressBean.setContactPart(true);
            updateProgress();
        }else if(requestCode == REQUEST_PHOTO && resultCode == Activity.RESULT_OK){
            mProgressBean.setFilePart(true);
            updateProgress();
        }else{

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void resetProgress(){
        mProgressBean = new ProgressBean();
        mProgressBean.clear();
        updateProgress();
    }

    private void updateProgress(){
        int progress = 0;
        if(mProgressBean.isPersonalInfoPart()){
            notFinishPerson.setVisibility(View.INVISIBLE);
            finishPerson.setVisibility(View.VISIBLE);
            progress += 25;
        }else{
            notFinishPerson.setVisibility(View.VISIBLE);
            finishPerson.setVisibility(View.INVISIBLE);
        }
        if(mProgressBean.isEmploymentPart()){
            notFinishEmploy.setVisibility(View.INVISIBLE);
            finishEmploy.setVisibility(View.VISIBLE);
            progress += 25;
        }else{
            notFinishEmploy.setVisibility(View.VISIBLE);
            finishEmploy.setVisibility(View.INVISIBLE);
        }
        if(mProgressBean.isContactPart()){
            notFinishContact.setVisibility(View.INVISIBLE);
            finishContact.setVisibility(View.VISIBLE);
            progress += 25;
        }else{
            notFinishContact.setVisibility(View.VISIBLE);
            finishContact.setVisibility(View.INVISIBLE);
        }
        if(mProgressBean.isFilePart()){
            notFinishUploadPhoto.setVisibility(View.INVISIBLE);
            finishUploadPhoto.setVisibility(View.VISIBLE);
            progress += 25;
        }else{
            notFinishUploadPhoto.setVisibility(View.VISIBLE);
            finishUploadPhoto.setVisibility(View.INVISIBLE);
        }
        setProgress(progress);
        showApplyButton(progress);
    }

    private void setProgress(int progress){
        mProgressView.setCurrentProgress((float)(progress*0.01));
        mProgressView.invalidate();
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append(String.valueOf(progress));
        spannableString.append("%");
//        AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(20);
//        AbsoluteSizeSpan sizeSpan2 = new AbsoluteSizeSpan(10);
//        spannableString.setSpan(sizeSpan, 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        spannableString.setSpan(sizeSpan2, 3, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mProgressText.setText(spannableString);
    }

    private void showApplyButton(int progress){
        if(!TokenManager.getInstance().hasLogin()){
            mSubmit.setVisibility(View.INVISIBLE);
        }else{
            if(progress==100){
                mSubmit.setClickable(true);
                mSubmit.setAlpha(0.8f);
            }else{
                mSubmit.setClickable(false);
                mSubmit.setAlpha(0.3f);
            }
        }
    }

    @Override
    protected void initUIValue(View view){

        mProgressView = (ProfilProgressView)view.findViewById(R.id.progressView);
        mProgressText = (TextView)view.findViewById(R.id.progressText);
        textView = (TextView)view.findViewById(R.id.textView);
        notFinishPerson = (RelativeLayout)view.findViewById(R.id.view_not_finish_person);
        finishPerson = (RelativeLayout)view.findViewById(R.id.view_finish_person);
        notFinishEmploy = (RelativeLayout)view.findViewById(R.id.view_not_finish_employ);

        finishEmploy = (RelativeLayout)view.findViewById(R.id.view_finish_employ);
        notFinishContact = (RelativeLayout)view.findViewById(R.id.view_not_finish_contact);
        finishContact = (RelativeLayout)view.findViewById(R.id.view_finish_contact);
        notFinishUploadPhoto = (RelativeLayout)view.findViewById(R.id.view_not_finish_upload_photo);
        finishUploadPhoto = (RelativeLayout)view.findViewById(R.id.view_finish_upload_photo);

        mSubmit = (RelativeLayout)view.findViewById(R.id.submit);
    }

    private void doBpjsAction(){
//        OctopusParam param = new OctopusParam();
////                param.passbackarams=“*****”//选填;
//        param.identityCode = "420143198805163322";//必填，作为唯⼀一标识关联多数据源数据;

        OctopusManager.getInstance().setNavImgResId(R.drawable.path_3_copy);
        OctopusManager.getInstance().setPrimaryColorResId(R.color.app_yellow);
//                OctopusManager.getInstance().setTitleColorResId(R.color.app_yellow);
//                OctopusManager.getInstance().setTitleSize(14);
        OctopusManager.getInstance().setShowWarnDialog(true);
        OctopusManager.getInstance().setStatusBarBg(R.color.app_yellow);
        OctopusManager.getInstance().getChannel(getActivity(),"105002",null,octopusTaskCallBack);
    }

    @Override
    public void bpjsOk() {
        ToastManager.showToast("Selamat, sukses dalam memperoleh informasi keamanan sosial!");
    }

    @Override
    public void bpjsFail() {
        HappySnackBar.showSnackBar(mSubmit,"Gagal mendapatkan informasi keamanan sosial, silakan coba lagi", SPKeyUtils.SNACKBAR_TYPE_WORN);
    }

    private OctopusTaskCallBack octopusTaskCallBack = new OctopusTaskCallBack() { @Override
        public void onCallBack(int code, String taskId) {
            String msg = "成功";
            if(code == 0){//code为0表示成功，f⾮非0表示失败
    //只有code为0时taskid才会有值。msg+=taskId;
                mBpjsPresenter.initBpjs(taskId);
            }else {
                msg = "失败："+code;
                HappySnackBar.showSnackBar(mSubmit,"Gagal mendapatkan informasi keamanan sosial, silakan coba lagi", SPKeyUtils.SNACKBAR_TYPE_WORN);
            }
        }
    };
}
