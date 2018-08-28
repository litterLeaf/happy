package com.yinshan.happycash.view.bindcard.view.impl;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.yinshan.happycash.R;
import com.yinshan.happycash.analytic.event.MobAgent;
import com.yinshan.happycash.analytic.event.MobEvent;
import com.yinshan.happycash.framework.BaseActivity;
import com.yinshan.happycash.utils.SPKeyUtils;
import com.yinshan.happycash.view.bindcard.view.impl.support.BankCardNameAdapter;
import com.yinshan.happycash.view.information.view.impl.support.InfoType;
import com.yinshan.happycash.view.main.view.impl.MainActivity;
import com.yinshan.happycash.widget.HappySnackBar;
import com.yinshan.happycash.widget.dialog.DialogManager;
import com.yinshan.happycash.widget.userdefined.BandaEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by admin on 2018/3/14.
 */

public class BindCardActivity  extends BaseActivity {

    @BindView(R.id.bind_bank_name)
    TextView bindBankName;
    @BindView(R.id.bind_user_name)
    BandaEditText bindUserName;
    @BindView(R.id.bind_bank_number)
    BandaEditText bindBankNumber;
    @BindView(R.id.bind_add_bank_name_rl)
    RelativeLayout addBankCardName;

    private Dialog dialogPlus;

    public static final String BIND_BANKNAME = "bindBankName";
    public static final String BIND_USERNAME = "bindUserName";
    public static final String BIND_BANKNUMBER = "bindBankNumber";


    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        RxBus.get().register(this);
        MobAgent.onEvent(MobEvent.IN_BIND_BANK_CARD_ACTIVITY);
        addBankCardName.setFocusable(true);
        addBankCardName.requestFocus();
        bindUserName.clearFocus();
        inputTxtTime();
        checkPaste();
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_bind_bank_card;
    }

    @Override
    protected void secondLayout() {

    }

    @Override
    protected void secondInit() {

    }

    /**
     * 收集用户输入的时间
     */
    private void inputTxtTime() {
        onFocusChange(bindUserName,MobEvent.INPUT_YOUR_BANK_ACCOUNT_NAME);
        onFocusChange(bindBankNumber,MobEvent.INPUT_BANK_CARD_NUMBER);
    }

    /**
     * 监听用户剪贴板
     */
    private void checkPaste() {
        onPasteListener( bindUserName,"YOUR_BANK_ACCOUNT_NAME");
        onPasteListener( bindBankNumber,"BANK_CARD_NUMBER");
    }

    @OnClick({R.id.id_imagebutton_close,R.id.bind_add_bank_name_rl,R.id.bind_bank_confirm})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.id_imagebutton_close:
                MobAgent.onEvent(MobEvent.CLICK+MobEvent.BIND_BANK_CARD_CLICK_BACK);
                finish();
                break;
            case R.id.bind_add_bank_name_rl:
                MobAgent.onEvent(MobEvent.CLICK+ MobEvent.SELECT_BANK);
                dialogPlus = DialogManager.newListViewDialog(this)
                        .setAdapter(getbankCardNameAdapter())
                        .setExpanded(false)
                        .setGravity(Gravity.CENTER)
                        .create();
                dialogPlus.show();
                break;
            case R.id.bind_bank_confirm:
                MobAgent.onEvent(MobEvent.CLICK+MobEvent.BIND_BANK_CARD_CONFIRM);
                checkUserInfor();
                break;
        }
    }

    private BankCardNameAdapter getbankCardNameAdapter() {

        ArrayList<String> bankName = new ArrayList(Arrays.asList(getResources().getStringArray(R.array.bankCardName1)));
        ArrayList<String> bankname2 = new ArrayList(Arrays.asList(getResources().getStringArray(R.array.bankCardName2)));

        Collections.sort(bankname2);
        bankName.addAll(bankname2);

        BankCardNameAdapter loanBankInfoAdapter = new BankCardNameAdapter(this);
        for (String info : bankName
                ) {
            loanBankInfoAdapter.addItem(info, InfoType.RECEIVINGBANK);
        }
        return loanBankInfoAdapter;
    }

    private void checkUserInfor(){
        if(TextUtils.isEmpty(bindBankName.getText().toString().trim())){
            HappySnackBar.showSnackBar(bindBankName,R.string.loaning_fragment_bank_name, SPKeyUtils.SNACKBAR_TYPE_WORN);
            return;
        }
        if(TextUtils.isEmpty(bindUserName.getText().toString().trim())){
            HappySnackBar.showSnackBar(bindBankName,R.string.loaning_fragment_bank_nuber, SPKeyUtils.SNACKBAR_TYPE_WORN);
            return;
        }
        if(TextUtils.isEmpty(bindBankNumber.getText().toString().trim())){
            HappySnackBar.showSnackBar(bindBankName,R.string.loaning_fragment_name, SPKeyUtils.SNACKBAR_TYPE_WORN);
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(BIND_BANKNAME,bindBankName.getText().toString().trim());
        intent.putExtra(BIND_USERNAME,bindUserName.getText().toString().trim());
        intent.putExtra(BIND_BANKNUMBER,bindBankNumber.getText().toString().trim());
        BindCardActivity.this.setResult(Activity.RESULT_OK,intent);
        MainActivity.isNotResume = false;
        finish();
    }

    @Subscribe
    public void onReceivingBank(BankCardNameAdapter.BankCardItemSelectedEvent<BankCardNameAdapter.BankCardInfoItem> event) {
        if (event.data.getType() == InfoType.RECEIVINGBANK) {
            if(dialogPlus != null && dialogPlus.isShowing()){
                dialogPlus.dismiss();
            }
            bindBankName.setText(event.data.getInfoStr());
            MobAgent.onEvent("GET_BANK_NAME_"+MobEvent.LIST+event.data.getInfoStr());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
        MobAgent.onEvent(MobEvent.BIND_BANK_CARD_BACK);
    }
}
