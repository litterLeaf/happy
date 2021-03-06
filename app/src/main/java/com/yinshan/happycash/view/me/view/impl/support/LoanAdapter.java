package com.yinshan.happycash.view.me.view.impl.support;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.utils.ServiceLoanStatus;
import com.yinshan.happycash.utils.StringFormatUtils;
import com.yinshan.happycash.utils.TimeManager;
import com.yinshan.happycash.view.me.model.LoanItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huxin on 2018/3/16.
 */

public class LoanAdapter extends BaseAdapter{

    Context mContext;
    List<LoanItem> mList;

    public LoanAdapter(Context context){
        mContext = context;
        mList = new ArrayList<>();
    }

    public void addList(List<LoanItem> list){
        mList.clear();
        mList.addAll(list);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if(null==view){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_loan,parent,false);
            holder = new ViewHolder();
            holder.bgView = (RelativeLayout)view.findViewById(R.id.bgView);
            holder.time = (TextView)view.findViewById(R.id.time);
            holder.id = (TextView)view.findViewById(R.id.id);
            holder.line = (View)view.findViewById(R.id.line);
            holder.title1 = (TextView)view.findViewById(R.id.title1);
            holder.title2 = (TextView)view.findViewById(R.id.title2);
            holder.title3 = (TextView)view.findViewById(R.id.title3);
            holder.money = (TextView)view.findViewById(R.id.money);
            holder.period = (TextView)view.findViewById(R.id.period);
            holder.status = (TextView)view.findViewById(R.id.status);
            view.setTag(holder);
        }else{
            holder = (ViewHolder)view.getTag();
        }
        LoanItem loan = mList.get(position);

        holder.time.setText(TimeManager.convertTimeDay(loan.getCreateTime()));
        holder.id.setText(String.valueOf(loan.getLoanAppId()));
        holder.money.setText("Rp" + StringFormatUtils.moneyFormat(loan.getPrincipalAmount()));
        holder.period.setText(StringFormatUtils.fullTime(mContext,loan.getPeriod(),loan.getPeriodUnit()));
        final String status = loan.getStatus();
//        if (TextUtils.equals(FieldParams.LoanStatus.REJECTED,status)||TextUtils.equals(FieldParams.LoanStatus.WITHDRAWN,status)||TextUtils.equals(FieldParams.LoanStatus.SUBMITTED,status)||
//                TextUtils.equals(FieldParams.LoanStatus.PAID_OFF,status) || TextUtils.equals(FieldParams.LoanStatus.CLOSED,status)) {
//            holder.status.setTextColor(mContext.getResources().getColor(R.color.color_tab_text));
//        }else{
//            holder.status.setTextColor(mContext.getResources().getColor(R.color.color_red));
//        }
        if(position==0){
//        if(status.equals("CURRENT")){
            holder.bgView.setBackgroundResource(R.drawable.bg_oval_yellow);
            holder.time.setTextColor(mContext.getResources().getColor(R.color.app_blue));
            holder.id.setTextColor(mContext.getResources().getColor(R.color.app_blue));
            int whiteColor = mContext.getResources().getColor(R.color.white);
            holder.line.setBackgroundColor(whiteColor);
            holder.title1.setTextColor(mContext.getResources().getColor(R.color.f6f6f6));
            holder.title2.setTextColor(mContext.getResources().getColor(R.color.f6f6f6));
            holder.title3.setTextColor(mContext.getResources().getColor(R.color.f6f6f6));
            holder.money.setTextColor(mContext.getResources().getColor(R.color.white));
            holder.period.setTextColor(mContext.getResources().getColor(R.color.white));
            holder.status.setTextColor(mContext.getResources().getColor(R.color.white));
        }else{
            holder.bgView.setBackgroundResource(R.drawable.bg_round_white);
            holder.time.setTextColor(mContext.getResources().getColor(R.color.gray_9b));
            holder.id.setTextColor(mContext.getResources().getColor(R.color.gray_9b));
            holder.line.setBackgroundColor(mContext.getResources().getColor(R.color.hc_line_bg));
            holder.title1.setTextColor(mContext.getResources().getColor(R.color.line));
            holder.title2.setTextColor(mContext.getResources().getColor(R.color.line));
            holder.title3.setTextColor(mContext.getResources().getColor(R.color.line));
            holder.money.setTextColor(mContext.getResources().getColor(R.color.gray_9b));
            holder.period.setTextColor(mContext.getResources().getColor(R.color.gray_9b));
            holder.status.setTextColor(mContext.getResources().getColor(R.color.gray_9b));
        }
        if (TextUtils.equals(ServiceLoanStatus.WITHDRAWN,status)) {
            holder.status.setAllCaps(true);
            holder.status.setText(R.string.text_cancel);
        }else {
            if(!TextUtils.isEmpty(loan.getPaidOffMode())&&"ROLLOVER".equals(loan.getPaidOffMode())){
                holder.status.setText("RENEWED");
            }else {
                holder.status.setText(status);
            }
        }

        return view;
    }

    class ViewHolder{
        RelativeLayout bgView;
        TextView time;
        TextView id;
        View line;
        TextView title1;
        TextView title2;
        TextView title3;
        TextView money;
        TextView period;
        TextView status;
    }
}
