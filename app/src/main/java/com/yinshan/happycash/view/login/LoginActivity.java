package com.yinshan.happycash.view.login;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;
import com.yinshan.happycash.R;
import com.yinshan.happycash.analytic.event.MobAgent;
import com.yinshan.happycash.analytic.event.MobEvent;
import com.yinshan.happycash.framework.BaseActivity;
import com.yinshan.happycash.network.api.CaptchaApi;
import com.yinshan.happycash.utils.MachineUtils;
import com.yinshan.happycash.utils.SPKeyUtils;
import com.yinshan.happycash.utils.SPUtils;
import com.yinshan.happycash.utils.ToolsUtils;
import com.yinshan.happycash.view.login.contract.LoginContract;
import com.yinshan.happycash.view.login.model.LoginTokenResponse;
import com.yinshan.happycash.view.login.presenter.LoginPresenter;
import com.yinshan.happycash.widget.HappySnackBar;
import com.yinshan.happycash.widget.happyedittext.OnCheckInputResultAdapter;
import com.yinshan.happycash.widget.logger.LogUtil;
import com.yinshan.happycash.widget.userdefined.OnCheckInputResult;
import com.yinshan.happycash.widget.userdefined.RupiahEditText;
import com.yinshan.happycash.widget.userdefined.SmsEditText;

import org.apache.commons.lang3.RandomStringUtils;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;

/**
 * Created by admin on 2018/1/31.
 */

public class LoginActivity extends BaseActivity implements LoginContract.View{

    LoginPresenter mPresenter;
    int loginCount = 0;

    boolean isShowTip = false;

    String mSmsCode;
    String mCaptcha;
    String mMobile;
    String mInviteCode;

    @BindView(R.id.mobileLayout)
    LinearLayout mMobileLayout;
    @BindView(R.id.id_edittext_phone_number)
    RupiahEditText mEditMobile;
    @BindView(R.id.login_edit1)
    SmsEditText mSmsCode1;
    @BindView(R.id.login_edit2)
    SmsEditText mSmsCode2;
    @BindView(R.id.login_edit3)
    SmsEditText mSmsCode3;
    @BindView(R.id.login_edit4)
    SmsEditText mSmsCode4;

    //发送短信验证码按钮
    @BindView(R.id.btnSendSms)
    Button mBtnSendSms;
    //登录按钮
    @BindView(R.id.id_button_login)
    Button mBtnLogin;

    @BindView(R.id.id_linearlayout_graphical_code)
    LinearLayout mViewCaptcha;
    @BindView(R.id.id_imageview_code)
    ImageView mImageCaptcha;
    @BindView(R.id.id_edittext_graphical_code)
    EditText mTextCaptcha;
    //图形验证码实际值
    String mSid;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        init();
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void secondLayout() {

    }

    @Override
    protected void secondInit() {

    }

    private void init(){
        mPresenter = new LoginPresenter(this);
        mPresenter.attachView(this);
        updateSendSmsState();
        updateLoginState();
        setFocusListener();
    }

    @OnClick({R.id.id_button_login,R.id.btnSendSms,R.id.id_imageview_code})
    public void onClick(View view){
        switch (view.getId()){

            case R.id.id_button_login:
                mMobile = mEditMobile.getText().toString();
                mSmsCode = getSmsCode();
                mCaptcha = "";
                mInviteCode = "";
                mPresenter.signIn(mSmsCode,mSid,mTextCaptcha.getText().toString(),mMobile,mInviteCode, MachineUtils.getAndroidId(getApplicationContext()));
                break;
            case R.id.btnSendSms:
                if(!isShowTip) {
                    mMobile = mEditMobile.getText().toString();
                    mPresenter.sendSms(mMobile);
                }
                break;
            case R.id.id_imageview_code:
                MobAgent.onEvent(MobEvent.CLICK+MobEvent.REFRESH);
                refreshCaptcha();
                break;
        }

    }
    //连接4个验证码
    private String getSmsCode(){
        String sms1 = mSmsCode1.getText().toString().trim();
        String sms2 = mSmsCode2.getText().toString().trim();
        String sms3 = mSmsCode3.getText().toString().trim();
        String sms4 = mSmsCode4.getText().toString().trim();
        return sms1+sms2+sms3+sms4;
    }

