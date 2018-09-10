package com.yinshan.happycash.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.widget.common.CommonClickListener;

/**
 * Created by huxin on 2018/9/6.
 */
public class CommonDialog extends Dialog{

    String mStrTitle;
    String mStrDesc;
    String mStrCancel;
    String mStrConfirm;

    TextView mTextTitle;
    TextView mTextDesc;
    RelativeLayout mBtnConfirm;
    RelativeLayout mBtnCancel;
    TextView mTextCancel;
    TextView mTextConfirm;

    CommonClickListener mListener;

    public CommonDialog(@NonNull Context context, CommonClickListener listener,String title,String desc,String strCancel,String strConfirm,boolean isCanCancel) {
        super(context, R.style.DialogTheme);
        mListener = listener;
        mStrTitle = title;
        mStrDesc = desc;
        mStrCancel = strCancel;
        mStrConfirm = strConfirm;
        initView(context);
        if(!isCanCancel){
            setCancelable(false);
            setOnKeyListener(new OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        return true;
                    }
                    return false;
                }
            });
        }
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_common, null, false);
        setContentView(view);
        mTextTitle = (TextView)view.findViewById(R.id.title);
        mTextDesc = (TextView)view.findViewById(R.id.desc);
        mBtnConfirm = (RelativeLayout)view.findViewById(R.id.btnConfirm);
        mBtnCancel = (RelativeLayout)view.findViewById(R.id.btnCancel);
        mTextCancel = (TextView)view.findViewById(R.id.textCancel);
        mTextConfirm = (TextView)view.findViewById(R.id.textConfirm);

        if(mStrTitle!=null)
            mTextTitle.setText(mStrTitle);
        if(mStrDesc!=null)
            mTextDesc.setText(mStrDesc);

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick();
                dismiss();
            }
        });
    }
}
