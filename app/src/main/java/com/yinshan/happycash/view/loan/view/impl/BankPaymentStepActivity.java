package com.yinshan.happycash.view.loan.view.impl;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
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

    LinearLayout mViewChoose;
    ScrollView mScrollView;
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

    @Override
    protected void onResume() {
        super.onResume();
        setListViewHeightBasedOnChildren(mListStep);
        mScrollView.invalidate();
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
                boolean isShow = false;
                int showCount = 0;
                if(getIndexs[REPAYMENT_ATM]== AppDefaultConfig.DEFAULT_INDEX){
                    mViewATM.setVisibility(View.GONE);
                }else{
                    showCount++;
                    mAdapter.setNewArray(getIndexs[REPAYMENT_ATM]);
                    isShow = true;
                }
                if(getIndexs[REPAYMENT_ONLINE]==AppDefaultConfig.DEFAULT_INDEX){
                    mViewOnline.setVisibility(View.GONE);
                }else{
                    showCount++;
                    if(!isShow) {
                        mAdapter.setNewArray(getIndexs[REPAYMENT_ONLINE]);
                        isShow = true;
                    }
                }
                if(getIndexs[REPAYMENT_BANK]==AppDefaultConfig.DEFAULT_INDEX){
                    mViewBank.setVisibility(View.GONE);
                }else{
                    showCount++;
                    if(!isShow) {
                        mAdapter.setNewArray(getIndexs[REPAYMENT_BANK]);
                        isShow = true;
                    }
                }
                if(showCount==1)
                    mViewChoose.setVisibility(View.GONE);
                mAdapter.notifyDataSetChanged();
                setListViewHeightBasedOnChildren(mListStep);
            }
        }


        mListStep.setAdapter(mAdapter);
    }

    private void initUI() {
        mMoney = (TextView)findViewById(R.id.money);
        mVa = (TextView)findViewById(R.id.va);
        mListStep = (ListView)findViewById(R.id.listStep);
        mAdapter = new BankPaymentAdapter(this);
        mViewChoose = (LinearLayout)findViewById(R.id.viewChoose);
        mScrollView = (ScrollView)findViewById(R.id.scrollView);
        mViewATM = (RelativeLayout)findViewById(R.id.viewATM);
        mViewOnline = (RelativeLayout)findViewById(R.id.viewOnline);
        mViewBank = (RelativeLayout)findViewById(R.id.viewBanking);

        mViewATM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.setNewArray(getIndexs[REPAYMENT_ATM]);
                mAdapter.notifyDataSetChanged();
                setListViewHeightBasedOnChildren(mListStep);
                mScrollView.invalidate();

            }
        });
        mViewOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.setNewArray(getIndexs[REPAYMENT_ONLINE]);
                mAdapter.notifyDataSetChanged();
                setListViewHeightBasedOnChildren(mListStep);
                mScrollView.invalidate();
            }
        });
        mViewBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.setNewArray(getIndexs[REPAYMENT_BANK]);
                mAdapter.notifyDataSetChanged();
                setListViewHeightBasedOnChildren(mListStep);
                mScrollView.invalidate();
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
