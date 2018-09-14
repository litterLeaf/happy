package com.yinshan.happycash.view.loan.view.impl;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.analytic.event.MobAgent;
import com.yinshan.happycash.analytic.event.MobEvent;
import com.yinshan.happycash.framework.BaseFragment;
import com.yinshan.happycash.framework.BaseStatusLogsBean;
import com.yinshan.happycash.framework.TokenManager;
import com.yinshan.happycash.utils.SPKeyUtils;
import com.yinshan.happycash.utils.SPUtils;
import com.yinshan.happycash.utils.ServiceLoanStatus;
import com.yinshan.happycash.utils.StringFormatUtils;
import com.yinshan.happycash.utils.TimeManager;
import com.yinshan.happycash.view.loan.presenter.ApplyPresenter;
import com.yinshan.happycash.view.loan.view.IApplyView;
import com.yinshan.happycash.view.loan.view.impl.support.ApplyAdapter;
import com.yinshan.happycash.view.main.view.impl.MainActivity;
import com.yinshan.happycash.view.main.model.LastLoanAppBean;
import com.yinshan.happycash.view.me.model.LoanDetailBean;
import com.yinshan.happycash.widget.pullrefresh.MyRefreshHeader;
import com.yinshan.happycash.widget.pullrefresh.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by huxin on 2018/4/3.
 * 进度被审核界面
 */
public class ApplyFragment extends BaseFragment implements IApplyView{

    RefreshLayout refreshLayout;
    View progressView;
    ListView progressList;
    ApplyAdapter mAdapter;

    TextView mTextProgress;
    TextView mTextMoney;
    TextView mTextTime;

    RelativeLayout mViewDown;
    RelativeLayout mViewUp;
    LinearLayout mViewDesc;

    TextView mCreateTime;
    TextView mKtp;
    TextView mSubmitFee;
    TextView mReturnPerPeriod;
    TextView mRecipientBankName;
    TextView mRecipientAccountNo;

    private List<BaseStatusLogsBean> statusLogs = new ArrayList<>();

    ApplyPresenter mPresenter;

    @Override
    protected void initView() {

        mAdapter = new ApplyAdapter(getActivity());
        progressList.setAdapter(mAdapter);

        MobAgent.onEvent(MobEvent.IN_ISSUING_FRAGMENT);
        initPullRefresh();
        resume();

        MyRefreshHeader header = new MyRefreshHeader(getActivity());
        refreshLayout.setRefreshHeader(header);

        setListViewHeightBasedOnChildren(progressList);

        mPresenter = new ApplyPresenter(getActivity(),this);
        refresh();

        shrinkageView();

    }

    @Override
    protected void initUIValue(View view) {
        refreshLayout = (RefreshLayout)view.findViewById(R.id.refreshLayout);
        progressView = (View)view.findViewById(R.id.progressView);
        progressList = (ListView)view.findViewById(R.id.progressList);

        mTextProgress = (TextView)view.findViewById(R.id.textProgress);
        mTextMoney = (TextView)view.findViewById(R.id.textMoney);
        mTextTime = (TextView)view.findViewById(R.id.textTime);
        mViewDown = (RelativeLayout)view.findViewById(R.id.viewDown);
        mViewUp = (RelativeLayout)view.findViewById(R.id.viewUp);
        mViewDesc = (LinearLayout)view.findViewById(R.id.viewDesc);

        mCreateTime = (TextView)view.findViewById(R.id.createTime);
        mKtp = (TextView)view.findViewById(R.id.ktp);
        mSubmitFee = (TextView)view.findViewById(R.id.submitFee);
        mReturnPerPeriod = (TextView)view.findViewById(R.id.returnPerPeriod);
        mRecipientBankName = (TextView)view.findViewById(R.id.recipientBankName);
        mRecipientAccountNo = (TextView)view.findViewById(R.id.recipientAccountNo);
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_apply;
    }

    @OnClick({R.id.viewUp,R.id.viewDown,R.id.cancel})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.viewUp:
                shrinkageView();
                break;
            case R.id.viewDown:
                expandView();
                break;
            case R.id.cancel:
                LastLoanAppBean object = SPUtils.getInstance().getObject(SPKeyUtils.LOANAPPBEAN, LastLoanAppBean.class);
                if(object!=null) {
                    mPresenter.cancel(Long.valueOf(object.getLoanAppId()));
                }

