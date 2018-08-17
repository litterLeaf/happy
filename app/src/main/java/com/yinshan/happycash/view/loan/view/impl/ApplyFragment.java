package com.yinshan.happycash.view.loan.view.impl;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
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
import com.yinshan.happycash.view.loan.view.impl.support.ApplyAdapter;
import com.yinshan.happycash.view.main.view.impl.MainActivity;
import com.yinshan.happycash.view.main.model.LastLoanAppBean;
import com.yinshan.happycash.widget.pullrefresh.MyRefreshHeader;
import com.yinshan.happycash.widget.pullrefresh.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by huxin on 2018/4/3.
 * 进度界面
 */

public class ApplyFragment extends BaseFragment{

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.progressView)
    View progressView;
    @BindView(R.id.progressList)
    ListView progressList;
    ApplyAdapter mAdapter;

    @BindView(R.id.textProgress)
    TextView mTextProgress;
    @BindView(R.id.textMoney)
    TextView mTextMoney;
    @BindView(R.id.textTime)
    TextView mTextTime;

    private List<BaseStatusLogsBean> statusLogs = new ArrayList<>();

    @Override
    protected void initView() {

        mAdapter = new ApplyAdapter(getActivity());
        progressList.setAdapter(mAdapter);

        MobAgent.onEvent(MobEvent.IN_ISSUING_FRAGMENT);
        initPullRefresh();
        mResume();

        MyRefreshHeader header = new MyRefreshHeader(getActivity());
        refreshLayout.setRefreshHeader(header);

        setListViewHeightBasedOnChildren(progressList);

    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_apply;
    }

    public void mResume(){
        LastLoanAppBean object = SPUtils.getInstance().getObject(SPKeyUtils.LOANAPPBEAN, LastLoanAppBean.class);
        if(object!=null)
            getRefreshData(object);
    }

    private void getRefreshData(LastLoanAppBean loanAppBean) {
        showData(loanAppBean);
        refreshLayout.setRefreshListener(()-> {
            refreshLayout.refreshComplete();
        });
        if(TextUtils.equals("PAID_OFF",loanAppBean.getStatus())){
            ((MainActivity)getActivity()).showDefaultView();
        }
    }

    private void showData(LastLoanAppBean loanAppBean){
        if(loanAppBean!=null){
            String word =getResources().getString(R.string.process_point_tip)+getORMWord(getActivity(),2,loanAppBean.getStatus());
            mTextProgress.setText(word);

            String period =loanAppBean.getPeriod() + " "+loanAppBean.getPeriodUnit();
            String  totalAmount= "Rp" + StringFormatUtils.moneyFormat(loanAppBean.getTotalAmount());
            String  amount= "Rp" + StringFormatUtils.moneyFormat(loanAppBean.getAmount());
            mTextMoney.setText(totalAmount);
            mTextTime.setText(period);

            mAdapter.setStatusList(loanAppBean.getStatusLogs());
            mAdapter.notifyDataSetChanged();
        }
    }

    private void initPullRefresh() {
        refreshLayout.setRefreshListener(new RefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        MyRefreshHeader header = new MyRefreshHeader(getActivity());
        refreshLayout.setRefreshHeader(header);
    }

    //刷新的方法
    private void refresh() {
        ((MainActivity)getActivity()).updateStatus(TokenManager.getInstance().getToken());
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
}
