package com.yinshan.happycash.view.loan.view.impl.support;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.utils.StringFormatUtils;
import com.yinshan.happycash.utils.TimeManager;
import com.yinshan.happycash.view.loan.view.impl.RepaymentFragment;
import com.yinshan.happycash.view.me.model.StageBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huxin on 2018/4/4.
 */

public class RepaymentAdapter extends BaseAdapter{

    Context mContext;
    List<StageBean> mList;

    public RepaymentAdapter(Context context){
        mContext = context;
        mList = new ArrayList<>();
    }

    public void setList(List<StageBean> list){
        mList.clear();
        mList.addAll(list);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_repayment,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.time = (TextView)convertView.findViewById(R.id.time);
            viewHolder.money = (TextView)convertView.findViewById(R.id.money);
            viewHolder.status = (TextView)convertView.findViewById(R.id.status);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.time.setText(TimeManager.convertYNTimeDay(mList.get(position).getDueDate()));
        viewHolder.money.setText(StringFormatUtils.moneyFormat(RepaymentFragment.getSum(mList.get(position))));
        String status = mList.get(position).getStatus();
        if(status.equals("INACTIVE"))
            viewHolder.status.setText(mContext.getString(R.string.return_clear));
        else if(status.equals("ACTIVE"))
            viewHolder.status.setText(mContext.getString(R.string.not_return_clear));
        else if(status.equals("OVERDUE"))
            viewHolder.status.setText(mContext.getString(R.string.more_than_expected));

        return convertView;
    }

    class ViewHolder{
        TextView time;
        TextView money;
        TextView status;
    }
}
