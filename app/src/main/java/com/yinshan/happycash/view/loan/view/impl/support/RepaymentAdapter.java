package com.yinshan.happycash.view.loan.view.impl.support;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yinshan.happycash.R;

/**
 * Created by huxin on 2018/4/4.
 */

public class RepaymentAdapter extends BaseAdapter{

    Context mContext;

    public RepaymentAdapter(Context context){
        mContext = context;
    }

    @Override
    public int getCount() {
        return 3;
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
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        return convertView;
    }

    class ViewHolder{
        TextView time;
        TextView number;
        TextView status;
    }
}
