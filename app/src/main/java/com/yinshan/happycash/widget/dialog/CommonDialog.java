package com.yinshan.happycash.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import com.yinshan.happycash.R;
import com.yinshan.happycash.widget.common.CommonClickListener;

/**
 * Created by huxin on 2018/9/6.
 */
public class CommonDialog extends Dialog{

    CommonClickListener mListener;

    public CommonDialog(@NonNull Context context, CommonClickListener listener) {
        super(context, R.style.DialogTheme);
        mListener = listener;
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_permission_guide, null, false);
    }
}
