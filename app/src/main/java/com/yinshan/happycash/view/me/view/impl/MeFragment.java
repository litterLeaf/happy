package com.yinshan.happycash.view.me.view.impl;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseFragment;
import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.utils.SPUtils;
import com.yinshan.happycash.view.information.model.PersonalBean;
import com.yinshan.happycash.view.information.presenter.PersonalPresenter;
import com.yinshan.happycash.view.information.view.IPersonalView;
import com.yinshan.happycash.view.login.LoginActivity;
import com.yinshan.happycash.view.me.view.IGetPersonView;
import com.yinshan.happycash.view.me.view.impl.AboutActivity;
import com.yinshan.happycash.view.me.view.impl.HelpCenterActivity;
import com.yinshan.happycash.view.me.view.impl.LoanListActivity;
import com.yinshan.happycash.view.me.view.impl.SafeSettingActivity;
import com.yinshan.happycash.view.me.view.impl.support.GetPersonInfoPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by huxin on 2018/3/13.
 */

public class MeFragment extends BaseFragment implements IGetPersonView{

    @BindView(R.id.loginView)
    RelativeLayout mLoginView;
    @BindView(R.id.infoView)
    LinearLayout mInfoView;
    @BindView(R.id.userName)
    TextView mUserName;
    @BindView(R.id.userMobile)
    TextView mUserMobile;

    GetPersonInfoPresenter mGetPersonPresenter;

    @Override
    protected void initView() {
//        mIMKit = YWAPI.getIMKitInstance("", "");
        mGetPersonPresenter = new GetPersonInfoPresenter(getActivity(),this);
        resume();
    }

    @Override
    protected void initUIValue(View view) {

    }

    public void resume(){
        if(isLogin()){
            mLoginView.setVisibility(View.GONE);
            mInfoView.setVisibility(View.VISIBLE);
            if(TextUtils.isEmpty(SPUtils.getInstance().getUsername())) {
                mUserName.setText(SPUtils.getInstance().getUsername());
            }else{
                mGetPersonPresenter.getPersonInfo();
            }
            mUserMobile.setText(SPUtils.getInstance().getMobile());
        }
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_me;
    }

    @OnClick({R.id.loanView,R.id.safeSettingView,R.id.helpCenterView,R.id.customHotLineView,R.id.aboutView,R.id.loginView})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.loanView:
                if(TextUtils.isEmpty(TokenManager.getInstance().getToken())||TokenManager.isExpired)
                    mStartActivity(LoginActivity.class);
                else
                    mStartActivity(LoanListActivity.class);
                break;
            case R.id.safeSettingView:
                mStartActivity(SafeSettingActivity.class);
                break;
            case R.id.helpCenterView:
                mStartActivity(HelpCenterActivity.class);
                break;
            case R.id.customHotLineView:
//                Intent intent = mIMKit.getConversationActivityIntent();
//                startActivity(intent);
                break;
            case R.id.aboutView:
                mStartActivity(AboutActivity.class);
                break;
            case R.id.loginView:
                mStartActivity(LoginActivity.class);
                break;
        }
    }

    @Override
    public void showInfo(PersonalBean personalBean) {
        if(personalBean!=null&&personalBean.getFullName()!=null){
            SPUtils.getInstance().setUsername(personalBean.getFullName());
            mUserName.setText(personalBean.getFullName());
        }
    }
}
