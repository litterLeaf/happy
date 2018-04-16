package com.yinshan.happycash.view.information.view.impl.support;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.yinshan.happycash.R;
import com.yinshan.happycash.utils.ScreenUtils;
import com.yinshan.happycash.widget.common.CommonClickListener;

/**
 * Created by huxin on 2018/4/16.
 */

public class UploadKtpPhotoDialog extends Dialog{

    Context mContext;
    CommonClickListener mListener;

    public UploadKtpPhotoDialog(@NonNull Context context, int themeResId,CommonClickListener listener) {
        super(context,themeResId);
        mContext = context;
        mListener = listener;
        init();
    }

    private void init(){
        setContentView(R.layout.dialog_upload_self_ktp);
        RelativeLayout btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick();
            }
        });

        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (int) (ScreenUtils.getScreenWidth(mContext));
        lp.height = (int) (ScreenUtils.getScreenHeight(mContext));
        window.setAttributes(lp);
        window.setGravity(Gravity.CENTER);
        setCanceledOnTouchOutside(true);
    }
}
