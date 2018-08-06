package com.yinshan.happycash.view.me.view.impl.support;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.view.me.model.NameDescData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huxin on 2018/3/16.
 */

public class LoanDetailAdapter extends BaseAdapter{

    List<NameDescData> mList;

    public LoanDetailAdapter(){
        mList = new ArrayList<>();
    }

    public void addList(List<NameDescData> list){
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
        final ViewHolder holder;
        if(null == convertView){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_loan_detail,parent,false);
            holder = new ViewHolder();
            holder.name = (TextView)convertView.findViewById(R.id.name);
            holder.desc = (TextView)convertView.findViewById(R.id.desc);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        holder.name.setText(mList.get(position).getName());
        holder.desc.setText(mList.get(position).getDesc());

        return convertView;
    }

    class ViewHolder{
        TextView name;
        TextView desc;
    }
}
