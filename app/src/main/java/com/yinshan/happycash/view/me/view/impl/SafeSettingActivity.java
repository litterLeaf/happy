package com.yinshan.happycash.view.me.view.impl;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.application.AppManager;
import com.yinshan.happycash.framework.BaseSingleNoScrollActivity;
import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.utils.SPUtils;
import com.yinshan.happycash.view.main.view.impl.MainActivity;
import com.yinshan.happycash.view.me.presenter.SafeSettingPresenter;
import com.yinshan.happycash.view.me.view.ISafeSettingView;
import com.yinshan.happycash.widget.common.CommonClickListener;
import com.yinshan.happycash.widget.dialog.CommonDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by huxin on 2018/3/20.
 * 安全设置界面
 */

public class SafeSettingActivity extends BaseSingleNoScrollActivity implements ISafeSettingView{

    TextView mName;
    TextView mPhone;

    SafeSettingPresenter mPresenter;

    @Override
    protected String bindTitle() {
        return getResources().getString(R.string.safe_setting);
    }

    @Override
    protected int bindDownLayout() {
        return R.layout.activity_safe_setting;
    }

    @Override
    protected void secondInit() {
        mName = (TextView)findViewById(R.id.name);
        mPhone = (TextView)findViewById(R.id.phone);
        mPresenter = new SafeSettingPresenter(this,this);
        mName.setText(SPUtils.getInstance().getUsername());
        mPhone.setText(SPUtils.getInstance().getMobile());
    }

    @OnClick({R.id.btnQuit})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.btnQuit:
                submitQuit();
                break;
        }
    }

    private void submitQuit(){
        CommonClickListener click = new CommonClickListener() {
            @Override
            public void onClick() {
                mPresenter.logout();
            }
        };
        String desc = getResources().getString(R.string.dialog_ensure_logout);
        CommonDialog dialog = new CommonDialog(this, click,null,desc,null,null,true);
        dialog.show();
    }

    private void quitClear(){
        SPUtils.clearAll();
    }

    @Override
    public void logoutOk() {
        quitClear();
        AppManager.getInstance().finishAllActivity();
        startActivity(new Intent(this, MainActivity.class));
    }
}
