package com.yinshan.happycash.view.loan.view.impl;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseFragment;
import com.yinshan.happycash.framework.DateManager;
import com.yinshan.happycash.network.common.base.ApiException;
import com.yinshan.happycash.utils.DebugUtil;
import com.yinshan.happycash.utils.MyDebugUtils;
import com.yinshan.happycash.utils.PaymentMethodManager;
import com.yinshan.happycash.utils.SPKeyUtils;
import com.yinshan.happycash.utils.SPUtils;
import com.yinshan.happycash.utils.StringFormatUtils;
import com.yinshan.happycash.utils.TimeManager;
import com.yinshan.happycash.utils.ToastUtils;
import com.yinshan.happycash.view.information.view.impl.support.InfoAdapterEnum;
import com.yinshan.happycash.view.loan.model.DepositMethodsBean;
import com.yinshan.happycash.view.loan.model.DepositResponseBean;
import com.yinshan.happycash.view.loan.presenter.RepaymentPresenter;
import com.yinshan.happycash.view.loan.view.IRepaymentView;
import com.yinshan.happycash.view.loan.view.impl.support.FullRepaymentDialog;
import com.yinshan.happycash.view.loan.view.impl.support.RepaymentAdapter;
import com.yinshan.happycash.view.loan.view.impl.support.RepaymentDialog;
import com.yinshan.happycash.view.main.model.LastLoanAppBean;
import com.yinshan.happycash.view.me.model.LoanDetailBean;
import com.yinshan.happycash.view.me.model.StageBean;
import com.yinshan.happycash.view.me.presenter.LoanDetailPresenter;
import com.yinshan.happycash.view.me.view.ILoanDetailView;
import com.yinshan.happycash.view.me.view.impl.RepaymentStrategyActivity;
import com.yinshan.happycash.widget.common.CommonClickListener;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 待还款界面
 * Created by huxin on 2018/2/1.
 */

public class RepaymentFragment extends BaseFragment implements ILoanDetailView,IRepaymentView{

    ListView listView;
    RepaymentAdapter mAdapter;

    TextView mClickFullPay;

    TextView mCurrentReturn;
    TextView mPaymentDate;
    TextView mMoney;
    TextView mTenor;
    TextView mTitlePeriod;

    LoanDetailPresenter mDetailPresenter;
    RepaymentPresenter mPresenter;

    RepaymentDialog mDialog;

    public static DepositResponseBean depositRB;

    LoanDetailBean mDetail;

    @Override
    protected void initView() {
        RxBus.get().register(this);
        mAdapter = new RepaymentAdapter(getActivity());
        listView.setAdapter(mAdapter);

        mDetailPresenter = new LoanDetailPresenter(getActivity(),this);
        mDetailPresenter.getDetail(Long.valueOf(SPUtils.getInstance().getObject(SPKeyUtils.LOANAPPBEAN,LastLoanAppBean.class).getLoanAppId()));

        mPresenter = new RepaymentPresenter(getActivity(),this);
    }

    @Override
    protected void initUIValue(View view) {
        mClickFullPay = (TextView)view.findViewById(R.id.clickFullPay);

        listView = (ListView)view.findViewById(R.id.listView);
        mCurrentReturn = (TextView)view.findViewById(R.id.currentReturn);
        mPaymentDate = (TextView)view.findViewById(R.id.paymentDate);
        mMoney = (TextView)view.findViewById(R.id.money);
        mTenor = (TextView)view.findViewById(R.id.tenor);
        mTitlePeriod = (TextView)view.findViewById(R.id.titlePeriod);

        mClickFullPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDetail!=null&&mDetail.getLpayDtoList()!=null||mDetail.getLpayDtoList().size()>0){
                    FullRepaymentDialog dialog = new FullRepaymentDialog(getActivity(), new CommonClickListener() {
                        @Override
                        public void onClick() {
                            mPresenter.getRepaymentList();
                        }
                    },mDetail);
                    dialog.show();
                }
            }
        });
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_repayment;
    }

    @Override
    public void showDetail(LoanDetailBean detail) {
        mDetail = detail;
        if(detail.getLpayDtoList()!=null||detail.getLpayDtoList().size()>0){
//            SPUtils.getInstance().setObject("REPAYMENT_LOAN_DETAIL",d);
            //寻找第一个ACTIVE的，
            String stage = TimeManager.getRpTime(detail.getPeriodUnit());
            int count = 0;
            int firstActive = -1;
            for(int i=0;i<detail.getLpayDtoList().size();i++){
                if(detail.getLpayDtoList().get(i).getStatus().equals("INACTIVE")) {
                    count++;
                }else if(firstActive==-1&&detail.getLpayDtoList().get(i).getStatus().equals("ACTIVE")){
                    firstActive = i;
                }
            }

            if(firstActive!=-1){
                mCurrentReturn.setText(StringFormatUtils.moneyFormat(getSum(detail.getLpayDtoList().get(firstActive))));
                mPaymentDate.setText(getString(R.string.payment_date_f,TimeManager.convertYNTimeDay(detail.getLpayDtoList().get(firstActive).getDueDate())));
            }

            mTenor.setText(detail.getPeriod()+" "+stage+"/"+count+" "+stage+" lunas");
            mTitlePeriod.setText(getString(R.string.time_period2)+"("+count+"/"+detail.getPeriod()+")");
            mAdapter.setList(detail.getLpayDtoList());
            mAdapter.notifyDataSetChanged();
            setListViewHeightBasedOnChildren(listView);
        }
        mMoney.setText("Rp "+ StringFormatUtils.moneyFormat(detail.getPrincipalAmount()));
    }

    @OnClick(R.id.btnPay)
    public void btnPay(View view){
        mPresenter.getRepaymentList();
    }

    @Override
    public void getRepaymentListOk(DepositMethodsBean bean) {
//        SPUtils.getInstance().setObject(SPKeyUtils.DEPOSITMETHODS_METHODS,bean);
        mDialog = new RepaymentDialog(getActivity(),bean);
        mDialog.show();
    }

    @Override
    public void getRepaymentListFail(ApiException ex) {
        ToastUtils.showShort(ex.getMessage());
    }

    @Override
    public void getDepositOk(DepositResponseBean bean) {
        depositRB = bean;
        int depositRBType = PaymentMethodManager.getPaymentStepsLayout(bean);
        Intent intent = new Intent(getActivity(),BankPaymentStepActivity.class);
        intent.putExtra("Steps",depositRBType);
        startActivity(intent);
    }

    @Override
    public void getDepositFail(ApiException ex) {
        ToastUtils.showShort(ex.getMessage());
    }


    @Subscribe
    public void sendRepayRequest(InfoAdapterEnum.ItemSelectedEvent<String> event){
        if(mDialog!=null&&mDialog.isShowing())
            mDialog.dismiss();
        String str = event.data;
        MyDebugUtils.v(str);
        doRepayRequest(str);

    }

    public void doRepayRequest(String str){
        LastLoanAppBean bean = SPUtils.getInstance().getObject(SPKeyUtils.LOANAPPBEAN,LastLoanAppBean.class);
        String appId;
        if(bean!=null&&bean.getLoanAppId()!=null){
            appId = bean.getLoanAppId();
        }else{
            return;
        }
        mPresenter.doDeposit(appId,str);
    }

    public static double getSum(StageBean bean){
        return bean.getPrincipalAccr()-bean.getPrincipalPaid()+bean.getDefaultAccr()-bean.getDefaultPaid()+bean.getInterestAccr()-bean.getInterestPaid();
    }
}
