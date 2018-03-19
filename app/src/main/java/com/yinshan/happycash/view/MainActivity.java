package com.yinshan.happycash.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.mobileim.IYWLoginService;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.YWLoginParam;
import com.alibaba.mobileim.channel.event.IWxCallback;
import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseActivity;
import com.yinshan.happycash.network.api.UserApi;
import com.yinshan.happycash.network.common.RxHttpUtils;
import com.yinshan.happycash.network.common.base.BaseObserver;
import com.yinshan.happycash.utils.ToastUtils;

import java.lang.ref.SoftReference;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

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
    protected void secondInit() {

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
                    RxHttpUtils.getInstance()
                            .createApi(UserApi.class)
                            .sendSms("0818912345611")
                            .subscribeOn(Schedulers.io())
                            .doOnSubscribe(new Consumer<Disposable>() {
                                @Override
                                public void accept(Disposable disposable) throws Exception {

                                }
                            }).subscribeOn(AndroidSchedulers.mainThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new BaseObserver<ResponseBody>(new SoftReference(MainActivity.this)){
                                @Override
                                public void onNext(ResponseBody value) {
                                    super.onNext(value);
                                    ToastUtils.showShort("success");
                                }

                            });
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

//                RxHttpUtils.getInstance().createService(.)

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

    @OnClick(R.id.id_linearlayout_online_qa)
    public void onBtnOnlineQa(){
//        String token = TokenManager.getInstance().getToken();
//        if (TextUtils.isEmpty(token) || TokenManager.isExpired) {
//            BandaSnackBar.showSnackBar(getWindow().getDecorView(),R.string.show_not_login_yet, SPKey.SNACKBAR_TYPE_INTEENT);
//            startActivity(new Intent(this, LoginActivity.class));
//            return;
//        }
//        showLoading(getString(R.string.show_open_chat_window));
//        UserApi mUserApi = ServiceGenerator.createService(UserApi.class);
//        mUserApi.getChatUserInfo(token)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<YWUser>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        dismissLoading();
//                        BandaSnackBar.showSnackBar(getWindow().getDecorView(),R.string.show_get_user_account_fail, SPKey.SNACKBAR_TYPE_ERROR);
//                    }
//
//                    @Override
//                    public void onNext(YWUser responseBody) {
//                        String userid = responseBody.getUserid();
//                        String password = responseBody.getPassword();
//                        loginYW(userid, password);
//                    }
//                });
    }

    private void loginYW(String userid, String password) {
//        final YWIMKit mIMKit = YWAPI.getIMKitInstance(userid, App.ALI_IM_APP_KEY);
//        mIMKit.setShortcutBadger(0);
//        IYWLoginService loginService = mIMKit.getLoginService();
//        YWLoginParam loginParam = YWLoginParam.createLoginParam(userid, password);
//        loginService.login(loginParam, new IWxCallback() {
//
//            @Override
//            public void onSuccess(Object... arg0) {
//                LoggerWrapper.d(arg0.length);
//
//                for (Object obj : arg0
//                        ) {
//                    LoggerWrapper.d(obj.toString());
//                }
//                begainConversation(mIMKit);
//            }
//
//            @Override
//            public void onProgress(int arg0) {
//                LoggerWrapper.d("progress " + arg0);
//            }
//
//            @Override
//            public void onError(int errCode, String description) {
//                //如果登录失败，errCode为错误码,description是错误的具体描述信息
//                dismissLoading();
//                LoggerWrapper.d("errCode " + errCode + " desc " + description);
//            }
//        });
    }
}
