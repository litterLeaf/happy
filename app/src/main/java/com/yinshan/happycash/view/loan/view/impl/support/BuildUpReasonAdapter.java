package com.yinshan.happycash.view.loan.view.impl.support;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hwangjr.rxbus.RxBus;
import com.yinshan.happycash.R;
import com.yinshan.happycash.view.loan.model.BuildUpReasonEvent;
import com.yinshan.happycash.view.loan.model.BuildUpReasonModel;

import java.util.List;

/**
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 *        ┃　　　┃   神兽保佑
 *        ┃　　　┃   代码无BUG！
 *        ┃　　　┗━━━┓
 *        ┃　　　　　　　┣┓
 *        ┃　　　　　　　┏┛
 *        ┗┓┓┏━┳┓┏┛
 *           ┃┫┫　┃┫┫
 *           ┗┻┛　┗┻┛
 *
 *  @author  admin
 *  on 2018/4/28
 *
 */

public class BuildUpReasonAdapter extends RecyclerView.Adapter<BuildUpViewHolder>{

    private List<BuildUpReasonModel> mReasonList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public BuildUpReasonAdapter(List<BuildUpReasonModel> reasonList){
        mReasonList = reasonList;
    }

    @Override
    public BuildUpViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        mLayoutInflater = LayoutInflater.from(mContext);
        View view = mLayoutInflater.inflate(R.layout.item_build_up_reason,parent,false);

        return new BuildUpViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BuildUpViewHolder holder, final int position) {
        final BuildUpReasonModel model = mReasonList.get(position);
        holder.reason.setText((position+1)+"."+model.getReason());
        holder.checkDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuildUpReasonEvent event = new BuildUpReasonEvent(position);
                RxBus.get().post(event);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mReasonList!=null?mReasonList.size():0;
    }
}
