package com.yinshan.happycash.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
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
    LinearLayout mViewTitle;
    TextView mTextDesc;
    RelativeLayout mBtnConfirm;
    View mSpaceView;
    RelativeLayout mBtnCancel;
    TextView mTextCancel;
    TextView mTextConfirm;

    boolean mIsCanCancel;

    CommonClickListener mListener;

    public CommonDialog(@NonNull Context context, CommonClickListener listener, String title, String desc, String strCancel, String strConfirm, boolean isCanCancel) {
        super(context, R.style.DialogTheme);
        mListener = listener;
        mStrTitle = title;
        mStrDesc = desc;
        mStrCancel = strCancel;
        mStrConfirm = strConfirm;
        mIsCanCancel = isCanCancel;
        initView(context,0);
        if(TextUtils.isEmpty(title))
            mViewTitle.setVisibility(View.GONE);
        if(!isCanCancel){
            setCancelable(false);
            setOnKeyListener(new OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    return  keyCode == KeyEvent.KEYCODE_BACK;
                }
            });
            mBtnCancel.setVisibility(View.GONE);
            mSpaceView.setVisibility(View.GONE);
        }
    }
    public CommonDialog(@NonNull Context context,int layout ,CommonClickListener listener, String title, String desc, String strCancel, String strConfirm, boolean isCanCancel) {
        super(context, R.style.DialogTheme);
        mListener = listener;
        mStrTitle = title;
        mStrDesc = desc;
        mStrCancel = strCancel;
        mStrConfirm = strConfirm;
        mIsCanCancel = isCanCancel;
        initView(context,layout);
        if(TextUtils.isEmpty(title))
            mViewTitle.setVisibility(View.GONE);
        if(!isCanCancel){
            setCancelable(false);
            setOnKeyListener(new OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
//                    if (keyCode == KeyEvent.KEYCODE_BACK) {
//                        return true;
//                    }
//                    return false;
                    return  keyCode==KeyEvent.KEYCODE_BACK;
                }
            });
            mBtnCancel.setVisibility(View.GONE);
            mSpaceView.setVisibility(View.GONE);
        }
    }

    private void initView(Context context,int layout) {
        if(layout==0){
            layout = R.layout.dialog_common;
        }
        View view = LayoutInflater.from(context).inflate(layout, null, false);
        setContentView(view);
        mTextTitle = (TextView)view.findViewById(R.id.title);
        mViewTitle = (LinearLayout)view.findViewById(R.id.titleView);
        mTextDesc = (TextView)view.findViewById(R.id.desc);
        mBtnConfirm = (RelativeLayout)view.findViewById(R.id.btnConfirm);
        mBtnCancel = (RelativeLayout)view.findViewById(R.id.btnCancel);
        mTextCancel = (TextView)view.findViewById(R.id.textCancel);
        mSpaceView = (View)view.findViewById(R.id.spaceView);
        mTextConfirm = (TextView)view.findViewById(R.id.textConfirm);

        if(!TextUtils.isEmpty(mStrTitle))
            mTextTitle.setText(mStrTitle);
        if(!TextUtils.isEmpty(mStrDesc))
            mTextDesc.setText(mStrDesc);
        if(!TextUtils.isEmpty(mStrConfirm))
            mTextConfirm.setText(mStrConfirm);
        if(!TextUtils.isEmpty(mStrCancel))
            mTextCancel.setText(mStrCancel);

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
