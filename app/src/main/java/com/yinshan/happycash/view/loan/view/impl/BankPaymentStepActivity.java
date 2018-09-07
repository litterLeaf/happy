package com.yinshan.happycash.view.loan.view.impl;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.config.inner.AppDataConfig;
import com.yinshan.happycash.config.inner.AppDefaultConfig;
import com.yinshan.happycash.framework.BaseActivity;
import com.yinshan.happycash.utils.DensityUtil;
import com.yinshan.happycash.utils.ScreenUtils;
import com.yinshan.happycash.view.loan.view.impl.support.BankPaymentAdapter;
import com.yinshan.happycash.widget.custom.NoScrollListView;

/**
 * Created by huxin on 2018/8/31.
 */
public class BankPaymentStepActivity extends BaseActivity {

    TextView mTitle;
    RelativeLayout mBackBtn;
    TextView mClickATM;
    TextView mNormalATM;
    View mLineATM;
    TextView mClickOnline;
    TextView mNormalOnline;
    View mLineOnline;
    TextView mClickBank;
    TextView mNormalBank;
    View mLineBank;

    TextView mMoney;
    TextView mVa;

    LinearLayout mViewChoose;
    ScrollView mScrollView;
    RelativeLayout mViewATM;
    RelativeLayout mViewOnline;
    RelativeLayout mViewBank;

    NoScrollListView mListStep;

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
    int[] getInserStrIndexIndexs;
    int[] getInsertStrIndexs;

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
        setMTextListViewHeightBasedOnChildren(mListStep);
        mScrollView.smoothScrollTo(0,0);
        mScrollView.invalidate();
    }

    private void init(){
        initUI();
        if(RepaymentFragment.depositRB!=null) {
            mMoney.setText(String.valueOf(RepaymentFragment.depositRB.getAmount()));
            mVa.setText(RepaymentFragment.depositRB.getPaymentCode());
            int channelIndex = getChannelIndex(RepaymentFragment.depositRB.getDepositChannel());
            int methodIndex = getMethodIndex(RepaymentFragment.depositRB.getDepositMethod());
            getIndexs = null;
            getInserStrIndexIndexs = null;
            getInsertStrIndexs = null;
            if(channelIndex!=-1&&methodIndex!=-1){
                getIndexs = STEP_INDEX[methodIndex][channelIndex];
                getInserStrIndexIndexs = STEP_INSERT_STR_INDEX_INDEX[methodIndex][channelIndex];
                getInsertStrIndexs = STEP_INSERT_STR_INDEX[methodIndex][channelIndex];
                boolean isShow = false;
                int showCount = 0;
                if(getIndexs[REPAYMENT_ATM]== AppDefaultConfig.DEFAULT_INDEX){
                    mViewATM.setVisibility(View.GONE);
                }else{
                    showCount++;
                    mAdapter.setNewArray(getIndexs[REPAYMENT_ATM],getInserStrIndexIndexs[REPAYMENT_ATM],getInsertStrIndexs[REPAYMENT_ATM]);
                    isShow = true;
                }
                if(getIndexs[REPAYMENT_ONLINE]==AppDefaultConfig.DEFAULT_INDEX){
                    mViewOnline.setVisibility(View.GONE);
                }else{
                    showCount++;
                    if(!isShow) {
                        mAdapter.setNewArray(getIndexs[REPAYMENT_ONLINE], getInserStrIndexIndexs[REPAYMENT_ONLINE], getIndexs[REPAYMENT_ONLINE]);
                        isShow = true;
                    }
                }
                if(getIndexs[REPAYMENT_BANK]==AppDefaultConfig.DEFAULT_INDEX){
                    mViewBank.setVisibility(View.GONE);
                }else{
                    showCount++;
                    if(!isShow) {
                        mAdapter.setNewArray(getIndexs[REPAYMENT_ATM], getInserStrIndexIndexs[REPAYMENT_ONLINE], getIndexs[REPAYMENT_ONLINE]);
                        isShow = true;
                    }
                }
                if(showCount==1)
                    mViewChoose.setVisibility(View.GONE);
                mAdapter.notifyDataSetChanged();
                setMTextListViewHeightBasedOnChildren(mListStep);
            }
        }

        mListStep.setAdapter(mAdapter);
    }

    private void initUI() {
        mBackBtn = (RelativeLayout)findViewById(R.id.btnBack);
        mTitle = (TextView)findViewById(R.id.title);

        mClickATM = (TextView)findViewById(R.id.clickATM);
        mNormalATM = (TextView)findViewById(R.id.normalATM);
        mLineATM = (View) findViewById(R.id.lineATM);
        mClickOnline = (TextView)findViewById(R.id.clickOnline);
        mNormalOnline = (TextView)findViewById(R.id.normalOnline);
        mLineOnline = (View) findViewById(R.id.lineOnline);
        mClickBank = (TextView)findViewById(R.id.clickBanking);
        mNormalBank = (TextView)findViewById(R.id.normalBanking);
        mLineBank = (View) findViewById(R.id.lineManking);

        mMoney = (TextView)findViewById(R.id.money);
        mVa = (TextView)findViewById(R.id.va);
        mListStep = (NoScrollListView)findViewById(R.id.listStep);
        mAdapter = new BankPaymentAdapter(this);
        mViewChoose = (LinearLayout)findViewById(R.id.viewChoose);
        mScrollView = (ScrollView)findViewById(R.id.scrollView);
        mViewATM = (RelativeLayout)findViewById(R.id.viewATM);
        mViewOnline = (RelativeLayout)findViewById(R.id.viewOnline);
        mViewBank = (RelativeLayout)findViewById(R.id.viewBanking);

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitle.setText("Pembayaran "+RepaymentFragment.depositRB.getDepositMethod());
        mViewATM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickIndex(0);
                mAdapter.setNewArray(getIndexs[REPAYMENT_ATM], getInserStrIndexIndexs[REPAYMENT_ATM], getIndexs[REPAYMENT_ATM]);
                mAdapter.notifyDataSetChanged();
                setMTextListViewHeightBasedOnChildren(mListStep);
//                mScrollView.smoothScrollTo(0,0);
//                mScrollView.invalidate();
            }
        });
        mViewOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickIndex(1);
                mAdapter.setNewArray(getIndexs[REPAYMENT_ONLINE], getInserStrIndexIndexs[REPAYMENT_ONLINE], getIndexs[REPAYMENT_ONLINE]);
                mAdapter.notifyDataSetChanged();
                setMTextListViewHeightBasedOnChildren(mListStep);