    private void setFocusListener(){
        //mobile改变检查
        mEditMobile.setOnFocusChangeListener((v,hasFocus)-> {
                if(hasFocus){
                    mEditMobile.setCursorVisible(true);
                    mMobileLayout.setActivated(true);
                    InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    manager.showSoftInput(mEditMobile, InputMethodManager.SHOW_IMPLICIT);
                }else{
                    mEditMobile.setCursorVisible(false);
                    mMobileLayout.setActivated(false);
                }
        });

        OnCheckInputResult onCheckInputResult = new OnCheckInputResultAdapter() {
            @Override
            public boolean onCheckResult(EditText v) {
                return changeEditInputState(v);
            }

            @Override
            public void onTextChanged(EditText v, Editable s) {
                super.onTextChanged(v, s);
                changeEditInputState(v);
                updateLoginState();
                updateSendSmsState();
            }
        };
        mEditMobile.setOnCheckInputResult(onCheckInputResult);
        mSmsCode1.addTextChangedListener(mCodeWatcher);
        mSmsCode2.addTextChangedListener(mCodeWatcher);
        mSmsCode3.addTextChangedListener(mCodeWatcher);
        mSmsCode4.addTextChangedListener(mCodeWatcher);
        mSmsCode1.setOnEditorActionListener(mEditorListener);
        mSmsCode2.setOnEditorActionListener(mEditorListener);
        mSmsCode3.setOnEditorActionListener(mEditorListener);
        mSmsCode4.setOnEditorActionListener(mEditorListener);
    }

    //更新发送短信验证码按钮状态
    private void updateSendSmsState(){
        if(ToolsUtils.checkInputWording(mEditMobile.getText().toString().trim())){
            mBtnSendSms.setClickable(true);
            mBtnSendSms.setAlpha(0.8f);
        }else{
            mBtnSendSms.setClickable(true);
            mBtnSendSms.setAlpha(0.3f);
        }
    }

    private boolean changeEditInputState(TextView v){
        if(v == mEditMobile && !ToolsUtils.checkInputWording(v.getText().toString())){
            v.setTextColor(Color.RED);
            return false;
        }else{
            v.setTextColor(getResources().getColor(R.color.hc_input_text));
            return true;
        }
    }

    private boolean checkoutComplete(){
        mSmsCode = getSmsCode();
        boolean result;
        if(TextUtils.isEmpty(mEditMobile.getText().toString())){
            result = false;
        }else if(TextUtils.isEmpty(mSmsCode) || mSmsCode.length()<4){
            result = false;
        }else{
            result = true;
        }
        return result;
    }

    private void updateLoginState() {
        if (checkoutComplete()) {
            mBtnLogin.setClickable(true);
            mBtnLogin.setAlpha(0.8f);
        } else {
            mBtnLogin.setClickable(false);
            mBtnLogin.setAlpha(0.3f);
        }
    }

