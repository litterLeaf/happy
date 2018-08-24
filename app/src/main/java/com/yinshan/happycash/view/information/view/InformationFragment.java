package com.yinshan.happycash.view.information.view;

import android.app.Activity;
import android.content.Intent;
import android.provider.ContactsContract;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hwangjr.rxbus.RxBus;
import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseFragment;
import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.view.information.model.ProgressBean;
import com.yinshan.happycash.view.information.presenter.InformationPresenter;
import com.yinshan.happycash.view.information.view.impl.ContactActivity;
import com.yinshan.happycash.view.information.view.impl.JobInformation;
import com.yinshan.happycash.view.information.view.impl.PersonalInformation;
import com.yinshan.happycash.view.information.view.impl.UploadPhotoActivity;
import com.yinshan.happycash.view.information.view.impl.support.InfoUploadEvent;
import com.yinshan.happycash.widget.userdefined.ProfilProgressView;


import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;


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

public class InformationFragment extends BaseFragment implements IInfoView{

    @BindView(R.id.progressView)
    ProfilProgressView mProgressView;
    @BindView(R.id.progressText)
    TextView mProgressText;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.view_not_finish_person)
    RelativeLayout notFinishPerson;
    @BindView(R.id.view_finish_person)
    RelativeLayout finishPerson;
    @BindView(R.id.view_not_finish_employ)
    RelativeLayout notFinishEmploy;
    @BindView(R.id.view_finish_employ)
    RelativeLayout finishEmploy;
    @BindView(R.id.view_not_finish_contact)
    RelativeLayout notFinishContact;
    @BindView(R.id.view_finish_contact)
    RelativeLayout finishContact;
    @BindView(R.id.view_not_finish_upload_photo)
    RelativeLayout notFinishUploadPhoto;
    @BindView(R.id.view_finish_upload_photo)
    RelativeLayout finishUploadPhoto;
    Unbinder unbinder;

    @BindView(R.id.submit)
    RelativeLayout mSubmit;

    InformationPresenter mPresenter;
    private ProgressBean mProgressBean;

    private static final int REQUEST_PERSONAL     = 1100;
    private static final int REQUEST_PROFESSIONAL = 1101;
    private static final int REQUEST_CONTACT      = 1102;
    private static final int REQUEST_PHOTO        = 1103;

    @Override
    protected void initView() {

        resetProgress();

        mPresenter = new InformationPresenter(getActivity(),this);
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
                changeToForResult(PersonalInformation.class,REQUEST_PERSONAL);
                break;
            case R.id.view_finish_person:
                changeToForResult(PersonalInformation.class,REQUEST_PERSONAL);
                break;
            case R.id.view_not_finish_employ:
                changeToForResult(JobInformation.class,REQUEST_PROFESSIONAL);
                break;
            case R.id.view_finish_employ:
                changeToForResult(JobInformation.class,REQUEST_PROFESSIONAL);
                break;
            case R.id.view_not_finish_contact:
                changeToForResult(ContactActivity.class,REQUEST_CONTACT);
                break;
            case R.id.view_finish_contact:
                changeToForResult(ContactActivity.class,REQUEST_CONTACT);
                break;
            case R.id.view_not_finish_upload_photo:
                changeToForResult(UploadPhotoActivity.class,REQUEST_PHOTO);
                break;
            case R.id.view_finish_upload_photo:
                changeToForResult(UploadPhotoActivity.class,REQUEST_PHOTO);
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
}
