package com.yinshan.happycash.view.me.view.impl;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseFragment;
import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.view.login.LoginActivity;
import com.yinshan.happycash.view.me.view.impl.AboutActivity;
import com.yinshan.happycash.view.me.view.impl.HelpCenterActivity;
import com.yinshan.happycash.view.me.view.impl.LoanListActivity;
import com.yinshan.happycash.view.me.view.impl.SafeSettingActivity;

import butterknife.OnClick;

/**
 * Created by huxin on 2018/3/13.
 */

public class MeFragment extends BaseFragment{

    @Override
    protected void initView() {
//        mIMKit = YWAPI.getIMKitInstance("", "");
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
}
