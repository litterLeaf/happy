package com.yinshan.happycash.view.loan.view.impl.support;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hwangjr.rxbus.RxBus;
import com.yinshan.happycash.R;
import com.yinshan.happycash.view.information.view.impl.support.InfoAdapterEnum;

import java.util.List;

/**
 * Created by huxin on 2018/8/17.
 */
public class RepaymentDialogAdapter extends BaseAdapter{

    Context mContext;
    private List<String> mDepositMethods;
    double mShouldPrePay;

    public RepaymentDialogAdapter(Context context, List<String> strs, double shouldPrePay){
        mContext = context;
        mDepositMethods = strs;
        mShouldPrePay = shouldPrePay;
    }

    @Override
    public int getCount() {
        return mDepositMethods.size();
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_dialog_repayment,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.text = (TextView)convertView.findViewById(R.id.text);
            viewHolder.btnClick = (RelativeLayout) convertView.findViewById(R.id.btnClick);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.text.setText(mDepositMethods.get(position));
        viewHolder.btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RepaymentDialogEvent<String> event = new RepaymentDialogEvent(position,mDepositMethods.get(position));
                event.type = InfoAdapterEnum.RepaymentType;
                event.shouldPrePay = mShouldPrePay;
                RxBus.get().post(event);
            }
        });
        return convertView;
    }

    class ViewHolder{
        TextView text;
        RelativeLayout btnClick;
    }

    public static class RepaymentDialogEvent<T>{
        public T data;
        public int pos;
        public int type;
        public double shouldPrePay;

        public RepaymentDialogEvent(int pos, T data){
            this.pos = pos;
            this.data = data;
        }
    }
}
