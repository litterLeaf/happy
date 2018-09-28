package com.yinshan.happycash.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.utils.StringFormatUtils;
import com.yinshan.happycash.view.me.model.PreBean;
import com.yinshan.happycash.view.me.model.PrePaymentBean;
import com.yinshan.happycash.widget.common.CommonClickListener;

import java.util.List;

/**
 * Created by huxin on 2018/9/26.
 */
public class InAdavanceRepayDialog extends Dialog{

    Context mContext;
    CommonClickListener mListener;
    PrePaymentBean mBean;

    TextView mAmountMoney;
    TextView mRepayPaid;
    TextView mInterestPaid;
    TextView mFinesPaid;
    TextView mEarlyRepayServiceFees;
    TextView mCutDownFees;

    RelativeLayout mCancel;
    RelativeLayout mConfirm;

    public InAdavanceRepayDialog(@NonNull Context context, CommonClickListener listener, PrePaymentBean bean) {
        super(context, R.style.DialogTheme);
        mContext = context;
        mListener = listener;
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_in_advance_repay,null,false);
        setContentView(view);

        mAmountMoney = (TextView)view.findViewById(R.id.amountMoney);
        mRepayPaid = (TextView)view.findViewById(R.id.basicPaid);
        mInterestPaid = (TextView)view.findViewById(R.id.interestPaid);
        mFinesPaid = (TextView)view.findViewById(R.id.finesPaid);
        mEarlyRepayServiceFees = (TextView)view.findViewById(R.id.earlyRepayServiceFees);
        mCutDownFees = (TextView)view.findViewById(R.id.cutDownFees);

        mCancel = (RelativeLayout) view.findViewById(R.id.cancel);
        mConfirm = (RelativeLayout) view.findViewById(R.id.confirm);

        String cMSign = mContext.getResources().getString(R.string.cm_sign);
        if(mBean!=null){
            mAmountMoney.setText(cMSign+ StringFormatUtils.moneyFormat(mBean.getPrincipalAmount()));
            double sumRepay = 0;
            double sumInterest = 0;
            double sumFines = 0;
            double sumEarlyRepay = 0;
            List<PreBean> lpayDtoList = mBean.getLpayDtoList();
            if(lpayDtoList!=null){
                for(PreBean preBean:lpayDtoList){
                    sumRepay += preBean.getPrincipalAccr() - preBean.getPrincipalPaid();
                    sumInterest += preBean.getInterestAccr() - preBean.getInterestPaid();
                    sumFines += preBean.getDefaultAccr() - preBean.getDefaultPaid();
                    sumEarlyRepay += preBean.getAdvanceFeeAccr() - preBean.getAdvanceFeePaid();
                }
                mAmountMoney.setText(cMSign+ StringFormatUtils.moneyFormat(mBean.getPrincipalAmount()));

                mRepayPaid.setText(cMSign+ StringFormatUtils.moneyFormat(sumRepay));
                mRepayPaid.setText(cMSign+ StringFormatUtils.moneyFormat(sumInterest));
                mRepayPaid.setText(cMSign+ StringFormatUtils.moneyFormat(sumFines));
                mRepayPaid.setText(cMSign+ StringFormatUtils.moneyFormat(sumEarlyRepay));

                mCutDownFees.setText(cMSign+ StringFormatUtils.moneyFormat(mBean.getPrepaymentFeeAmount()-mBean.getPrincipalAmount()));
            }
        }

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if(mListener!=null){
                    mListener.onClick();
                }
            }
        });
    }
}
