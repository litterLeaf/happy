package com.yinshan.happycash.view.loan.view.impl.support;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import com.yinshan.happycash.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2017/12/27.
 */

public class BuildUpViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.tv_reason)
    TextView reason;
    @BindView(R.id.tv_check_number)
    TextView checkDesc;


    public BuildUpViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
