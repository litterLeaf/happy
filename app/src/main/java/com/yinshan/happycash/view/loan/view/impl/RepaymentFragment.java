package com.yinshan.happycash.view.loan.view.impl;

import android.widget.ListView;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseFragment;
import com.yinshan.happycash.framework.DateManager;
import com.yinshan.happycash.utils.SPKeyUtils;
import com.yinshan.happycash.utils.StringFormatUtils;
import com.yinshan.happycash.utils.TimeManager;
import com.yinshan.happycash.view.loan.view.impl.support.RepaymentAdapter;
import com.yinshan.happycash.view.main.model.LastLoanAppBean;
import com.yinshan.happycash.view.me.model.LoanDetailBean;
import com.yinshan.happycash.view.me.presenter.LoanDetailPresenter;
import com.yinshan.happycash.view.me.view.ILoanDetailView;

import butterknife.BindView;

/**
 * Created by huxin on 2018/2/1.
 */

public class RepaymentFragment extends BaseFragment implements ILoanDetailView{

    @BindView(R.id.listView)
    ListView listView;
    RepaymentAdapter mAdapter;

    @BindView(R.id.currentReturn)
    TextView mCurrentReturn;
    @BindView(R.id.paymentDate)
    TextView mPaymentDate;
    @BindView(R.id.money)
    TextView mMoney;
    @BindView(R.id.tenor)
    TextView mTenor;
    @BindView(R.id.titlePeriod)
    TextView mTitlePeriod;

    LoanDetailPresenter mDetailPresenter;

    @Override
    protected void initView() {
        mAdapter = new RepaymentAdapter(getActivity());
        listView.setAdapter(mAdapter);

        mDetailPresenter = new LoanDetailPresenter(getActivity(),this);
        mDetailPresenter.getDetail(Long.valueOf(((LastLoanAppBean)DateManager.getInstance().getMessage(SPKeyUtils.LOANAPPBEAN)).getLoanAppId()));
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_repayment;
    }

    @Override
    public void showDetail(LoanDetailBean detail) {
        if(detail.getLpayDtoList()!=null||detail.getLpayDtoList().size()>0){
            mCurrentReturn.setText(StringFormatUtils.moneyFormat(detail.getLpayDtoList().get(0).getPrincipalAccr()));
            mPaymentDate.setText(getString(R.string.payment_date_f,TimeManager.convertYNTimeDay(detail.getLpayDtoList().get(0).getDueDate())));

            String stage = TimeManager.getRpTime(detail.getPeriodUnit());
            int count = 0;
            for(int i=0;i<detail.getLpayDtoList().size();i++){
                if(detail.getLpayDtoList().get(i).getStatus().equals("INACTIVE")){
                    count++;
                }
            }
            mTenor.setText(detail.getPeriod()+" "+stage+"/"+count+" "+stage+" lunas");
            mTitlePeriod.setText(getString(R.string.time_period2)+"("+count+"/"+detail.getPeriod()+")");
            mAdapter.setList(detail.getLpayDtoList());
            mAdapter.notifyDataSetChanged();
            setListViewHeightBasedOnChildren(listView);
        }
        mMoney.setText("Rp "+ StringFormatUtils.moneyFormat(detail.getPrincipalAmount()));
    }
}
