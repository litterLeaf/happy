package com.yinshan.happycash.view.me.view.impl;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.application.AppContext;
import com.yinshan.happycash.config.inner.AppDataConfig;
import com.yinshan.happycash.framework.BaseFragment;
import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.utils.SPUtils;
import com.yinshan.happycash.utils.ToolsUtils;
import com.yinshan.happycash.view.information.model.PersonalBean;
import com.yinshan.happycash.view.login.LoginActivity;
import com.yinshan.happycash.view.me.view.IGetPersonView;
import com.yinshan.happycash.view.me.view.impl.support.GetPersonInfoPresenter;
import com.yinshan.happycash.widget.common.CommonClickListener;
import com.yinshan.happycash.widget.dialog.CommonDialog;

import butterknife.OnClick;

/**
 * Created by huxin on 2018/3/13.
 */

public class MeFragment extends BaseFragment implements IGetPersonView {

    RelativeLayout mLoginView;
    LinearLayout mInfoView;
    TextView mUserName;
    TextView mUserMobile;

    GetPersonInfoPresenter mGetPersonPresenter;

    @Override
    protected void initView() {
//        mIMKit = YWAPI.getIMKitInstance("", "");
        mGetPersonPresenter = new GetPersonInfoPresenter(getActivity(), this);
        resume();
    }

    @Override
    protected void initUIValue(View view) {
        mLoginView = (RelativeLayout) view.findViewById(R.id.loginView);
        mInfoView = (LinearLayout) view.findViewById(R.id.infoView);
        mUserName = (TextView) view.findViewById(R.id.userName);
        mUserMobile = (TextView) view.findViewById(R.id.userMobile);
    }

    public void resume() {
        if (isLogin()) {
            mLoginView.setVisibility(View.GONE);
            mInfoView.setVisibility(View.VISIBLE);
            if(!TextUtils.isEmpty(SPUtils.getInstance().getUsername())) {
                mUserName.setText(SPUtils.getInstance().getUsername());
                mUserName.setVisibility(View.VISIBLE);
            }else{
                mGetPersonPresenter.getPersonInfo();
            }
            mUserMobile.setText(SPUtils.getInstance().getMobile());
        }else{
            mLoginView.setVisibility(View.VISIBLE);
            mInfoView.setVisibility(View.GONE);
        }
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_me;
    }

    @OnClick({R.id.loanView, R.id.safeSettingView, R.id.helpCenterView, R.id.customHotLineView, R.id.aboutView, R.id.loginView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.loanView:
                if (TextUtils.isEmpty(TokenManager.getInstance().getToken()) || TokenManager.isExpired)
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

                CommonDialog dialog = new CommonDialog(getActivity(),
                        () -> {
                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            Uri data = Uri.parse("tel:" + AppDataConfig.HOTLINE);
                            intent.setData(data);
                            startActivity(intent);
                        }, "",
                        String.format(getString(R.string.show_dial_hotline), AppDataConfig.HOTLINE),
                        "", "", true);
                dialog.show();
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
        if (personalBean != null && personalBean.getFullName() != null) {
            SPUtils.getInstance().setUsername(personalBean.getFullName());
            if(TextUtils.isEmpty(personalBean.getFullName())){
                mUserName.setVisibility(View.GONE);
            }else{
                mUserName.setVisibility(View.VISIBLE);
                mUserName.setText(personalBean.getFullName());
            }
        }
    }

    @Override
    public void showPersonInfoError() {
        mUserName.setVisibility(View.GONE);
    }
}
