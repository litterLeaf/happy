package com.yinshan.happycash.view.loan.view.impl.support;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseStatusLogsBean;
import com.yinshan.happycash.utils.DateUtil;
import com.yinshan.happycash.view.loan.view.impl.ApplyFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huxin on 2018/4/4.
 */

public class ApplyAdapter extends BaseAdapter{

    Context mContext;

    List<BaseStatusLogsBean> mStatusLogs = new ArrayList<>();

    public ApplyAdapter(Context context){
        mContext = context;
    }

    public void setStatusList(List<BaseStatusLogsBean> statusLogs){
        mStatusLogs.clear();
        for(int i=0;i<statusLogs.size();i++){
            mStatusLogs.add(statusLogs.get(i));
        }
    }

    @Override
    public int getCount() {
        return mStatusLogs.size();
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
            viewHolder.time = (TextView) convertView.findViewById(R.id.time);
            viewHolder.title = (TextView)convertView.findViewById(R.id.title);
            viewHolder.desc = (ApplyFitTextView)convertView.findViewById(R.id.desc);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
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

        String date = mStatusLogs.get(position).getCreateTime();
        viewHolder.time.setText(DateUtil.dealSpecialDate(date));
        viewHolder.title.setText(ApplyFragment.getORMWord(mContext,0,mStatusLogs.get(position).getStatus()));
        viewHolder.desc.setText(ApplyFragment.getORMWord(mContext,1,mStatusLogs.get(position).getStatus()));

        return convertView;
    }

    class ViewHolder{
        View bottomLine;
        View topLine;
        TextView time;
        TextView title;
        ApplyFitTextView desc;
    }
}
