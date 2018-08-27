package com.yinshan.happycash.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.yinshan.happycash.R;
import com.yinshan.happycash.widget.common.CommonClickListener;

/**
 * Created by huxin on 2018/8/27.
 */
public class PowerDialog extends Dialog{

    CommonClickListener mListener;

    public PowerDialog(@NonNull Context context, CommonClickListener listener) {
        super(context, R.style.DialogTheme);
        mListener = listener;
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_permission_guide, null, false);
        setContentView(view);
        RelativeLayout btn = (RelativeLayout) view.findViewById(R.id.btn_confirm);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                mListener.onClick();
            }
        });
    }
}
