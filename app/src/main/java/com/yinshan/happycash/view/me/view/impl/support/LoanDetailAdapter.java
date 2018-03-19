package com.yinshan.happycash.view.me.view.impl.support;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yinshan.happycash.R;

/**
 * Created by huxin on 2018/3/16.
 */

public class LoanDetailAdapter extends BaseAdapter{

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
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(null == convertView){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_loan_detail,parent,false);
            holder = new ViewHolder();
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        return convertView;
    }

    class ViewHolder{
        TextView name;
        TextView desc;
    }
}
