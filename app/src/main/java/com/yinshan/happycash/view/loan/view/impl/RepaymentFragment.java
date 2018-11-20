package com.yinshan.happycash.view.loan.view.impl;

import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseFragment;
import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.network.common.base.ApiException;
import com.yinshan.happycash.utils.DensityUtil;
import com.yinshan.happycash.utils.MyDebugUtils;
import com.yinshan.happycash.utils.SPKeyUtils;
import com.yinshan.happycash.utils.SPUtils;
import com.yinshan.happycash.utils.StringFormatUtils;
import com.yinshan.happycash.utils.TimeManager;
import com.yinshan.happycash.utils.ToastUtils;
import com.yinshan.happycash.view.information.view.impl.support.InfoAdapterEnum;
import com.yinshan.happycash.view.loan.model.DepositMethodsBean;
import com.yinshan.happycash.view.loan.model.DepositResponseBean;
import com.yinshan.happycash.view.loan.presenter.AdvancePresenter;
import com.yinshan.happycash.view.loan.presenter.RepaymentPresenter;
import com.yinshan.happycash.view.loan.view.IAdvanceView;
import com.yinshan.happycash.view.loan.view.IRepaymentView;
import com.yinshan.happycash.view.loan.view.impl.support.FullRepaymentDialog;
import com.yinshan.happycash.view.loan.view.impl.support.RepaymentAdapter;
import com.yinshan.happycash.view.loan.view.impl.support.RepaymentDialog;
import com.yinshan.happycash.view.loan.view.impl.support.RepaymentDialogAdapter;
import com.yinshan.happycash.view.main.model.LastLoanAppBean;
import com.yinshan.happycash.view.main.view.impl.MainActivity;
import com.yinshan.happycash.view.me.model.LoanDetailBean;
import com.yinshan.happycash.view.me.model.PrePaymentBean;
import com.yinshan.happycash.view.me.model.StageBean;
import com.yinshan.happycash.view.me.presenter.LoanDetailPresenter;
import com.yinshan.happycash.view.me.view.ILoanDetailView;
import com.yinshan.happycash.widget.HappySnackBar;
import com.yinshan.happycash.widget.common.CommonClickListener;
import com.yinshan.happycash.widget.dialog.InAdavanceRepayDialog;
import com.yinshan.happycash.widget.dialog.RepaymentShowDetailDialog;
import com.yinshan.happycash.widget.pullrefresh.MyRefreshHeader;
import com.yinshan.happycash.widget.pullrefresh.RefreshLayout;

import butterknife.OnClick;

/**
 * 待还款界面
 * Created by huxin on 2018/2/1.
 */

public class RepaymentFragment extends BaseFragment implements ILoanDetailView,IRepaymentView,IAdvanceView{

    RefreshLayout refreshLayout;
    ListView listView;
    RepaymentAdapter mAdapter;

    TextView mClickFullPay;

    LinearLayout mViewPrePay;
    RelativeLayout mClickPrePay;

    TextView mCurrentReturn;
    TextView mPaymentDate;
    TextView mMoney;
    TextView mTenor;
    TextView mTitlePeriod;

    LoanDetailPresenter mDetailPresenter;
    RepaymentPresenter mPresenter;
    AdvancePresenter mAdvancePresenter;

    RepaymentDialog mDialog;

    public static DepositResponseBean depositRB;

    LoanDetailBean mDetail;

    @Override
    protected void initView() {
        RxBus.get().register(this);
        mAdapter = new RepaymentAdapter(getActivity());
        listView.setAdapter(mAdapter);

        MyRefreshHeader header = new MyRefreshHeader(getActivity());
        refreshLayout.setRefreshHeader(header);
        initPullRefresh();

        mDetailPresenter = new LoanDetailPresenter(getActivity(),this);
        mAdvancePresenter = new AdvancePresenter(getActivity(),this);
        refresh();

        mPresenter = new RepaymentPresenter(getActivity(),this);
    }

    private void resume(){

    }

