package com.yinshan.happycash.view.me.view.impl.support;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yinshan.happycash.R;

/**
 * Created by huxin on 2018/3/19.
 */

public class HelpCenterAdapter extends BaseAdapter{

    int chooseIndex = -1;

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
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if(null==view){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_help_center,parent,false);
            holder = new ViewHolder();
            holder.name = (TextView)view.findViewById(R.id.name);
            holder.desc = (TextView)view.findViewById(R.id.desc);
            holder.nameRow = (RelativeLayout) view.findViewById(R.id.nameRow);
            holder.descRow = (RelativeLayout) view.findViewById(R.id.descRow);
            holder.line = (View) view.findViewById(R.id.line);

            holder.nameRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(chooseIndex==position)
                        chooseIndex = -1;
                    else
                        chooseIndex = position;
                    notifyDataSetChanged();
                }
            });
            if(position==getCount()-1)
                holder.line.setVisibility(View.GONE);

            view.setTag(holder);
        }else{
            holder = (ViewHolder)view.getTag();
        }

        if(chooseIndex==position){
            holder.descRow.setVisibility(View.VISIBLE);
        }else{
            holder.descRow.setVisibility(View.GONE);
        }

        return view;
    }

    class ViewHolder{
        TextView name;
        TextView desc;
        RelativeLayout nameRow;
        RelativeLayout descRow;
        View line;
    }
}
