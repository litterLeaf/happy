package com.yinshan.happycash.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseActivity;

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
 *        ┃　　　┃   神兽保佑
 *        ┃　　　┃   代码无BUG！
 *        ┃　　　┗━━━┓
 *        ┃　　　　　　　┣┓
 *        ┃　　　　　　　┏┛
 *        ┗┓┓┏━┳┓┏┛
 *           ┃┫┫　┃┫┫
 *           ┗┻┛　┗┻┛
 *
 * @author admin
 *         on 2018/1/11
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.id_textview_tab_loan)
    TextView textViewLoan;
    @BindView(R.id.id_textview_tab_certification)
    TextView textViewCertification;
    @BindView(R.id.id_textview_tab_me)
    TextView textViewMe;
    @BindView(R.id.id_textview_tab_online_qa)
    TextView textViewOnlineQA;
    @BindView(R.id.id_fragment_loan)
    FrameLayout fragmentLoan;

    private static final int TABLOAN = 0;
    private static final int TABCERT = 1;
    private static final int TABME = 2;
    private static final int TABONLINE = 3;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;


    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @OnClick({R.id.id_textview_tab_loan,R.id.id_textview_tab_certification,R.id.id_textview_tab_me,R.id.id_textview_tab_online_qa})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.id_textview_tab_loan:
                    setSelect(TABLOAN);
                break;
            case R.id.id_textview_tab_certification:
                reSet();
                setSelect(TABCERT);
                break;
            case R.id.id_textview_tab_me:
                reSet();
                setSelect(TABME);
                break;
            case R.id.id_textview_tab_online_qa:

                break;
        }
    }
    private void reSet() {
        textViewLoan.setSelected(false);
        textViewCertification.setSelected(false);
        textViewMe.setSelected(false);
        textViewOnlineQA.setSelected(false);
    }

//    private void f(){
//        //home
//        if (isHomeVisible && null == homeFragment) {
//            Fragment tab1 = getSupportFragmentManager().findFragmentByTag("tab1");
//            if (null != tab1) {
//                homeFragment = (HomeStandardFragment) tab1;
//            } else {
//                homeFragment = new HomeStandardFragment();
//                fragmentTransaction.add(R.id.fragmentContent, homeFragment, "tab1");
//            }
//        }
//        if (isHomeVisible && null != homeFragment) {
//            fragmentTransaction.show(homeFragment);
//        } else if (!isHomeVisible && null != homeFragment) {
//            fragmentTransaction.hide(homeFragment);
//        }
//    }


    private void setSelect(int i) {
        switch (i) {
            case TABLOAN:
                textViewLoan.setSelected(true);
                break;
            case TABCERT:
//                reSet();
//                hideFragment(transaction);
//                setMainTopVisibility(View.VISIBLE);
//                if (certFrag == null) {
//                    certFrag = new CertificationFragmentProgress();
//                    transaction.add(R.id.id_fragment_loan, certFrag);
//                    Log.d(TAG, "setSelect: 1 new fragment certFrag");
//                } else {
//                    transaction.show(certFrag);
//                    Log.d(TAG, "setSelect: 1 show fragment certFrag");
//                }
//
//                textViewTitle.setText(titleAuthentication);
//                imageButtonBack.setVisibility(View.INVISIBLE);
//                imageButtonInfoList.setVisibility(View.INVISIBLE);
//                betal.setVisibility(View.GONE);
                textViewCertification.setSelected(true);
                break;
            case TABME:
//                reSet();
//                hideFragment(transaction);
//                setMainTopVisibility(View.GONE);
//                if (meFrag == null) {
//                    meFrag = new MeFragment();
//                    transaction.add(R.id.id_fragment_loan, meFrag);
//                    LoggerWrapper.d("SetSelect 2, new fragment of me");
//                } else {
//                    transaction.show(meFrag);
//                    LoggerWrapper.d("setSelect 2,show me fragment");
//                }
//                betal.setVisibility(View.GONE);
                textViewMe.setSelected(true);
                break;
            case TABONLINE:
//                initYW();
                break;
            default:
                break;
        }
//        transaction.commitAllowingStateLoss();
//        mSelect = i;
    }
}
