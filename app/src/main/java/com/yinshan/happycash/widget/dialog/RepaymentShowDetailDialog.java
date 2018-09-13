package com.yinshan.happycash.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.widget.common.CommonClickListener;

/**
 * Created by huxin on 2018/8/27.
 */
public class RepaymentShowDetailDialog extends Dialog{

    TextView mText1;
    TextView mText2;
    TextView mText3;

    public RepaymentShowDetailDialog(@NonNull Context context) {
        super(context, R.style.DialogTheme);
        initView(context);
        setCanceledOnTouchOutside(true);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_repayment_show_detail, null, false);
        setContentView(view);
        mText1 = (TextView)view.findViewById(R.id.text1);
        mText2 = (TextView)view.findViewById(R.id.text2);
        mText3 = (TextView)view.findViewById(R.id.text3);
    }

    public void setText1(String str){
        mText1.setText(str);
    }

    public void setText2(String str){
        mText2.setText(str);
    }

    public void setText3(String str){
        mText3.setText(str);
    }
}
