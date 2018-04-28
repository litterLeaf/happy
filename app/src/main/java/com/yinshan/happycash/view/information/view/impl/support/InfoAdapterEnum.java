package com.yinshan.happycash.view.information.view.impl.support;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hwangjr.rxbus.RxBus;
import com.yinshan.happycash.R;
import com.yinshan.happycash.view.information.model.InfoValueType;

import java.util.ArrayList;

/**
 * Created by huxin on 2018/4/26.
 */

public class InfoAdapterEnum extends BaseAdapter implements InfoAdapter{
    private final Context mContext;

    private ArrayList<InfoItem> mItemInfo = new ArrayList<>();
    private LayoutInflater mInflater;

    public InfoAdapterEnum(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public void addItem(final InfoValueType item, InfoType type){
        mItemInfo.add(new InfoItem(item,type));
        notifyDataSetChanged();
    }

    public void addItem(final InfoValueType item, InfoType type, int id){
        mItemInfo.add(new InfoItem(item,type,id));
        notifyDataSetChanged();
    }

    public void addItem(final InfoValueType item, InfoType type, int id,String level){
        mItemInfo.add(new InfoItem(item,type,id,level));
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
        ViewHolder holder = null;

        if(convertView == null){
            convertView = mInflater.inflate(R.layout.bank_list_item,null);
            holder = new ViewHolder();
            holder.panelView = convertView.findViewById(R.id.id_relativelayout_bank_item);
            holder.textView = (TextView) convertView.findViewById(R.id.id_textview_bank_item);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        if (!TextUtils.isEmpty(mItemInfo.get(position).getInfoStr())) {
            holder.textView.setText(mItemInfo.get(position).getInfoStr());
        }
        holder.panelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ItemSelectedEvent<InfoItem> event = new ItemSelectedEvent(position, mItemInfo.get(position));
                RxBus.get().post(event);
            }
        });

        return convertView;
    }



    public static class ViewHolder{
        public View panelView;
        public TextView textView;

    }

    public  class InfoItem implements InfoAdapter.InfoItem{

        private InfoValueType infoStr;
        private InfoType type;
        private int id;
        private String level;


        public InfoItem(InfoValueType infoStr, InfoType type){
            this.infoStr = infoStr;
            this.type = type;
        }

        public InfoItem(InfoValueType infoStr, InfoType type, int id) {
            this.infoStr = infoStr;
            this.type = type;
            this.id = id;
        }

        public InfoItem(InfoValueType infoStr, InfoType type, int id, String level) {
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
            return InfoAdapterEnum.this.mContext.getResources().getText(infoStr.getShowString()).toString();
        }

        @Override
        public String getValueStr() {
            return infoStr.getValue();
        }

        public void setInfoStr(InfoValueType infoStr) {
            this.infoStr = infoStr;
        }

        public InfoType getType() {
            return type;
        }

        public void setType(InfoType type) {
            this.type = type;
        }

    }
    public static class ItemSelectedEvent<T>{
        public T data;
        public int pos;

        public ItemSelectedEvent(int pos, T data){
            this.pos = pos;
            this.data = data;
        }
    }
}
