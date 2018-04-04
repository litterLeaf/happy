package com.yinshan.happycash.view.loan.view.impl.support;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.yinshan.happycash.R;

/**
 * Created by huxin on 2018/4/4.
 */

public class ApplyAdapter extends BaseAdapter{

    Context mContext;

    public ApplyAdapter(Context context){
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_apply,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.topLine = (View)convertView.findViewById(R.id.topLine);
            viewHolder.bottomLine = (View)convertView.findViewById(R.id.bottomLine);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

//        if(position==0){
//            viewHolder.topLine.setVisibility(View.INVISIBLE);
//            viewHolder.bottomLine.setVisibility(View.VISIBLE);
//        }else if(position==getCount()-1){
//            viewHolder.topLine.setVisibility(View.VISIBLE);
//            viewHolder.bottomLine.setVisibility(View.VISIBLE);
//        }else{
//            viewHolder.topLine.setVisibility(View.VISIBLE);
//            viewHolder.bottomLine.setVisibility(View.VISIBLE);
//        }

//        viewHolder.topLine.setVisibility(View.VISIBLE);
//        viewHolder.bottomLine.setVisibility(View.VISIBLE);

        return convertView;
    }

    class ViewHolder{
        View bottomLine;
        View topLine;
    }
}
