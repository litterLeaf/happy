package com.yinshan.happycash.view.main.view.impl;


import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.application.HappyAppSP;
import com.yinshan.happycash.framework.BaseActivity;
import com.yinshan.happycash.framework.MessageEvent;
import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.utils.AppLoanStatus;
import com.yinshan.happycash.utils.LoggerWrapper;
import com.yinshan.happycash.utils.SPKeyUtils;
import com.yinshan.happycash.utils.StatusManagementUtils;
import com.yinshan.happycash.view.loan.view.impl.ApplyFragment;
import com.yinshan.happycash.view.loan.view.impl.BuildUpFragment;
import com.yinshan.happycash.utils.SPUtils;
import com.yinshan.happycash.utils.SystemUtil;
import com.yinshan.happycash.view.information.view.InformationFragment;
import com.yinshan.happycash.view.information.view.impl.support.InfoUploadEvent;
import com.yinshan.happycash.view.loan.view.impl.LoaningFragment;
import com.yinshan.happycash.view.loan.view.impl.RejectFragment;
import com.yinshan.happycash.view.loan.view.impl.RepaymentFragment;
import com.yinshan.happycash.view.loan.view.impl.UnLoanFragment;
import com.yinshan.happycash.view.login.LoginActivity;
import com.yinshan.happycash.view.main.QuestionActivity;
import com.yinshan.happycash.view.main.model.LastLoanAppBean;
import com.yinshan.happycash.view.main.presenter.GetStatusPresenter;
import com.yinshan.happycash.view.main.view.IGetStatusView;
import com.yinshan.happycash.view.me.view.impl.MeFragment;
import com.yinshan.happycash.widget.common.ToastManager;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

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
 *        ┃　　　┃   兽神保佑
 *        ┃　　　┃   代码无BUG！
 *        ┃　　　┗━━━┓
 *        ┃　　　　　　　┣┓
 *        ┃　　　　　　　┏┛
 *        ┗┓┓┏━┳┓┏┛
 *           ┃┫┫　┃┫┫
 *           ┗┻┛　┗┻┛
 *
 *    描述：          贷款状态机+
 *    创建人：     admin
 *    创建时间：2018/1/11 
 *
 */
public class MainActivity extends BaseActivity implements IGetStatusView {

    boolean isFirstEnter = true;

    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @BindView(R.id.id_textview_tab_loan)
    TextView tabLoan;
    @BindView(R.id.id_linearlayout_loan)
    LinearLayout idLinearlayoutLoan;
    @BindView(R.id.id_textview_tab_certification)
    TextView tabInformation;
    @BindView(R.id.id_linearlayout_certification)
    LinearLayout idLinearlayoutCertification;
    @BindView(R.id.id_textview_tab_me)
    TextView tabMe;
    @BindView(R.id.id_linearlayout_me)
    LinearLayout idLinearlayoutMe;
    @BindView(R.id.id_textview_tab_online_qa)
    TextView abOnlineQa;
    @BindView(R.id.id_linearlayout_online_qa)
    LinearLayout idLinearlayoutOnlineQa;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private UnLoanFragment unLoanFrag;
    private LoaningFragment loaningFrag;

    private ApplyFragment applyFragment;
    private InformationFragment inforFragament;
    private MeFragment meFrag;
    private BuildUpFragment buildUpFragment;
    private RejectFragment rejectFragment;
    private RepaymentFragment repaymentFragment;

    public static int chooseIndex;

    private GetStatusPresenter mPresenter;

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void secondLayout() {

    }

