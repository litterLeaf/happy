package com.yinshan.happycash.view.loan.view.impl.support;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hwangjr.rxbus.RxBus;
import com.yinshan.happycash.R;
import com.yinshan.happycash.utils.StringFormatUtils;
import com.yinshan.happycash.utils.TimeManager;
import com.yinshan.happycash.view.loan.view.impl.RepaymentFragment;
import com.yinshan.happycash.view.me.model.StageBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huxin on 2018/4/4.
 */

public class RepaymentAdapter extends BaseAdapter{

    Context mContext;
    List<StageBean> mList;

    public RepaymentAdapter(Context context){
        mContext = context;
        mList = new ArrayList<>();
    }

    public void setList(List<StageBean> list){
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_repayment,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.time = (TextView)convertView.findViewById(R.id.time);
            viewHolder.money = (TextView)convertView.findViewById(R.id.money);
            viewHolder.status = (TextView)convertView.findViewById(R.id.status);
            viewHolder.detailImage = (ImageView) convertView.findViewById(R.id.detailImage);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.time.setText(TimeManager.convertYNTimeDay(mList.get(position).getDueDate()));
        viewHolder.money.setText(StringFormatUtils.moneyFormat(RepaymentFragment.getSumAccr(mList.get(position))));
        viewHolder.detailImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RepaymentDetailEvent event = new RepaymentDetailEvent(position);
                RxBus.get().post(event);
            }
        });
        if((mList.get(position).getDefaultAccr()-mList.get(position).getDefaultPaid())>=1){
            viewHolder.detailImage.setVisibility(View.VISIBLE);
        }

        String status = mList.get(position).getStatus();
        if(status.equals("INACTIVE"))
            viewHolder.status.setText(mContext.getString(R.string.return_clear));
        else if(status.equals("ACTIVE"))
            viewHolder.status.setText(mContext.getString(R.string.not_return_clear));
        else if(status.equals("OVERDUE"))
            viewHolder.status.setText(mContext.getString(R.string.more_than_expected));

        return convertView;
    }

    class ViewHolder{
        TextView time;
        TextView money;
        ImageView detailImage;
        TextView status;
    }

    public static class RepaymentDetailEvent{
        public int pos;

        public RepaymentDetailEvent(int pos){
            this.pos = pos;
        }
    }

    public static double getSumAccr(StageBean bean){
        return bean.getPrincipalAccr()+bean.getDefaultAccr()+bean.getInterestAccr();
    }
}
