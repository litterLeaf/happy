package com.yinshan.happycash.view.loan.presenter;

import android.content.Context;
import android.util.Log;

import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.network.api.LoanApi;
import com.yinshan.happycash.network.api.RecordApi;
import com.yinshan.happycash.network.common.RxHttpUtils;
import com.yinshan.happycash.network.common.base.ApiException;
import com.yinshan.happycash.network.common.base.BaseObserver;
import com.yinshan.happycash.network.common.base.CodeException;
import com.yinshan.happycash.network.common.base.RxTransformer;
import com.yinshan.happycash.view.bindcard.model.BandCardBean;
import com.yinshan.happycash.view.loan.model.ApplyLoanAppsBean;
import com.yinshan.happycash.view.loan.model.ApplyPurpose;
import com.yinshan.happycash.view.loan.view.ILoaningView;

import java.lang.ref.SoftReference;
import java.util.List;

import retrofit2.HttpException;
import retrofit2.http.Field;
import retrofit2.http.Header;

/**
 * Created by huxin on 2018/5/2.
 */

public class LoaningPresenter {

    Context mContext;
    ILoaningView mView;
    RecordApi mApi;
    LoanApi mLoanApi;

    public LoaningPresenter(Context context,ILoaningView view){
        mContext = context;
        mView = view;
        mApi = RxHttpUtils.getInstance().createApi(RecordApi.class);
        mLoanApi = RxHttpUtils.getInstance().createApi(LoanApi.class);
    }

    public void getBankCard(){
        mApi.getBindCard(TokenManager.getInstance().getToken())
                .compose(RxTransformer.io_main())
                .subscribe(new BaseObserver<BandCardBean>(new SoftReference(mContext)){
                    @Override
                    public void onNext(BandCardBean bean) {
                        super.onNext(bean);
                        mView.showBindBankCard(bean);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        Log.e("bankCardDto","bindCard"+ex);
                    }
                });
    }

    public void getPurpose(){
        mLoanApi.getApplyPurposes(TokenManager.getInstance().getToken())
                .compose(RxTransformer.io_main())
                .subscribe(new BaseObserver<List<ApplyPurpose>>(new SoftReference(mContext)){
                    @Override
                    public void onNext(List<ApplyPurpose> list) {
                        super.onNext(list);
                        mView.showPurpose(list);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                    }
                });
    }

    public void submitLoanApp(String loanType,String amount, String period,String periodUnit,String bankCode,String cardNo,String holderName,String applyPurpose,String applyFor,
                              String applyChannel,String applyPlatform,String imei,String smsCode){
        mLoanApi.applyLoanApp(loanType,amount,period,periodUnit,bankCode,cardNo,holderName,applyPurpose,applyFor,
                applyChannel,applyPlatform,imei,TokenManager.getInstance().getToken(),smsCode)
                .compose(RxTransformer.io_main())
                .subscribe(new BaseObserver<ApplyLoanAppsBean>(new SoftReference(mContext)){
                    @Override
                    public void onNext(ApplyLoanAppsBean value) {
                        super.onNext(value);
                        mView.submitLoanOk();
                        Log.v("huxin","submit ok");
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);

                        if(ex.getCode()== CodeException.E_201_ERROR||ex.getCode()==CodeException.E_EOF_ERROR)
                            mView.submitLoanOk();
                        else
                            mView.submitFail(ex.getDisplayMessage());
                        Log.v("huxin","submit fail"+ex.getCode()+"  "+ex.toString());
                    }
                });
    }
}
