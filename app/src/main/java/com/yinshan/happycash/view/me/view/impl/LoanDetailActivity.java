package com.yinshan.happycash.view.me.view.impl;

import android.text.TextUtils;
import android.widget.ListView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.config.inner.AppInnerConfig;
import com.yinshan.happycash.framework.BaseSingleActivity;
import com.yinshan.happycash.utils.StatusManagerUtil;
import com.yinshan.happycash.utils.StringFormatUtils;
import com.yinshan.happycash.utils.TimeManager;
import com.yinshan.happycash.view.me.model.LoanDetailBean;
import com.yinshan.happycash.view.me.model.NameDescData;
import com.yinshan.happycash.view.me.model.StageBean;
import com.yinshan.happycash.view.me.presenter.LoanDetailPresenter;
import com.yinshan.happycash.view.me.view.ILoanDetailView;
import com.yinshan.happycash.view.me.view.impl.support.LoanDetailAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by huxin on 2018/3/16.
 */

public class LoanDetailActivity extends BaseSingleActivity implements ILoanDetailView{

    ListView mListView;
    LoanDetailAdapter mAdapter;

    LoanDetailPresenter mPresenter;

    @Override
    protected String bindTitle() {
        return getResources().getString(R.string.my_loan);
    }

    @Override
    protected int bindDownLayout() {
        return R.layout.activity_loan_detail;
    }

    @Override
    protected void secondInit() {
        mListView = (ListView)findViewById(R.id.listView);
        mLowestBg.setBackgroundColor(getResources().getColor(R.color.app_yellow));
        mAdapter = new LoanDetailAdapter();
        mListView.setAdapter(mAdapter);

        mPresenter = new LoanDetailPresenter(this,this);
        mPresenter.getDetail(getIntent().getLongExtra(AppInnerConfig.LONG,-1));
    }

    @Override
    public void showDetail(LoanDetailBean bean) {
        if(bean==null)
            return;
        List<NameDescData> list = new ArrayList<>();
        list.add(new NameDescData(getResources().getString(R.string.submit_time),checkIsNullTime(bean.getCreateTime())));
        list.add(new NameDescData(getResources().getString(R.string.no_order),String.valueOf(bean.getLoanAppId())));
        list.add(new NameDescData(getResources().getString(R.string.ktp),String.valueOf(bean.getCredentialNo())));
        String periodUnit = bean.getPeriodUnit();
        String lastUnit = "Bulan";
        if(periodUnit.equals("M"))
            lastUnit = "Bulan";
        list.add(new NameDescData(getResources().getString(R.string.time_period),String.valueOf(bean.getPeriod())+" "+lastUnit));
        list.add(new NameDescData(getResources().getString(R.string.name_of_beneficiary_bank),bean.getBankCode()));
        list.add(new NameDescData(getResources().getString(R.string.rule_number_receiver),bean.getCardNo()));
        list.add(new NameDescData(getResources().getString(R.string.disbursement_date),checkIsNullTime(bean.getIssueDate())));



        String dueDate = "";
        if(bean.getLpayDtoList().size()>0){
            int index = -1;
            for(int i=0;i<bean.getLpayDtoList().size();i++){
                StageBean stageBean = bean.getLpayDtoList().get(i);
                if(stageBean.getStatus().equals("ACTIVE"))
                    index = i;
            }
            if(index!=-1)
                dueDate = checkIsNullTime(bean.getLpayDtoList().get(index).getDueDate());
        }

        list.add(new NameDescData(getResources().getString(R.string.due_date),dueDate));
        list.add(new NameDescData(getResources().getString(R.string.date_of_return),checkIsNullTime(bean.getPaidOffDate())));
        list.add(new NameDescData(getResources().getString(R.string.borrowing_costs),"Rp"+ StringFormatUtils.moneyFormat(bean.getPrincipalAmount())));
        double currentRepay = 0f;
        List<StageBean> lpayDtoList = bean.getLpayDtoList();
        if(lpayDtoList!=null&&lpayDtoList.size()>=1){
            currentRepay = lpayDtoList.get(0).getDefaultPaid();
        }
        list.add(new NameDescData(getResources().getString(R.string.current_repay),"Rp"+ StringFormatUtils.moneyFormat(currentRepay)));
        String lastStatus = "";
        if(bean.getPaidOffMode()!=null&&!TextUtils.isEmpty(bean.getPaidOffMode())){
            lastStatus = StatusManagerUtil.getExplainStatus(bean.getStatus(),bean.getPaidOffMode());
        }else {
            lastStatus = StatusManagerUtil.getExplainStatus(bean.getStatus());
        }
        list.add(new NameDescData(getResources().getString(R.string.status),lastStatus));
        mAdapter.addList(list);
        mAdapter.notifyDataSetChanged();
        setListViewHeightBasedOnChildren(mListView);
    }


    private String checkIsNullTime(String params){
        String result;
        if(params==null){
            result= "———";
        }else {
            result = dealDate(params);
        }
        return result;
    }

    //时间处理方法
    private String dealDate( String date){
        String  deal= TimeManager.convertYNTimeDay(date);
        return deal ;
    }
}
