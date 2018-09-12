package com.yinshan.happycash.view.me.view.impl.support;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.utils.MyDebugUtils;
import com.yinshan.happycash.view.me.model.NameDescData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by huxin on 2018/3/19.
 */

public class HelpCenterAdapter extends BaseAdapter{

    int chooseIndex = -1;

    List<NameDescData> nameDescList = new ArrayList<>();
    private HashMap<Integer,View> mHashMap;

    public HelpCenterAdapter(){
        mHashMap = new HashMap<>();
    }

    public void addList(String name,String desc){
        if(nameDescList!=null){
            nameDescList.add(new NameDescData(name,desc));
        }
    }

    @Override
    public int getCount() {
        if(nameDescList!=null)
            return nameDescList.size();
        return 0;
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
        if(null==mHashMap.get(position)){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_help_center,parent,false);
            holder = new ViewHolder();
            holder.name = (TextView)view.findViewById(R.id.name);
            holder.desc = (TextView)view.findViewById(R.id.desc);
            holder.nameRow = (RelativeLayout) view.findViewById(R.id.nameRow);
            holder.descRow = (RelativeLayout) view.findViewById(R.id.descRow);
            holder.line = (View) view.findViewById(R.id.line);

            mHashMap.put(position,view);
            holder.nameRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyDebugUtils.v("the onClick is "+position);
                    if(chooseIndex==position){

                    }else{
                        chooseIndex = position;
                        notifyDataSetChanged();
                    }
                }
            });
            if(position==getCount()-1)
                holder.line.setVisibility(View.GONE);

            view.setTag(holder);
        }else{
            view = mHashMap.get(position);
            holder = (ViewHolder)view.getTag();
        }

        MyDebugUtils.v("the chooseIndex is "+chooseIndex);
        if(chooseIndex==position){
            holder.descRow.setVisibility(View.VISIBLE);
        }else{
            holder.descRow.setVisibility(View.GONE);
        }

        holder.name.setText(nameDescList.get(position).getName());
        holder.desc.setText(nameDescList.get(position).getDesc());

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