//                mScrollView.smoothScrollTo(0,0);
//                mScrollView.invalidate();
            }
        });
        mViewBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickIndex(2);
                mAdapter.setNewArray(getIndexs[REPAYMENT_BANK], getInserStrIndexIndexs[REPAYMENT_BANK], getIndexs[REPAYMENT_BANK]);
                mAdapter.notifyDataSetChanged();
                setMTextListViewHeightBasedOnChildren(mListStep);
//                mScrollView.smoothScrollTo(0,0);
//                mScrollView.invalidate();
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

    private void clickIndex(int index){
        mClickATM.setVisibility(index==0?View.VISIBLE:View.GONE);
        mNormalATM.setVisibility(index!=0?View.VISIBLE:View.GONE);
        mLineATM.setVisibility(index==0?View.VISIBLE:View.GONE);
        mClickOnline.setVisibility(index==1?View.VISIBLE:View.GONE);
        mNormalOnline.setVisibility(index!=1?View.VISIBLE:View.GONE);
        mLineOnline.setVisibility(index==1?View.VISIBLE:View.GONE);
        mClickBank.setVisibility(index==2?View.VISIBLE:View.GONE);
        mNormalBank.setVisibility(index!=2?View.VISIBLE:View.GONE);
        mLineBank.setVisibility(index==2?View.VISIBLE:View.GONE);
    }


    public int setMTextListViewHeightBasedOnChildren(ListView listView) {

// 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return 0;
        }
        int totalHeight = 0;
        int listViewWidth = ScreenUtils.getScreenWidth(this)- DensityUtil.dip2px(this, 50);
        int widthSpec = View.MeasureSpec.makeMeasureSpec(listViewWidth, View.MeasureSpec.AT_MOST);
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);

            // 计算子项View 的宽高
            listItem.measure(widthSpec, 0);
            // 统计所有子项的总高度
            int itemHeight = listItem.getMeasuredHeight();
            totalHeight += itemHeight;

            System.out.println("listView.totalHeight: " + totalHeight + " itemHeight: " + itemHeight);
        }
        int historyHeight = totalHeight + (listView.getDividerHeight() * listAdapter.getCount() - 1)+DensityUtil.dip2px(this,1000);

// listView.getDividerHeight()获取子项间分隔符占用的高度
        System.out.println("listViewHeight = " + historyHeight);
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = historyHeight;
        listView.setLayoutParams(params);
        return historyHeight;
    }
}
