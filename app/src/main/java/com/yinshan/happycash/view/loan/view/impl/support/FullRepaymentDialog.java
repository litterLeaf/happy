package com.yinshan.happycash.view.loan.view.impl.support;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.utils.StringFormatUtils;
import com.yinshan.happycash.view.me.model.LoanDetailBean;
import com.yinshan.happycash.view.me.model.StageBean;
import com.yinshan.happycash.view.me.model.StageStatus;
import com.yinshan.happycash.widget.common.CommonClickListener;

import java.util.List;

/**
 * Created by huxin on 2018/9/4.
 */
public class FullRepaymentDialog extends Dialog{

    CommonClickListener mListener;
    LoanDetailBean mBean;

    public FullRepaymentDialog(@NonNull Context context, CommonClickListener listener, LoanDetailBean bean) {
        super(context, R.style.DialogTheme);
        mListener = listener;
        mBean = bean;
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_full_repayment, null, false);
        setContentView(view);
        RelativeLayout btn = (RelativeLayout) view.findViewById(R.id.submit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                mListener.onClick();
            }
        });

        double sumRemainMoney = 0;
        double sumInter = 0;
        double sumPenaltyInter = 0;

        List<StageBean> lpayDtoList = mBean.getLpayDtoList();
        for(int i=0;i< lpayDtoList.size();i++){
            StageBean stageBean = lpayDtoList.get(i);
            if(stageBean!=null){
                if(stageBean.getStatus()!=null&&stageBean.getStatus().equals(StageStatus.ACTIVE)){
                    sumRemainMoney = sumRemainMoney + stageBean.getPrincipalAccr()-stageBean.getPrincipalPaid();
                    sumInter = sumInter + stageBean.getInterestAccr()-stageBean.getInterestPaid();
                    sumPenaltyInter = sumPenaltyInter + stageBean.getDefaultAccr() - stageBean.getDefaultPaid();
                }
            }
        }

        TextView remainMoney = (TextView)view.findViewById(R.id.remainMoney);
        remainMoney.setText(StringFormatUtils.moneyFormat(Math.ceil(sumRemainMoney)));
        TextView interest = (TextView)view.findViewById(R.id.interest);
        interest.setText(StringFormatUtils.moneyFormat(Math.ceil(sumInter)));
        TextView penaltyInterest = (TextView)view.findViewById(R.id.penaltyInterest);
        penaltyInterest.setText(StringFormatUtils.moneyFormat(Math.ceil(sumPenaltyInter)));
        TextView total = (TextView)view.findViewById(R.id.total);
        total.setText(StringFormatUtils.moneyFormat(Math.ceil(sumRemainMoney+sumInter+sumPenaltyInter)));
    }
}
