package com.yinshan.happycash.view.loan.view.impl;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.riven.library.TextViewExpand;
import com.yinshan.happycash.R;
import com.yinshan.happycash.analytic.event.MobAgent;
import com.yinshan.happycash.analytic.event.MobEvent;
import com.yinshan.happycash.application.HappyAppSP;
import com.yinshan.happycash.framework.BaseFragment;
import com.yinshan.happycash.utils.SPKeyUtils;
import com.yinshan.happycash.utils.StringFormatUtils;
import com.yinshan.happycash.view.bindcard.view.impl.BindCardActivity;
import com.yinshan.happycash.view.bindcard.model.BandCardBean;
import com.yinshan.happycash.view.information.view.impl.support.InfoAdapterEnum;
import com.yinshan.happycash.view.information.view.impl.support.InfoType;
import com.yinshan.happycash.view.loan.model.ApplyPurpose;
import com.yinshan.happycash.view.loan.presenter.LoaningPresenter;
import com.yinshan.happycash.view.loan.view.ILoaningView;
import com.yinshan.happycash.widget.HappySnackBar;
import com.yinshan.happycash.widget.dialog.DialogManager;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by admin on 2018/2/1.
 */

public class LoaningFragment extends BaseFragment implements ILoaningView{

    @BindView(R.id.id_textview_repayment_amount)
    TextView idTextviewRepaymentAmount;
    private final long lMaxAmount = 50000000;
    private final long lMinAmount = 10000000;
    private final int subLength = 10;
    long moneyAmount;

    @BindView(R.id.bt_period_1_unloan)
    Button choose1Period;
    @BindView(R.id.bt_period_3_unloan)
    Button choose3Period;

    @BindView(R.id.loan_before_bind_card)
    TextViewExpand beforeBindCard;
    @BindView(R.id.loan_bind_card)
    TextViewExpand bindCard;
    @BindView(R.id.loan_reason)
    TextViewExpand borrowReason;

    LoaningPresenter mPresenter;
    Dialog dialogPlus;

    private int mRequestCode = 1333;

    int choosePeriod;
    private String smsCode;

    private String borrowReasonString;
    private String bankCardName;
    private String bankCardNumber;
    private String userName;

    @Override
    protected void initView() {
        RxBus.get().register(this);
        MobAgent.onEvent(MobEvent.IN_LOANING_FRAGMENT);

        idTextviewRepaymentAmount.setText(StringFormatUtils.moneyFormat(lMaxAmount));
        moneyAmount = lMaxAmount;
        choosePeriod = 1;

        mPresenter = new LoaningPresenter(getActivity(),this);
        mPresenter.getBankCard();
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_loaning;
    }

    @Override
    public void showBindBankCard(BandCardBean bean) {
        if(bean!=null){
            beforeBindCard.setVisibility(View.GONE);
            bindCard.setVisibility(View.VISIBLE);
            bindCard.setCenterString(bean.getCardNo());
            bindCard.setLeftString(bean.getBankCode());
        }
    }

    @Override
    public void showPurpose(List<ApplyPurpose> list) {
        final InfoAdapterEnum repaymentMethodAdapter = new InfoAdapterEnum(getActivity());
        for (int i= 0;i<list.size();i++) {
            repaymentMethodAdapter.addItem(list.get(i), InfoType.BORROW_REASON);
        }
        dialogPlus = DialogManager.newListViewDialog(getActivity())
//                .setContentHolder(new ListHolder())
                .setGravity(Gravity.CENTER)
                .setExpanded(false)
                .setAdapter(repaymentMethodAdapter)
                .create();
        dialogPlus.show();
    }

    @Override
    public void submitLoanOk() {

    }

