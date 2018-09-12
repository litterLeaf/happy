package com.yinshan.happycash.view.information.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hwangjr.rxbus.RxBus;
import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseFragment;
import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.utils.AppLoanStatus;
import com.yinshan.happycash.utils.SPKeyUtils;
import com.yinshan.happycash.utils.SPUtils;
import com.yinshan.happycash.utils.StatusManagementUtils;
import com.yinshan.happycash.view.information.model.ProgressBean;
import com.yinshan.happycash.view.information.presenter.BpjsPresenter;
import com.yinshan.happycash.view.information.presenter.InformationPresenter;
import com.yinshan.happycash.view.information.view.impl.ContactActivity;
import com.yinshan.happycash.view.information.view.impl.JobInformation;
import com.yinshan.happycash.view.information.view.impl.PersonalInformation;
import com.yinshan.happycash.view.information.view.impl.UploadPhotoActivity;
import com.yinshan.happycash.view.information.view.impl.support.InfoUploadEvent;
import com.yinshan.happycash.view.main.model.LastLoanAppBean;
import com.yinshan.happycash.widget.HappySnackBar;
import com.yinshan.happycash.widget.common.ToastManager;
import com.yinshan.happycash.widget.userdefined.ProfilProgressView;


import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.fraudmetrix.octopus.aspirit.bean.OctopusParam;
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

    ProgressBar mProgressView;
    TextView mProgressText;

    RelativeLayout mViewPerson;
    RelativeLayout mViewJob;
    RelativeLayout mViewContact;
    RelativeLayout mViewSS;
    RelativeLayout mViewPhoto;
    ImageView mCheckPerson;
    ImageView mCheckJob;
    ImageView mCheckContact;
    ImageView mCheckSS;
    ImageView mCheckPhoto;
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

    @OnClick({R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            case R.id.view_not_finish_person:
////                doBpjsAction();
//                changeToForResult(PersonalInformation.class,REQUEST_PERSONAL,true);
//                break;
//            case R.id.view_finish_person:
//                changeToForResult(PersonalInformation.class,REQUEST_PERSONAL,true);
//                break;
//            case R.id.view_not_finish_employ:
//                changeToForResult(JobInformation.class,REQUEST_PROFESSIONAL,true);
//                break;
//            case R.id.view_finish_employ:
//                changeToForResult(JobInformation.class,REQUEST_PROFESSIONAL,true);
//                break;
//            case R.id.view_not_finish_contact:
//                changeToForResult(ContactActivity.class,REQUEST_CONTACT,true);
//                break;
//            case R.id.view_finish_contact:
//                changeToForResult(ContactActivity.class,REQUEST_CONTACT,true);
//                break;
//            case R.id.view_not_finish_upload_photo:
//                changeToForResult(UploadPhotoActivity.class,REQUEST_PHOTO,true);
//                break;
//            case R.id.view_finish_upload_photo:
//                changeToForResult(UploadPhotoActivity.class,REQUEST_PHOTO,true);
//                break;
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
            mCheckPerson.setVisibility(View.VISIBLE);
            progress += 20;
        }else{
            mCheckPerson.setVisibility(View.GONE);
        }
        if(mProgressBean.isEmploymentPart()){
            mCheckJob.setVisibility(View.VISIBLE);
            progress += 20;
        }else{
            mCheckJob.setVisibility(View.GONE);
        }
        if(mProgressBean.isContactPart()){
            mCheckContact.setVisibility(View.VISIBLE);
            progress += 20;
        }else{
            mCheckContact.setVisibility(View.GONE);
        }
        if(mProgressBean.isBpjsPart()){
            mCheckSS.setVisibility(View.VISIBLE);
            progress += 20;
        }else{
            mCheckSS.setVisibility(View.GONE);
        }
        if(mProgressBean.isFilePart()){
            mCheckPhoto.setVisibility(View.VISIBLE);
            progress += 20;
        }else{
            mCheckPhoto.setVisibility(View.GONE);
        }
        setProgress(progress);
        showApplyButton(progress);
    }

    private void setProgress(int progress){
        mProgressView.setProgress(progress);
        mProgressView.invalidate();
//        SpannableStringBuilder spannableString = new SpannableStringBuilder();
//        spannableString.append(String.valueOf(progress));
//        spannableString.append("%");
//        AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(20);
//        AbsoluteSizeSpan sizeSpan2 = new AbsoluteSizeSpan(10);
//        spannableString.setSpan(sizeSpan, 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        spannableString.setSpan(sizeSpan2, 3, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mProgressText.setText(String.valueOf(progress)+"%");
    }

    private void showApplyButton(int progress){
        if(!TokenManager.getInstance().hasLogin()){
            mSubmit.setVisibility(View.INVISIBLE);
        }else{
            LastLoanAppBean bean = SPUtils.getInstance().getObject(SPKeyUtils.LOANAPPBEAN, LastLoanAppBean.class);
            if(bean==null)
                showProgressButton(progress);
            else{
                String loanStatus = StatusManagementUtils.loanStatusClassify(bean);
                if(loanStatus.equals(AppLoanStatus.UNLOAN)){
                    showProgressButton(progress);
                }else{
                    mSubmit.setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    private void showProgressButton(int progress) {
        if(progress==100){
            mSubmit.setClickable(true);
            mSubmit.setAlpha(0.8f);
        }else{
            mSubmit.setClickable(false);
            mSubmit.setAlpha(0.3f);
        }
    }

    @Override
    protected void initUIValue(View view){
        mProgressView = (ProgressBar)view.findViewById(R.id.progressView);
        mProgressText = (TextView)view.findViewById(R.id.progressNum);

        mViewPerson = (RelativeLayout)view.findViewById(R.id.viewPerson);
        mViewJob = (RelativeLayout)view.findViewById(R.id.viewJob);
        mViewContact = (RelativeLayout)view.findViewById(R.id.viewContact);
        mViewSS = (RelativeLayout)view.findViewById(R.id.viewSS);
        mViewPhoto = (RelativeLayout)view.findViewById(R.id.viewPhoto);

        mCheckPerson = (ImageView)view.findViewById(R.id.personCheck);
        mCheckJob = (ImageView)view.findViewById(R.id.jobCheck);
        mCheckContact = (ImageView)view.findViewById(R.id.contactCheck);
        mCheckSS = (ImageView) view.findViewById(R.id.ssCheck);
        mCheckPhoto = (ImageView)view.findViewById(R.id.photoCheck);

        mViewPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToForResult(PersonalInformation.class,REQUEST_PERSONAL,true);
            }
        });
        mViewJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToForResult(JobInformation.class,REQUEST_PROFESSIONAL,true);
            }
        });
        mViewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToForResult(ContactActivity.class,REQUEST_CONTACT,true);
            }
        });
        mViewSS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mProgressBean==null)
                    mPresenter.getProgress();
                else{
                    if(mProgressBean.isPersonalInfoPart()&& !TextUtils.isEmpty(SPUtils.getInstance().getUserKtp())){
                        doBpjsAction();
                    }else{
                        ToastManager.showToast("Harap tingkatkan informasi pribadi Anda terlebih dahulu.");
                    }
                }
            }
        });
        mViewPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToForResult(UploadPhotoActivity.class,REQUEST_PHOTO,true);
            }
        });

        mSubmit = (RelativeLayout)view.findViewById(R.id.submit);
    }

    private void doBpjsAction(){
//        OctopusParam param = new OctopusParam();
////                param.passbackarams=“*****”//选填;
//        param.identityCode = "420143198805163322";//必填，作为唯⼀一标识关联多数据源数据;
        OctopusParam param = new OctopusParam();
        param.identityCode = SPUtils.getInstance().getUserKtp();//必填，作为唯⼀一标识关联多数据源数据;

        OctopusManager.getInstance().setNavImgResId(R.drawable.path_3_copy);
        OctopusManager.getInstance().setPrimaryColorResId(R.color.app_yellow);
//                OctopusManager.getInstance().setTitleColorResId(R.color.app_yellow);
//                OctopusManager.getInstance().setTitleSize(14);
        OctopusManager.getInstance().setShowWarnDialog(true);
        OctopusManager.getInstance().setStatusBarBg(R.color.app_yellow);
        OctopusManager.getInstance().getChannel(getActivity(),"105002",param,octopusTaskCallBack);
    }

    @Override
    public void bpjsOk() {
        mProgressBean.setBpjsPart(true);
        updateProgress();
        //ToastManager.showToast("Selamat, sukses dalam memperoleh informasi keamanan sosial!");
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
