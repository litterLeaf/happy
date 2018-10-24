package com.yinshan.happycash.view.liveness.view.impl.support;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import com.yinshan.happycash.R;

/**
 * Created by huxin on 2018/8/14.
 */
public class HappyProgressDialog extends AlertDialog {
    private ObjectAnimator mLoadAnimation;
    private TextView mMessage;
    private View mView;
    private Handler mHandler;

    public HappyProgressDialog(Context context) {
        this(context, R.style.style_bg_transparent_dialog);
    }

    public HappyProgressDialog(Context context, int theme) {
        super(context, R.style.style_bg_transparent_dialog);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0) {
                    mLoadAnimation.cancel();
                } else {
                    mLoadAnimation.start();
                }
            }
        };
        mView = LayoutInflater.from(context).inflate(R.layout.progress_dialog_loading, null, false);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                (int) (context.getResources().getDisplayMetrics().widthPixels * 0.8f),
////                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//        );
//        params.gravity = Gravity.CENTER;
//        mView.setLayoutParams(params);
        initView(mView);
//        setView(mView);
    }


    private void initView(View view) {
        mMessage = (TextView) view.findViewById(R.id.tv_dialog_message);
        View quan = view.findViewById(R.id.iv_dialog_quan);
        mLoadAnimation = ObjectAnimator.ofFloat(quan, "rotation", 0, 360);
        mLoadAnimation.setDuration(1000);
        mLoadAnimation.setRepeatCount(Animation.INFINITE);
    }

    @Override
    public void setMessage(CharSequence message) {
        mMessage.setText(message);
    }

    @Override
    public void show() {
        if (!mLoadAnimation.isStarted()) {
            if (Looper.myLooper() == null) {
                mHandler.sendEmptyMessage(1);
            } else {
                mLoadAnimation.start();
            }

        }
        super.show();
        setContentView(mView);

    }

    @Override
    public void hide() {
        if (mLoadAnimation.isStarted()) {
            if (Looper.myLooper() == null) {
                mHandler.sendEmptyMessage(0);
            }else{
                mLoadAnimation.cancel();
            }
        }
        super.hide();
    }

    @Override
    public void dismiss() {

        if (mLoadAnimation.isStarted()) {
            if (Looper.myLooper() == null) {
                mHandler.sendEmptyMessage(0);
            }else{
                mLoadAnimation.cancel();
            }
        }

        super.dismiss();
    }
}

