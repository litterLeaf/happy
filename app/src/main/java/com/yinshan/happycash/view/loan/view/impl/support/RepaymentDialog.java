package com.yinshan.happycash.view.loan.view.impl.support;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.hwangjr.rxbus.annotation.Subscribe;
import com.yinshan.happycash.R;
import com.yinshan.happycash.view.information.view.impl.support.InfoAdapterEnum;
import com.yinshan.happycash.view.loan.model.DepositMethodsBean;

/**
 * Created by huxin on 2018/8/17.
 */
public class RepaymentDialog extends Dialog{

    Context mContext;
    DepositMethodsBean mBean;

    public RepaymentDialog(@NonNull Context context,DepositMethodsBean bean) {
        super(context);
        mContext = context;
        mBean = bean;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_repayment);

        ListView listView = (ListView)findViewById(R.id.list);
        RepaymentDialogAdapter adapter = new RepaymentDialogAdapter(mContext,mBean.getDepositMethods());
        listView.setAdapter(adapter);
    }


}