    @OnClick({R.id.loan_before_bind_card,R.id.loan_reason,R.id.btnSubmit,R.id.bt_period_1_unloan,R.id.bt_period_3_unloan})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.loan_before_bind_card:
                MobAgent.onEvent(MobEvent.CLICK+MobEvent.BIND_BANK_CARD);
                Intent intent = new Intent(getActivity(), BindCardActivity.class);
                startActivityForResult(intent,mRequestCode);
                break;
            case R.id.loan_reason:
                MobAgent.onEvent(MobEvent.CLICK+MobEvent.BORROW_REANSON);
                mPresenter.getPurpose();
                break;
            case R.id.btnSubmit:
                MobAgent.onEvent(MobEvent.CLICK+MobEvent.APPLY_LOANING);
                applyLoanAppSubmit();
                break;
            case R.id.bt_period_1_unloan:
                choosePeriod = 1;
                choose1Period.setBackgroundResource(R.drawable.shape_unloan_bg);
                choose3Period.setBackgroundResource(R.drawable.shape_period_bg);
                break;
            case R.id.bt_period_3_unloan:
                choosePeriod = 3;
                choose1Period.setBackgroundResource(R.drawable.shape_period_bg);
                choose3Period.setBackgroundResource(R.drawable.shape_unloan_bg);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK&&requestCode==mRequestCode){
            if(data==null)
                return;
            bankCardName = data.getStringExtra("bindBankName");
            bankCardNumber=data.getStringExtra("bindBankNumber");
            userName=data.getStringExtra("bindUserName");
            beforeBindCard.setVisibility(View.GONE);
            bindCard.setVisibility(View.VISIBLE);
            bindCard.setCenterString(bankCardNumber);
            bindCard.setLeftString(bankCardName);
        }
    }

    /**
     * 设置借款原因
     * @param event
     */
    @Subscribe
    public void sendRepayRequest(InfoAdapterEnum.ItemSelectedEvent<InfoAdapterEnum.InfoItem> event) {
        if (event.data.getType() == InfoType.BORROW_REASON) {
            if (dialogPlus != null && dialogPlus.isShowing()) {
                dialogPlus.dismiss();
            }
            borrowReason.setCenterString(event.data.getInfoStr());
            borrowReasonString = event.data.getValueStr();
            borrowReason.setBackground(getResources().getDrawable(R.drawable.shape_amount_selected));
            MobAgent.onEvent("LOANING_REASON_"+MobEvent.LIST+borrowReasonString);
        }
    }

    public void applyLoanAppSubmit(){
        if(TextUtils.isEmpty(bankCardName)){
            HappySnackBar.showSnackBar(bindCard,R.string.loaning_fragment_bank_name, SPKeyUtils.SNACKBAR_TYPE_WORN);
            return;
        }
        if(TextUtils.isEmpty(bankCardNumber)){
            HappySnackBar.showSnackBar(bindCard,R.string.loaning_fragment_bank_nuber, SPKeyUtils.SNACKBAR_TYPE_WORN);
            return;
        }
        if(TextUtils.isEmpty(userName)){
            HappySnackBar.showSnackBar(bindCard,R.string.loaning_fragment_name,SPKeyUtils.SNACKBAR_TYPE_WORN);
            return;
        }
        if(TextUtils.isEmpty(borrowReasonString)){
            HappySnackBar.showSnackBar(bindCard,R.string.loaning_fragment_reason,SPKeyUtils.SNACKBAR_TYPE_WORN);
            return;
        }

        String loanType = "PAYDAY";
        long period = choosePeriod;
        String periodUnit = "M";
        String bankCode = bankCardName;
        String cardNo = bankCardNumber;
        String holderName = userName;
        String applyPurpose = borrowReasonString;
        String applyFor = "";
        String applyChannel = "NONE";
        String applyPlatform = "ANDROID";
        String imie = HappyAppSP.getInstance().getImei();
        smsCode ="SMSCode";

        moneyAmount = 800000;

        mPresenter.submitLoanApp(loanType,String.valueOf(moneyAmount),String.valueOf(period),periodUnit,bankCode,cardNo,holderName,applyPurpose,applyFor,
                applyChannel,applyPlatform,imie,smsCode);
    }
}