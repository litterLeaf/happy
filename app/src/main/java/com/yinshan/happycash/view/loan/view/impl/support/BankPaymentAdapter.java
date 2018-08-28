package com.yinshan.happycash.view.loan.view.impl.support;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yinshan.happycash.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by huxin on 2018/8/21.
 */
public class BankPaymentAdapter extends BaseAdapter{

    List<String> mList;
    Context mContext;

    public BankPaymentAdapter(Context context,int layout){
        mContext = context;
        mList = new ArrayList<>();
        if(layout!=0){
            mList = Arrays.asList(context.getResources().getStringArray(layout));
        }else {
            mList = new ArrayList<>();
        }
    }

    public void setNewArray(int layout){
        mList.clear();
        mList.addAll(Arrays.asList(mContext.getResources().getStringArray(layout)));
    }

//    public void setList(List<String> list){
//        mList.clear();
//        mList.addAll(list);
//    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_payment_step,viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.topLine = (View)view.findViewById(R.id.topLine);
            viewHolder.bottomLine = (View)view.findViewById(R.id.bottomLine);
            viewHolder.text = (TextView)view.findViewById(R.id.text);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)view.getTag();
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
        viewHolder.text.setText(mList.get(position));

        return view;
    }

    class ViewHolder{
        View bottomLine;
        View topLine;
        TextView text;
    }
}
