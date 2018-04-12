package com.yinshan.happycash.widget.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yinshan.happycash.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huxin on 2018/4/12.
 */

public class ListDialogAdapter extends BaseAdapter{

    Context mContext;
    List<String> mList;

    public ListDialogAdapter(Context context,List<String> strs){
        mContext = context;
        mList = new ArrayList<>();
        mList.addAll(strs);
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
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_list_dialog,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.text = (TextView)convertView.findViewById(R.id.text);
            viewHolder.line = (View)convertView.findViewById(R.id.line);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.text.setText(mList.get(position));
        if(position==getCount()-1){
            viewHolder.line.setVisibility(View.GONE);
        }

        return convertView;
    }

    class ViewHolder{
        TextView text;
        View line;
    }
}
