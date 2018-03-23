package com.yinshan.happycash.view.me;

import android.content.Intent;
import android.view.View;

import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseFragment;
import com.yinshan.happycash.view.me.view.impl.AboutActivity;
import com.yinshan.happycash.view.me.view.impl.HelpCenterActivity;
import com.yinshan.happycash.view.me.view.impl.LoanListActivity;
import com.yinshan.happycash.view.me.view.impl.SafeSettingActivity;

import butterknife.OnClick;

/**
 * Created by huxin on 2018/3/13.
 */

public class MeFragment extends BaseFragment{

    YWIMKit mIMKit;

    @Override
    protected void initView() {
//        mIMKit = YWAPI.getIMKitInstance("", "");
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_me;
    }

    @OnClick({R.id.loanView,R.id.safeSettingView,R.id.helpCenterView,R.id.customHotLineView,R.id.aboutView})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.loanView:
                startActivity(new Intent(getContext(), LoanListActivity.class));
                break;
            case R.id.safeSettingView:
                startActivity(new Intent(getContext(), SafeSettingActivity.class));
                break;
            case R.id.helpCenterView:
                startActivity(new Intent(getContext(), HelpCenterActivity.class));
                break;
            case R.id.customHotLineView:
//                Intent intent = mIMKit.getConversationActivityIntent();
//                startActivity(intent);
                break;
            case R.id.aboutView:
                startActivity(new Intent(getContext(), AboutActivity.class));
                break;
        }
    }
}
