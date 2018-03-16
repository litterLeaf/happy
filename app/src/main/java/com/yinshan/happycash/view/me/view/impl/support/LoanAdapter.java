package com.yinshan.happycash.view.me.view.impl.support;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yinshan.happycash.R;

/**
 * Created by huxin on 2018/3/16.
 */

public class LoanAdapter extends BaseAdapter{

    Context mContext;

    public LoanAdapter(Context context){
        mContext = context;
    }

    @Override
    public int getCount() {
        return 10;
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
    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if(null==view){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_loan,parent,false);
            holder = new ViewHolder();
            holder.time = (TextView)view.findViewById(R.id.time);
            view.setTag(holder);
        }else{
            holder = (ViewHolder)view.getTag();
        }
        return view;
    }

    class ViewHolder{
        TextView time;
    }
}
