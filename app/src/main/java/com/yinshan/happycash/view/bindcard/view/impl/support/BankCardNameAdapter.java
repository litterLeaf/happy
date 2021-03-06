package com.yinshan.happycash.view.bindcard.view.impl.support;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hwangjr.rxbus.RxBus;
import com.yinshan.happycash.R;
import com.yinshan.happycash.view.information.view.impl.support.InfoAdapter;
import com.yinshan.happycash.view.information.view.impl.support.InfoType;

import java.util.ArrayList;

/**
 * Created by huxin on 2018/5/2.
 */

public class BankCardNameAdapter  extends BaseAdapter implements InfoAdapter {

    private ArrayList<InfoItem> mItemInfo = new ArrayList<>();
    private LayoutInflater mInflater;

    public BankCardNameAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    public void addItem(final String item, InfoType type){
        mItemInfo.add(new BankCardInfoItem(item,type));
        notifyDataSetChanged();
    }

    public void addItem(final String item, InfoType type, int id){
        mItemInfo.add(new BankCardInfoItem(item,type,id));
        notifyDataSetChanged();
    }

    public void addItem(final String item, InfoType type, int id,String level){
        mItemInfo.add(new BankCardInfoItem(item,type,id,level));
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mItemInfo.size();
    }

    @Override
    public Object getItem(int position) {
        return mItemInfo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Logger.d("getView " + position+ " "+ convertView);
        BankCardNameAdapter.ViewHolder holder = null;

        if(convertView == null){
            convertView = mInflater.inflate(R.layout.bank_list_item,null);
            holder = new BankCardNameAdapter.ViewHolder();
            holder.panelView = convertView.findViewById(R.id.id_relativelayout_bank_item);
            holder.textView = (TextView) convertView.findViewById(R.id.id_textview_bank_item);
            convertView.setTag(holder);
        }else{
            holder = (BankCardNameAdapter.ViewHolder)convertView.getTag();
        }

        holder.textView.setText(mItemInfo.get(position).getInfoStr());
        holder.panelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BankCardItemSelectedEvent<BankCardInfoItem> event = new BankCardItemSelectedEvent(position, mItemInfo.get(position));
                RxBus.get().post(event);
            }
        });

        return convertView;
    }



    public static class ViewHolder{
        public View panelView;
        public TextView textView;

    }

    public static class BankCardInfoItem implements InfoAdapter.InfoItem{

        private String infoStr;
        private InfoType type;
        private int id;
        private String level;


        public BankCardInfoItem(String infoStr, InfoType type){
            this.infoStr = infoStr;
            this.type = type;
        }

        public BankCardInfoItem(String infoStr, InfoType type, int id) {
            this.infoStr = infoStr;
            this.type = type;
            this.id = id;
        }

        public BankCardInfoItem(String infoStr, InfoType type, int id, String level) {
            this.infoStr = infoStr;
            this.type = type;
            this.id = id;
            this.level = level;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getInfoStr() {
            return infoStr;
        }

        @Override
        public String getValueStr() {
            return infoStr;
        }

        public void setInfoStr(String infoStr) {
            this.infoStr = infoStr;
        }

        public InfoType getType() {
            return type;
        }

        public void setType(InfoType type) {
            this.type = type;
        }
    }

    public static class BankCardItemSelectedEvent<T>{
        public T data;
        public int pos;

        public BankCardItemSelectedEvent(int pos, T data){
            this.pos = pos;
            this.data = data;
        }

    }
}
