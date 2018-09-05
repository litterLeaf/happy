package com.yinshan.happycash.view.loan.view.impl;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.config.inner.AppDataConfig;
import com.yinshan.happycash.config.inner.AppDefaultConfig;
import com.yinshan.happycash.config.inner.AppInnerConfig;
import com.yinshan.happycash.framework.BaseActivity;
import com.yinshan.happycash.utils.PaymentMethodManager;
import com.yinshan.happycash.view.loan.view.impl.support.BankPaymentAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by huxin on 2018/8/31.
 */
public class BankPaymentStepActivity extends BaseActivity {

    TextView mMoney;
    TextView mVa;

    RelativeLayout mViewATM;
    RelativeLayout mViewOnline;
    RelativeLayout mViewBank;

    ListView mListStep;

    BankPaymentAdapter mAdapter;


    final int METHOD_ALFMART_INDEX = 0;
    final int METHOD_MANDIRI_INDEX = 1;
    final int METHOD_BNI_INDEX = 2;
    final int METHOD_BRI_INDEX = 3;
    final int METHOD_OTHERS_INDEX = 4;

    final int CHANNLE_XENDIT_INDEX = 0;

    final int REPAYMENT_ATM = 0;
    final int REPAYMENT_ONLINE = 1;
    final int REPAYMENT_BANK = 2;

    int[][][] STEP_INDEX =
            {
                    //alfmart
                    {
                            {R.array.alfamart_xendit,-1,-1},//XENDIT
//                        {1,2,3}//xendit
                    },
                    //mandiri
                    {
                            {R.array.mandiri_xendit_atm,R.array.mandiri_xendit_online,R.array.mandiri_xendit_bank},//XENDIT
//                        {1,2,3}//xendit
                    },
                    //bni
                    {
                            {R.array.bni_xendit_atm,R.array.bni_xendit_online,R.array.bni_xendit_bank},//XENDIT
//                        {1,2,3}//xendit
                    },
                    //bri
                    {
                            {R.array.bri_xendit_atm,R.array.bri_xendit_online,R.array.bri_xendit_bank},//XENDIT
//                        {1,2,3}//xendit
                    },
                    //others
                    {
                            {R.array.others_xendit_atm,R.array.others_xendit_online,R.array.others_xendit_bank},//XENDIT
//                        {1,2,3}//xendit
                    }
            };

    int[][][] STEP_INSERT_STR_INDEX_INDEX =
            {
                    //alfamart
                    {
                            {R.array.alfamart_xendit_insert_str_index,-1,-1},//XENDIT
//                        {1,2,3}//xendit
                    },
                    //mandiri
                    {
                            {R.array.mandiri_xendit_atm_insert_str_index,R.array.mandiri_xendit_online_insert_str_index,R.array.mandiri_xendit_bank_insert_str_index},//XENDIT
//                        {1,2,3}//xendit
                    },
                    //bni
                    {
                            {R.array.bni_xendit_atm_insert_str_index,R.array.bni_xendit_online_insert_str_index,R.array.bni_xendit_bank_insert_str_index},//XENDIT

//                        {1,2,3}//xendit
                    },
                    //bri
                    {
                            {R.array.bri_xendit_atm_insert_str_index,R.array.bri_xendit_online_insert_str_index,R.array.bri_xendit_bank_insert_str_index},//XENDIT

//                        {1,2,3}//xendit
                    },
                    //others
                    {
                            {R.array.others_xendit_atm_insert_str_index,R.array.others_xendit_online_insert_str_index,R.array.others_xendit_bank_insert_str_index},//XENDIT

//                        {1,2,3}//xendit
                    }
            };

    int[][][] STEP_INSERT_STR_INDEX =
            {
                    //alfamart
                    {
                            {R.array.alfamart_xendit_insert_str,-1,-1},//XENDIT
//                        {1,2,3}//xendit
                    },
                    //mandiri
                    {
                            {R.array.mandiri_xendit_atm_insert_str,R.array.mandiri_xendit_online_insert_str,R.array.mandiri_xendit_bank_insert_str},//XENDIT
//                        {1,2,3}//xendit
                    },
                    //bni
                    {
                            {R.array.bni_xendit_atm_insert_str,R.array.bni_xendit_online_insert_str,R.array.bni_xendit_bank_insert_str},//XENDIT

//                        {1,2,3}//xendit
                    },
                    //bri
                    {
                            {R.array.bri_xendit_atm_insert_str,R.array.bri_xendit_online_insert_str,R.array.bri_xendit_bank_insert_str},//XENDIT

//                        {1,2,3}//xendit
                    },
                    //others
                    {
                            {R.array.others_xendit_atm_insert_str,R.array.others_xendit_online_insert_str,R.array.others_xendit_bank_insert_str},//XENDIT

//                        {1,2,3}//xendit
                    }
            };

    //Map<Integer,List<String>> ALFMART_INSERT_STR = new HashMap<>()
    int[] getIndexs;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_bank_payment;
    }

    @Override
    protected void secondLayout() {

    }

    @Override
    protected void secondInit() {
        init();
    }

    private void init(){
        initUI();
        if(RepaymentFragment.depositRB!=null) {
            mMoney.setText(String.valueOf(RepaymentFragment.depositRB.getPrice()));
            mVa.setText(RepaymentFragment.depositRB.getPaymentCode());
            int channelIndex = getChannelIndex(RepaymentFragment.depositRB.getDepositChannel());
            int methodIndex = getMethodIndex(RepaymentFragment.depositRB.getDepositMethod());
            getIndexs = null;
            if(channelIndex!=-1&&methodIndex!=-1){
                getIndexs = STEP_INDEX[methodIndex][channelIndex];
//                if(getIndexs[]!= AppDefaultConfig.DEFAULT_INDEX){
//
//                }
            }
        }

        mAdapter = new BankPaymentAdapter(this, PaymentMethodManager.getPaymentStepsLayout(RepaymentFragment.depositRB));
        mListStep.setAdapter(mAdapter);
        //setListViewHeightBasedOnChildren(mListStep);
    }

    private void initUI() {
        mMoney = (TextView)findViewById(R.id.money);
        mVa = (TextView)findViewById(R.id.va);
        mListStep = (ListView)findViewById(R.id.listStep);
        mViewATM = (RelativeLayout)findViewById(R.id.viewATM);
        mViewOnline = (RelativeLayout)findViewById(R.id.viewOnline);
        mViewBank = (RelativeLayout)findViewById(R.id.viewBanking);

        mViewATM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private int getChannelIndex(String channel){
          if(channel.equals(AppDataConfig.CHANNEL_XENDIT))
              return CHANNLE_XENDIT_INDEX;
          return -1;
    }

    private int getMethodIndex(String method){
        if(method.equals(AppDataConfig.METHOD_ALFMART))
            return METHOD_ALFMART_INDEX;
        else if(method.equals(AppDataConfig.METHOD_MANDIRI))
            return METHOD_MANDIRI_INDEX;
        else if(method.equals(AppDataConfig.METHOD_BNI))
            return METHOD_BNI_INDEX;
        else if(method.equals(AppDataConfig.METHOD_BRI))
            return METHOD_BRI_INDEX;
        else if(method.equals(AppDataConfig.METHOD_OTHERS))
            return METHOD_OTHERS_INDEX;
        return -1;
    }
}