    @Override
    protected void initUIValue(View view) {
        refreshLayout = (RefreshLayout)view.findViewById(R.id.refreshLayout);
        mClickFullPay = (TextView)view.findViewById(R.id.clickFullPay);

        listView = (ListView)view.findViewById(R.id.listView);
        mCurrentReturn = (TextView)view.findViewById(R.id.currentReturn);
        mPaymentDate = (TextView)view.findViewById(R.id.paymentDate);
        mMoney = (TextView)view.findViewById(R.id.money);
        mTenor = (TextView)view.findViewById(R.id.tenor);
        mTitlePeriod = (TextView)view.findViewById(R.id.titlePeriod);

        mViewPrePay = (LinearLayout) view.findViewById(R.id.viewPrePay);
        mClickPrePay = (RelativeLayout) view.findViewById(R.id.clickPrePay);

        mClickPrePay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdvancePresenter.getAdvance(mDetail.getLoanAppId());
            }
        });
        mClickFullPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDetail!=null&&mDetail.getLpayDtoList()!=null||mDetail.getLpayDtoList().size()>0){
                    FullRepaymentDialog dialog = new FullRepaymentDialog(getActivity(), new CommonClickListener() {
                        @Override
                        public void onClick() {
                            mPresenter.getRepaymentList(-1);
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
//        if(true){
        if(detail.isQualification()){
            mViewPrePay.setVisibility(View.VISIBLE);
        }else{
            mViewPrePay.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.btnPay)
    public void btnPay(View view){

        mPresenter.getRepaymentList(-1);
    }

    @Override
    public void getRepaymentListOk(DepositMethodsBean bean, double shouldPrePay) {
//        SPUtils.getInstance().setObject(SPKeyUtils.DEPOSITMETHODS_METHODS,bean);
        mDialog = new RepaymentDialog(getActivity(),bean,shouldPrePay);
        mDialog.show();
    }

    @Override
    public void getRepaymentListFail(ApiException ex) {
        ToastUtils.showShort(ex.getMessage());
    }

    //跳转到还款步骤页面
    @Override
    public void getDepositOk(DepositResponseBean bean, double shouldPrePay) {
        MyDebugUtils.v("the wantImage is "+getActivity());
        if(shouldPrePay != -1)
            bean.setAmount((long) shouldPrePay);
        depositRB = bean;

        if(getActivity()==null) {

        }else{
            jump();
        }
    }

    private void jump(){
        Intent intent = new Intent(getActivity(),BankPaymentStepActivity.class);
        startActivity(intent);
    }

    @Override
    public void getDepositFail(ApiException ex) {
        ToastUtils.showShort(ex.getMessage());
    }

    @Subscribe
    public void onGetRe(RepaymentAdapter.RepaymentDetailEvent event){
        RepaymentShowDetailDialog showDetailDialog = new RepaymentShowDetailDialog(getActivity());
        Window win = showDetailDialog.getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.x = listView.getLeft()+DensityUtil.dip2px(getActivity(),28);
        lp.y = listView.getTop()-DensityUtil.dip2px(getActivity(),3)+event.pos* DensityUtil.dip2px(getActivity(),34);
        showDetailDialog.getWindow().setAttributes(lp);
        showDetailDialog.setText1(String.format(getResources().getString(R.string.repayment_detail_1)
                ,String.valueOf(mDetail.getLpayDtoList().get(event.pos).getPrincipalAccr())));
        showDetailDialog.setText2(String.format(getResources().getString(R.string.repayment_detail_2)
                ,String.valueOf(mDetail.getLpayDtoList().get(event.pos).getInterestAccr())));
        showDetailDialog.setText3(String.format(getResources().getString(R.string.repayment_detail_3)
                ,String.valueOf(mDetail.getLpayDtoList().get(event.pos).getDefaultAccr())));
        showDetailDialog.show();
    }

    @Subscribe
    public void sendRepayRequest(RepaymentDialogAdapter.RepaymentDialogEvent<String> event){
        if(mDialog!=null&&mDialog.isShowing())
            mDialog.dismiss();
        if(event.type!=InfoAdapterEnum.RepaymentType)
            return;
        String str = event.data;
        MyDebugUtils.v(str);
        doRepayRequest(str,event.shouldPrePay);

    }

    public void doRepayRequest(String str, double shouldPrePay){
        LastLoanAppBean bean = SPUtils.getInstance().getObject(SPKeyUtils.LOANAPPBEAN,LastLoanAppBean.class);
        String appId;
        if(bean!=null&&bean.getLoanAppId()!=null){
            appId = bean.getLoanAppId();
            mPresenter.doDeposit(appId,str,shouldPrePay);
        }
    }

    public static double getSum(StageBean bean){
        return bean.getPrincipalAccr()-bean.getPrincipalPaid()+bean.getDefaultAccr()-bean.getDefaultPaid()+bean.getInterestAccr()-bean.getInterestPaid();
    }

    public static double getSumAccr(StageBean bean){
        return bean.getPrincipalAccr()+bean.getDefaultAccr()+bean.getInterestAccr();
    }

    //刷新的方法
    private void refresh() {
        ((MainActivity) getActivity()).updateStatus(TokenManager.getInstance().getToken());
        LastLoanAppBean object = SPUtils.getInstance().getObject(SPKeyUtils.LOANAPPBEAN, LastLoanAppBean.class);
        if (object != null) {
            if (object.getLoanAppId() != null)
                mDetailPresenter.getDetail(Long.valueOf(object.getLoanAppId()));
        }
    }

    private void initPullRefresh() {
        refreshLayout.setRefreshListener(new RefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
                refreshLayout.refreshComplete();
            }
        });
        MyRefreshHeader header = new MyRefreshHeader(getActivity());
        refreshLayout.setRefreshHeader(header);
    }

    @Override
    public void showAdvanceFail(String displayMessage) {
        HappySnackBar.showSnackBar(mMoney,displayMessage,SPKeyUtils.SNACKBAR_TYPE_WORN);

    }

    @Override
    public void showAdvance(PrePaymentBean bean) {
        CommonClickListener listener = new CommonClickListener() {
            @Override
            public void onClick() {
                mPresenter.getRepaymentList(bean.getAdvanceFeeAmount());
            }
        };
        InAdavanceRepayDialog dialog = new InAdavanceRepayDialog(getActivity(),listener,bean);
        dialog.show();
    }
}
