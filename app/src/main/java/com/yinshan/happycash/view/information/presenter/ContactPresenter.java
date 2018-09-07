package com.yinshan.happycash.view.information.presenter;

import android.content.Context;

import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.network.api.RecordApi;
import com.yinshan.happycash.network.common.RxHttpUtils;
import com.yinshan.happycash.network.common.base.ApiException;
import com.yinshan.happycash.network.common.base.BaseObserver;
import com.yinshan.happycash.network.common.base.RxTransformer;
import com.yinshan.happycash.view.information.model.ContactBean;
import com.yinshan.happycash.view.information.view.IContactView;

import java.lang.ref.SoftReference;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by huxin on 2018/4/2.
 */

public class ContactPresenter {

    Context mContext;
    IContactView mView;

    public ContactPresenter(Context context,IContactView view){
        mContext = context;
        mView = view;
    }

    public void getContactInfo(){
        mView.showLoadingDialog();
        RxHttpUtils.getInstance().createApi(RecordApi.class)
                .getContactInfo(TokenManager.getInstance().getToken())
                .compose(RxTransformer.io_main())
                .subscribe(new BaseObserver<List<ContactBean>>(new SoftReference(mContext)){
                    @Override
                    public void onNext(List<ContactBean> value) {
                        super.onNext(value);
                        mView.dismissLoadingDialog();
                        mView.showContactInfo(value);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        mView.dismissLoadingDialog();
                        super.onError(ex);
                    }
                });
    }

    public void submitContactInfo(String name1,String mobile1,String relative1,String name2,String mobile2,String relative2,
    String name3,String mobile3,String relative3,String name4,String mobile4,String relative4,String name5,String mobile5,String relative5){
        mView.showLoadingDialog();
        RxHttpUtils.getInstance().createApi(RecordApi.class)
                .submitContactInfo(name1,mobile1,relative1,name2,mobile2,relative2,
                        name3,mobile3,relative3,name4,mobile4,relative4,
                        name5,mobile5,relative5, TokenManager.getInstance().getToken())
                .compose(RxTransformer.io_main())
                .subscribe(new BaseObserver<ResponseBody>(new SoftReference(mContext)){
                    @Override
                    public void onNext(ResponseBody body) {
                        super.onNext(body);
                        mView.dismissLoadingDialog();
                        mView.submitContactOk();
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        mView.dismissLoadingDialog();
                        super.onError(ex);
                    }
                });
    }
}
