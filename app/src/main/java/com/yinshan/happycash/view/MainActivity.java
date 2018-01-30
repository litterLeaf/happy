package com.yinshan.happycash.view;


import android.util.Log;
import android.widget.TextView;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.yinshan.happycash.R;
import com.yinshan.happycash.network.api.UserApi;
import com.yinshan.happycash.network.common.RxHttpUtils;
import com.yinshan.happycash.utils.ToastUtils;
import com.yinshan.happycash.view.base.BaseActivity;

import javax.annotation.Nonnull;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
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
 *  @author  admin
 *  on 2018/1/11
 *
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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
            RxHttpUtils.getInstance()
                    .createService(UserApi.class)
                    .sendSms("12345678901")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ResponseBody>() {
                        @Override
                        public void onComplete() {

                        }
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ResponseBody responseBody) {
                            Log.i("123","123");
                        }

                        @Override
                        public void onError(Throwable e) {
                                e.printStackTrace();
                        }
                    });
    }


}
