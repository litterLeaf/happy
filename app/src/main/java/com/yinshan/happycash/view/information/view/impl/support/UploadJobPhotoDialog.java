package com.yinshan.happycash.view.information.view.impl.support;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.yinshan.happycash.R;
import com.yinshan.happycash.utils.ScreenUtils;

/**
 * Created by huxin on 2018/4/2.
 */

public class UploadJobPhotoDialog extends Dialog{

    Context mContext;

    public UploadJobPhotoDialog(@NonNull Context context) {
        super(context);
        mContext = context;
        init();
    }

    public UploadJobPhotoDialog(@NonNull Context context, int themeResId) {
        super(context, R.style.DialogTheme);
        mContext = context;
        init();
    }

    protected UploadJobPhotoDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        mContext = context;
        init();
    }

    private void init(){
        setContentView(R.layout.dialog_upload_work_ktp);

        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (int) (ScreenUtils.getScreenWidth(mContext));
        lp.height = (int) (ScreenUtils.getScreenHeight(mContext));
        window.setAttributes(lp);
        window.setGravity(Gravity.CENTER);
        setCanceledOnTouchOutside(true);
    }
}