    private TextWatcher mCodeWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(mSmsCode1.isFocused()){
                if(s.length()==1){
                    mSmsCode1.clearFocus();
                    mSmsCode2.requestFocus();
                }else if(s.length()>1){
                    mSmsCode1.setText(mSmsCode1.getText().toString().substring(mSmsCode1.getText().length()-1));
                }
            }else if(mSmsCode2.isFocused()) {
                if (s.length() == 1) {
                    mSmsCode2.clearFocus();
                    mSmsCode3.requestFocus();
                } else if (s.length() > 1) {
                    mSmsCode2.setText(mSmsCode2.getText().toString().substring(mSmsCode2.getText().length() - 1));
                } else if (s.length() == 0) {
                    mSmsCode2.clearFocus();
                    mSmsCode1.requestFocus();
                }
            }else if (mSmsCode3.isFocused()) {
                if (s.length() == 1) {
                    mSmsCode3.clearFocus();
                    mSmsCode4.requestFocus();
                } else if (s.length() > 1) {
                    mSmsCode3.setText(mSmsCode3.getText().toString().substring(mSmsCode3.getText().length() - 1));
                } else if (s.length() == 0) {
                    mSmsCode3.clearFocus();
                    mSmsCode2.requestFocus();
                }
            } else if (mSmsCode4.isFocused()) {
                if (s.length() == 1) {
                    mSmsCode4.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mSmsCode4.getWindowToken(), 0);
                } else if (s.length() > 1) {
                    mSmsCode4.setText(mSmsCode4.getText().toString().substring(mSmsCode4.getText().length() - 1));
                } else if (s.length() == 0) {
                    mSmsCode4.clearFocus();
                    mSmsCode3.requestFocus();
                }
            }
            updateLoginState();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    //点击软键盘回车键的处理
    private TextView.OnEditorActionListener mEditorListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                mSmsCode1.clearFocus();
                mSmsCode2.clearFocus();
                mSmsCode3.clearFocus();
                mSmsCode4.clearFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mSmsCode4.getWindowToken(), 0);
            }
            return false;
        }
    };

    @Override
    public void signInSuccess(String mobile, LoginTokenResponse loginTokenResponse) {
        loginCount = 0;
        HappySnackBar.showSnackBar(mViewCaptcha, R.string.login_success, SPKeyUtils.SNACKBAR_TYPE_TIP);
        SPUtils.getInstance().setToken(loginTokenResponse.getToken());
        SPUtils.getInstance().setMobile(mMobile);
        finish();
    }

    @Override
    public void signInError(String message) {
        loginCount++;
        if(loginCount>=2){
            mViewCaptcha.setVisibility(View.VISIBLE);
            refreshCaptcha();
        }
        LogUtil.getInstance().e(message);
        HappySnackBar.showSnackBar(mViewCaptcha, R.string.show_input_error, SPKeyUtils.SNACKBAR_TYPE_WORN);
    }

    @Override
    public void getSMSCodeSuccess(ResponseBody responseBody) {
        new TimeCount(60000, 1000).start();

    }

    private void refreshCaptcha(){
        mSid = RandomStringUtils.randomAlphanumeric(5);
        Glide.with(this)
                .load(CaptchaApi.formatCaptchaUrl(""+ mSid))
                .fitCenter()
                .signature(new StringSignature(""+System.currentTimeMillis()))
                .into(mImageCaptcha);
    }


    /**
     * 统计注册用户的埋点
     *
     * @param loginTokenResponse
     */
    private void pushAppFlyer(LoginTokenResponse loginTokenResponse) {
//        String imei = BandaAppSP.getInstance().getImei();
//        AppsFlyerLib.getInstance().setCustomerUserId(MachineUtils.getAndroidId(getApplicationContext()));
//        if (!TextUtils.isEmpty(loginTokenResponse.getAction())) {
//            if ("register".equals(loginTokenResponse.getAction())) {
//                Map<String, Object> eventValue = new HashMap<String, Object>();
//                eventValue.put("Register", "CustomerIMei:" + imei);
//                AppsFlyerLib.getInstance().trackEvent(getApplicationContext(), "Register", eventValue);
//            }
//        }
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }


        @Override
        public void onFinish() {
            mBtnSendSms.setText(getResources().getText(R.string.button_obtain_code));
            mBtnSendSms.setSelected(false);
            mBtnSendSms.setClickable(true);
            mBtnSendSms.setAlpha(0.8f);
            isShowTip = false;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mBtnSendSms.setSelected(true);
            mBtnSendSms.setClickable(false);
            mBtnSendSms.setAlpha(0.3f);
            mBtnSendSms.setText(millisUntilFinished / 1000 + "s");
            isShowTip = true;
        }
    }
}
