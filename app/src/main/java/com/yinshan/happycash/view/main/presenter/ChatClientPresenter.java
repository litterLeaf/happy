package com.yinshan.happycash.view.main.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.easeui.util.IntentBuilder;
import com.hyphenate.helpdesk.model.ContentFactory;
import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseActivity;
import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.network.api.OtherApi;
import com.yinshan.happycash.network.api.UserApi;
import com.yinshan.happycash.network.common.RxHttpUtils;
import com.yinshan.happycash.network.common.base.BaseSubscriber;
import com.yinshan.happycash.utils.SPKeyUtils;
import com.yinshan.happycash.utils.SPUtils;
import com.yinshan.happycash.utils.ToastUtils;
import com.yinshan.happycash.view.login.LoginActivity;
import com.yinshan.happycash.view.main.contract.ChatClientContract;
import com.yinshan.happycash.view.main.contract.SplashContract;
import com.yinshan.happycash.view.main.model.HXBean;
import com.yinshan.happycash.view.main.model.YWUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
 *            ┃┫┫　┃┫┫
 *            ┗┻┛　┗┻┛
 * <p>
 * 文件描述：
 * 创建人：    admin
 * 创建时间：2018/8/28
 */
public class ChatClientPresenter implements ChatClientContract.Presenter {
    private Context context;
    private ChatClientContract.View view;

    public ChatClientPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void attachView(@NonNull ChatClientContract.View mvpView) {
        this.view = mvpView;

    }

    @Override
    public void detachView() {
        this.view = null;
    }



    @Override
    public void getMessage(String tenantId) {
        RxHttpUtils.getInstance().baseUrl(SPKeyUtils.HX_API_URL)
                .createApi(OtherApi.class)
                .getMessage(tenantId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HXBean>() {
                    @Override
                    public void onFailure(Throwable e) {
                        view.getMessageError(e.getMessage());
                    }

                    @Override
                    public void onSuccess(HXBean hxBean) {
                        view.getMessageSuccess(hxBean);
                    }
                });
    }

    @Override
    public void getChatClientAccount(String token) {
        RxHttpUtils.getInstance()
                .createApi(OtherApi.class)
                .getChatUserInfo(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<YWUser>() {
                    @Override
                    public void onSuccess(YWUser ywUser) {
                        view.getChatAccountSuccess(ywUser);
                    }

                    @Override
                    public void onFailure(Throwable e) {
                        view.getChatAccountFailure(e.getMessage());
                    }
                });
    }
}
