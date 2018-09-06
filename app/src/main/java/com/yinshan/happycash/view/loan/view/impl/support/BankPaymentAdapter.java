package com.yinshan.happycash.view.loan.view.impl.support;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.config.inner.AppDataConfig;
import com.yinshan.happycash.utils.PaymentMethodManager;
import com.yinshan.happycash.utils.StringUtils;
import com.yinshan.happycash.view.loan.view.impl.RepaymentFragment;
import com.yinshan.happycash.widget.custom.CustomTextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by huxin on 2018/8/21.
 */
public class BankPaymentAdapter extends BaseAdapter{

    List<String> mList;
    HashMap<Integer,String[]> insertStrMap;
    Context mContext;

    public BankPaymentAdapter(Context context){
        mContext = context;
        mList = new ArrayList<>();
        insertStrMap = new HashMap<>();
    }

    public BankPaymentAdapter(Context context,int layout){
        mContext = context;
        mList = new ArrayList<>();
        insertStrMap = new HashMap<>();
        if(layout!=0){
            mList = Arrays.asList(context.getResources().getStringArray(layout));
        }else {
            mList = new ArrayList<>();
        }
    }

    public void setNewArray(int stepArray, int insertStrIndexArray, int insertStrArray){
        mList.clear();
        mList.addAll(Arrays.asList(mContext.getResources().getStringArray(stepArray)));
        insertStrMap.clear();
        List<String> insertStrIndexList = Arrays.asList(mContext.getResources().getStringArray(insertStrIndexArray));
        List<String> insertStrList = Arrays.asList(mContext.getResources().getStringArray(insertStrArray));
        for(int i=0;i<insertStrIndexList.size();i++){
            String[] split = insertStrList.get(i).split(",");
            insertStrMap.put(Integer.valueOf(insertStrIndexList.get(i)),split);
        }
    }

//    public void setList(List<String> list){
//        mList.clear();
//        mList.addAll(list);
//    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_payment_step,viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.topLine = (View)view.findViewById(R.id.topLine);
            viewHolder.bottomLine = (View)view.findViewById(R.id.bottomLine);
            viewHolder.text = (CustomTextView)view.findViewById(R.id.text);
            viewHolder.num = (TextView)view.findViewById(R.id.num);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)view.getTag();
        }

        if(position==0){
            viewHolder.topLine.setVisibility(View.INVISIBLE);
            viewHolder.bottomLine.setVisibility(View.VISIBLE);
        }else if(position==getCount()-1){
            viewHolder.topLine.setVisibility(View.VISIBLE);
            viewHolder.bottomLine.setVisibility(View.INVISIBLE);
        }else{
            viewHolder.topLine.setVisibility(View.VISIBLE);
            viewHolder.bottomLine.setVisibility(View.VISIBLE);
        }

        viewHolder.num.setText(String.valueOf(position+1));

        String[] strings = insertStrMap.get(position);
        if(strings!=null){
            if(strings.length==1){
                if(strings[0].equals(AppDataConfig.DATA_AMOUNT)){
                    viewHolder.text.setText(StringUtils.getInstance().setBoldStringWords(mList.get(position), String.valueOf(RepaymentFragment.depositRB.getAmount())));
                }else if(strings[0].equals(AppDataConfig.DATA_VIRTUAL_ACCOUNT)){
                    viewHolder.text.setText(StringUtils.getInstance().setBoldStringWords(mList.get(position), String.valueOf(RepaymentFragment.depositRB.getPaymentCode())));
                }
            }else if(strings.length==2){
                String str1 = "";
                String str2 = "";
                if(strings[0].equals(AppDataConfig.DATA_AMOUNT)){
                    str1 = String.valueOf(RepaymentFragment.depositRB.getAmount());
                }else if(strings[0].equals(AppDataConfig.DATA_VIRTUAL_ACCOUNT)){
                    str1 = String.valueOf(RepaymentFragment.depositRB.getPaymentCode());
                }
                if(strings[1].equals(AppDataConfig.DATA_AMOUNT)){
                    str2 = String.valueOf(RepaymentFragment.depositRB.getAmount());
                }else if(strings[1].equals(AppDataConfig.DATA_VIRTUAL_ACCOUNT)){
                    str2 = String.valueOf(RepaymentFragment.depositRB.getPaymentCode());
                }
                viewHolder.text.setText(StringUtils.getInstance().setStringWords(mList.get(position), str1,str2));
            }
        }else{
            viewHolder.text.setText(mList.get(position));
        }

        return view;
    }

    class ViewHolder{
        View bottomLine;
        View topLine;
        TextView num;
        CustomTextView text;
    }
}