    @Override
    protected void secondInit() {

    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        reSetTab(1);

        mPresenter = new GetStatusPresenter(this,this);

        LastLoanAppBean object = SPUtils.getInstance().getObject(SPKeyUtils.LOANAPPBEAN, LastLoanAppBean.class);
        if(object!=null&&object.getStatus()!=null){
            dealResult(object);
        }else {
            showFragment(AppLoanStatus.UNLOAN);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(chooseIndex==1&&TokenManager.getInstance().hasLogin()){
            if(!isFirstEnter){

            }
        }
    }

    private void dealResult(LastLoanAppBean bean){
        if(bean==null||bean.getStatus()==null){
            return;
        }
        String lastAppStatus = StatusManagementUtils.loanStatusClassify(bean);
        showFragment(lastAppStatus);
    }

    /**
     * manage  fragment  with status
     *
     * @param isUnLoan
     * @param isInfor
     * @param isMeFragment
     * @param isLoaning
     * @param isProcess
     * @param isBuildUp
     * @param isRepayment
     * @param isReject
     */

    private void manageFragament(boolean isUnLoan, boolean isInfor, boolean isMeFragment, boolean isLoaning, boolean isProcess,
                                 boolean isBuildUp, boolean isRepayment, boolean isExpiryRepayment, boolean isRollover, boolean isReject) {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        //repaymentFragment
        if (isRepayment && null == repaymentFragment) {
            Fragment tab1 = getSupportFragmentManager().findFragmentByTag(SPKeyUtils.REPAYMENT_FRAG);
            if (null != tab1) {
                repaymentFragment = (RepaymentFragment) tab1;
            } else {
                repaymentFragment = new RepaymentFragment();
                transaction.add(R.id.fragment_container, repaymentFragment, SPKeyUtils.REPAYMENT_FRAG);
            }
        }
        if (isRepayment && null != repaymentFragment) {
            transaction.show(repaymentFragment);
        } else if (!isRepayment && null != repaymentFragment) {
            transaction.hide(repaymentFragment);
        }

        //Rejec
        if (isReject && null == rejectFragment) {
            Fragment tab1 = getSupportFragmentManager().findFragmentByTag(SPKeyUtils.REJECT_FRAG);
            if (null != tab1) {
                rejectFragment = (RejectFragment) tab1;
            } else {
                rejectFragment = new RejectFragment();
                transaction.add(R.id.fragment_container, rejectFragment, SPKeyUtils.REJECT_FRAG);
            }
        }
        if (isReject && null != rejectFragment) {
            transaction.show(rejectFragment);
        } else if (!isReject && null != rejectFragment) {
            transaction.hide(rejectFragment);
        }

        //BuildUp
        if (isBuildUp && null == buildUpFragment) {
            Fragment tab1 = getSupportFragmentManager().findFragmentByTag(SPKeyUtils.BUILDUP_FRAG);
            if (null != tab1) {
                buildUpFragment = (BuildUpFragment) tab1;
            } else {
                buildUpFragment = new BuildUpFragment();
                transaction.add(R.id.fragment_container, buildUpFragment, SPKeyUtils.BUILDUP_FRAG);
            }
        }
        if (isBuildUp && null != buildUpFragment) {
            transaction.show(buildUpFragment);
        } else if (!isBuildUp && null != buildUpFragment) {
            transaction.hide(buildUpFragment);
        }

        //isProcess
        if (isProcess && null == applyFragment) {
            Fragment tab1 = getSupportFragmentManager().findFragmentByTag(SPKeyUtils.PROCESS_FRAG);
            if (null != tab1) {
                applyFragment = (ApplyFragment) tab1;
            } else {
                applyFragment = new ApplyFragment();
                transaction.add(R.id.fragment_container, applyFragment, SPKeyUtils.PROCESS_FRAG);
            }
        }
        if (isProcess && null != applyFragment) {
            transaction.show(applyFragment);
        } else if (!isProcess && null != applyFragment) {
            transaction.hide(applyFragment);
        }

        //loaning
        if (isLoaning && null == loaningFrag) {
            Fragment tab1 = getSupportFragmentManager().findFragmentByTag(SPKeyUtils.LOANING_FRAG);
            if (null != tab1) {
                loaningFrag = (LoaningFragment) tab1;
            } else {
                loaningFrag = new LoaningFragment();
                transaction.add(R.id.fragment_container, loaningFrag, SPKeyUtils.LOANING_FRAG);
            }
        }
        if (isLoaning && null != loaningFrag) {
            transaction.show(loaningFrag);
        } else if (!isLoaning && null != loaningFrag) {
            transaction.hide(loaningFrag);
        }

        //unLoan
        if (isUnLoan && null == unLoanFrag) {
            Fragment tab1 = getSupportFragmentManager().findFragmentByTag(SPKeyUtils.UNLOAN_FRAG);
            if (null != tab1) {
                unLoanFrag = (UnLoanFragment) tab1;
            } else {
                unLoanFrag = new UnLoanFragment();
                transaction.add(R.id.fragment_container, unLoanFrag, SPKeyUtils.UNLOAN_FRAG);
            }
        }
        if (isUnLoan && null != unLoanFrag) {
            transaction.show(unLoanFrag);
        } else if (!isUnLoan && null != unLoanFrag) {
            transaction.hide(unLoanFrag);
        }

        //inforFragament
        if (isInfor && null == inforFragament) {
            Fragment tab1 = getSupportFragmentManager().findFragmentByTag(SPKeyUtils.CERTIFICATION_TAB);
            if (null != tab1) {
                inforFragament = (InformationFragment) tab1;
            } else {
                inforFragament = new InformationFragment();
                transaction.add(R.id.fragment_container, inforFragament, SPKeyUtils.CERTIFICATION_TAB);
            }
        }
        if (isInfor && null != inforFragament) {
            transaction.show(inforFragament);
        } else if (!isInfor && null != inforFragament) {
            transaction.hide(inforFragament);
        }

        //meFragment
        boolean isMeFirstReShow = false;
        if (isMeFragment) {
            if(null==meFrag){
                Fragment tab1 = getSupportFragmentManager().findFragmentByTag(SPKeyUtils.ME_TAB);
                if (null != tab1) {
                    meFrag = (MeFragment) tab1;
                    isMeFirstReShow = true;
                } else {
                    isMeFirstReShow = false;
                    meFrag = new MeFragment();
                    transaction.add(R.id.fragment_container, meFrag, SPKeyUtils.ME_TAB);
                }
            }else{
                isMeFirstReShow = true;
            }
        }
        if (isMeFragment && null != meFrag) {
            transaction.show(meFrag);
            if(isMeFirstReShow)
                meFrag.resume();
        } else if (!isMeFragment && null != meFrag) {
            transaction.hide(meFrag);
        }
        transaction.commit();
    }

    private void showFragment(String status) {
        if (AppLoanStatus.UNLOAN.equals(status)) {
            manageFragament(true, false, false, false, false, false, false,
                    false,false,false);
        } else if (AppLoanStatus.REVIEW.equals(status)) {
            manageFragament(false, false, false, false, true, false, false,
                    false,false,false);
        } else if (AppLoanStatus.REVIEW_SUPPLEMENT.equals(status)) {
            manageFragament(false, false, false, false, false, true, false,
                    false,false,false);
        } else if (AppLoanStatus.REPAYMENT.equals(status)) {
            manageFragament(false, false, false, false, false, false, true,
                    false,false,false);
        } else if (AppLoanStatus.OVERDUE.equals(status)) {
            manageFragament(false, false, false, false, false, false, true,
                    false,false,false);
        }else if(AppLoanStatus.REJECT.equals(status)){
            manageFragament(false, false, false, false, false, false, false,
                    false,false,true);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void goInformationFragment(MessageEvent messageEvent) {
        manageFragament(false, true, false, false, false,
                false, false, false,false,false);
        reSetTab(2);
    }

    //数据刷新
    public void updateStatus(final String token) {
        showLoading("update info...");

        if(TokenManager.getInstance().hasLogin())
            mPresenter.getStatusInfo(token);
//        api.getLatestLoanApp(token, "Main")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<LatestLoanAppBean>() {
//                    @Override
//                    public void onCompleted() {
//                        LoggerWrapper.d("MainActivity:updatestatus--oncompleted");
//                        dismissLoading();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        ToastManager.showToast("Loading data tidak normal");
//                        LoggerWrapper.d("MainActivity: updatestatus--" + e.getMessage());
//                        dismissLoading();
//                        LatestLoanAppBean object = AppSP.getInstance().getLatestBean();
//                        if(object!=null){
//                            dealResult(object.getStatus());
//                        }else{
//                            showDefaultView();
//                        }
//                    }
//
//                    @Override
//                    public void onNext(LatestLoanAppBean latestLoanAppBean) {
//                        if(latestLoanAppBean!=null) {
//                            AppSP.getInstance().setObject(FieldParams.LATESTBEAN, latestLoanAppBean);
//                            if(latestLoanAppBean.getStatus()!=null)
//                                dealResult(latestLoanAppBean.getStatus());
//                        }
//                        LoggerWrapper.d("MainActivity: updatestatus--" + "success");
//                    }
//                });
    }

    public void reUpdateStatus(){
        showLoading("update info...");
        if(!TokenManager.getInstance().hasLogin()){
            dismissLoading();
            return;
        }
//        mPres.getLatestLoanApp(TokenManager.getInstance().getToken(), "Main")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<LatestLoanAppBean>() {
//                    @Override
//                    public void onCompleted() {
//                        LoggerWrapper.d("MainActivity:updatestatus--oncompleted");
//                        dismissLoading();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        ToastManager.showToast("Loading data tidak normal");
//                        LoggerWrapper.d("MainActivity: updatestatus--" + e.getMessage());
//                        dismissLoading();
//                    }
//
//                    @Override
//                    public void onNext(LatestLoanAppBean latestLoanAppBean) {
//                        LatestLoanAppBean getObject = AppSP.getInstance().getLatestBean();
//                        AppSP.getInstance().setObject(FieldParams.LATESTBEAN, latestLoanAppBean);
//                        if(latestLoanAppBean!=null&&latestLoanAppBean.getStatus()!=null)
//                            dealResult(latestLoanAppBean.getStatus());
//                        LoggerWrapper.d("MainActivity: updatestatus--" + "success");
//                    }
//                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void goBackUnLoanFragment(InfoUploadEvent messageEvent) {
        manageFragament(false, false, false, true, false,
                false, false, false,false,false);
        reSetTab(1);
    }

    @OnClick({R.id.id_textview_tab_loan, R.id.id_textview_tab_certification, R.id.id_textview_tab_me,R.id.id_textview_tab_online_qa})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.id_textview_tab_loan:
                reSetTab(1);
                LastLoanAppBean object = SPUtils.getInstance().getObject(SPKeyUtils.LOANAPPBEAN, LastLoanAppBean.class);
                if(object!=null&&object.getStatus()!=null){
                    dealResult(SPUtils.getInstance().getObject(SPKeyUtils.LOANAPPBEAN,LastLoanAppBean.class));
                }else {
                    showFragment(AppLoanStatus.UNLOAN);
                }
                break;
            case R.id.id_textview_tab_certification:
                reSetTab(2);
                manageFragament(false,true,false,false,false,
                        false,false,false,false,false);
                break;
            case R.id.id_textview_tab_me:
                reSetTab(3);
                manageFragament(false,false,true,false,false,
                        false,false,false,false,false);
                break;
            case R.id.id_textview_tab_online_qa:
                if(TokenManager.getInstance().hasLogin()){
                    mStartActivity(MainActivity.this,QuestionActivity.class);
                }else {
                    mStartActivity(MainActivity.this,LoginActivity.class);
                }

                break;
        }
    }

    private void reSetTab(int tab){
        tabLoan.setSelected(false);
        tabInformation.setSelected(false);
        tabMe.setSelected(false);
        switch (tab){
            case  1:
                tabLoan.setSelected(true);
                break;
            case  2:
                tabInformation.setSelected(true);
                break;
            case  3:
                tabMe.setSelected(true);
                break;
        }
    }

    //crash 中出现Lenov的某机型会出现RuntimeExeception错误，应该是他们的底层做了修改
    //暂时先catch出来，后续找找解决方案
    public static boolean hasSelfPermission(Context context, String permission) {
        try {
            return ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public static void initImei() {
        String imei = HappyAppSP.getInstance().getImei();
        if (TextUtils.isEmpty(imei)) {
            imei = SystemUtil.getInstance().getImei();
            if (!TextUtils.isEmpty(imei)) {
                HappyAppSP.getInstance().setImei(imei);
            }
        }
    }

    //默认unLoan界面
    public void showDefaultView(){
//        manageFragment(true,false,false,false,false,false,false,
//                false,false,false);
    }

    @Override
    public void getStatusSuccess(LastLoanAppBean latestLoanAppBean) {
        if(latestLoanAppBean!=null) {
            SPUtils.getInstance().setObject(SPKeyUtils.LOANAPPBEAN, latestLoanAppBean);
            if(latestLoanAppBean.getStatus()!=null)
                dealResult(latestLoanAppBean);
        }
        LoggerWrapper.d("MainActivity: updatestatus--" + "success");
    }

    @Override
    public void getStatusError(String displayMessage) {
        ToastManager.showToast("Loading data tidak normal");
        LoggerWrapper.d("MainActivity: updatestatus--" + displayMessage);
        LastLoanAppBean object = SPUtils.getInstance().getObject(SPKeyUtils.LOANAPPBEAN, LastLoanAppBean.class);
        if(object!=null){
            dealResult(object);
        }else{
            showDefaultView();
        }
    }
}