                break;
        }
    }

    private void getRefreshData(LastLoanAppBean loanAppBean) {
        if(loanAppBean!=null&&loanAppBean.getLoanAppId()!=null){
            showData(loanAppBean);
        }
        if(TextUtils.equals("PAID_OFF",loanAppBean.getStatus())){
            ((MainActivity)getActivity()).showDefaultView();
        }
    }

    private void showData(LastLoanAppBean loanAppBean){
        if(loanAppBean!=null){
//            String word =getResources().getString(R.string.process_point_tip)+getORMWord(getActivity(),2,loanAppBean.getStatus());
//            mTextProgress.setText(word);
//
//            String period =loanAppBean.getPeriod() + " "+loanAppBean.getPeriodUnit();
//            String  totalAmount= "Rp" + StringFormatUtils.moneyFormat(loanAppBean.getTotalAmount());
//            String  amount= "Rp" + StringFormatUtils.moneyFormat(loanAppBean.getAmount());
//            mTextMoney.setText(totalAmount);
//            mTextTime.setText(period);

            mAdapter.setStatusList(loanAppBean.getStatusLogs());
            mAdapter.notifyDataSetChanged();
            setListViewHeightBasedOnChildren(progressList);
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

    //刷新的方法
    private void refresh() {
        ((MainActivity) getActivity()).updateStatus(TokenManager.getInstance().getToken());
        LastLoanAppBean object = SPUtils.getInstance().getObject(SPKeyUtils.LOANAPPBEAN, LastLoanAppBean.class);
        if (object != null) {
            if (object.getLoanAppId() != null)
                mPresenter.getDetail(Long.valueOf(object.getLoanAppId()));
        }
    }

    public static String getORMWord(Context context, int type, String status){
        if(ServiceLoanStatus.PRE_REVIEW.equals(status)&&type==0){
            return context.getString(R.string.process_status_preview_title);
        }else if(ServiceLoanStatus.PRE_REVIEW.equals(status)&&type==1){
            return context.getString(R.string.process_status_preview_content);
        }else if(ServiceLoanStatus.PRE_REVIEW.equals(status)&&type==2){
            return " 17%";
        } else if(ServiceLoanStatus.FIRST_REVIEW.equals(status)&&type==0){
            return context.getString(R.string.process_status_firstview_title);
        }else if(ServiceLoanStatus.FIRST_REVIEW.equals(status)&&type==1){
            return context.getString(R.string.process_status_firstview_content);
        }else if(ServiceLoanStatus.FIRST_REVIEW.equals(status)&&type==2){
            return " 34% ";
        }else if(ServiceLoanStatus.SECOND_REVIEW.equals(status)&&type==0){
            return context.getString(R.string.process_status_secondview_title);
        } else if(ServiceLoanStatus.SECOND_REVIEW.equals(status)&&type==1){
            return context.getString(R.string.process_status_secondview_content);
        } else if(ServiceLoanStatus.SECOND_REVIEW.equals(status)&&type==2){
            return " 51%";
        }else if(ServiceLoanStatus.FINAL_REVIEW.equals(status)&&type==0){
            return context.getString(R.string.process_status_finalview_title);
        }else if(ServiceLoanStatus.FINAL_REVIEW.equals(status)&&type==1){
            return context.getString(R.string.process_status_finalview_content);
        }else if(ServiceLoanStatus.FINAL_REVIEW.equals(status)&&type==2){
            return " 68%";
        }else if(ServiceLoanStatus.SUPPLEMENT.equals(status)&&type==0){
            return context.getString(R.string.process_status_supplement_title);
        }else if(ServiceLoanStatus.SUPPLEMENT.equals(status)&&type==1){
            return context.getString(R.string.process_status_supplement_content);
        } else if(ServiceLoanStatus.ISSUING.equals(status)&&type==0){
            return context.getString(R.string.process_status_issuing_title);
        }else if(ServiceLoanStatus.ISSUING.equals(status)&&type==1){
            return context.getString(R.string.process_status_issuing_content);
        } else if(ServiceLoanStatus.ISSUING.equals(status)&&type==2){
            return " 85%";
        }
        if(type==0){
            return status;
        }else {
            return "";
        }
    }

    @Override
    public void showDetailOk(LoanDetailBean bean) {
        if(bean!=null){
            String tStr = getResources().getString(R.string.process_point_tip);
            String word = tStr +getORMWord(getActivity(),2,bean.getStatus());
            mTextProgress.setText(word);

            String pUnit = "Bulan";
            if(bean.getPeriodUnit().equals("M"))
                pUnit = "Bulan";
            String period =bean.getPeriod() + " "+pUnit;
            String  totalAmount= "Rp" + StringFormatUtils.moneyFormat(bean.getPrincipalAmount());
            mTextMoney.setText(totalAmount);
            mTextTime.setText(period);


            mCreateTime.setText(TimeManager.convertTime(bean.getCreateTime()));
            mKtp.setText(bean.getCredentialNo());
            mSubmitFee.setText(String.valueOf(Math.round(bean.getPrincipalAmount())));
            MainActivity.getLastMoney(bean.getPrincipalAmount(),bean.getPeriod());
            mReturnPerPeriod.setText(String.valueOf(Math.round(Math.ceil(bean.getPrincipalAmount()/bean.getPeriod()))));
            mRecipientBankName.setText(bean.getBankCode());
            mRecipientAccountNo.setText(bean.getCardNo());

//            mAdapter.setStatusList(bean.getLpayDtoList());
//            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showDetailFail(String displayMessage) {

    }

    @Override
    public void cancelOk() {
        ((MainActivity) getActivity()).updateStatus(TokenManager.getInstance().getToken());
    }

    @Override
    public void cancelFail() {

    }

    private void expandView(){
        mViewDown.setVisibility(View.GONE);
        mViewDesc.setVisibility(View.VISIBLE);
        mViewUp.setVisibility(View.VISIBLE);
    }

    private void shrinkageView(){
        mViewDown.setVisibility(View.VISIBLE);
        mViewDesc.setVisibility(View.GONE);
        mViewUp.setVisibility(View.GONE);
    }

    public void resume() {
        LastLoanAppBean object = SPUtils.getInstance().getObject(SPKeyUtils.LOANAPPBEAN, LastLoanAppBean.class);
        if(object!=null) {
            getRefreshData(object);
        }
    }
}
