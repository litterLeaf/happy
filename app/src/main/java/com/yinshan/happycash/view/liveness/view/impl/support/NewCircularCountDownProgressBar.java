package com.yinshan.happycash.view.liveness.view.impl.support;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oliveapp.face.livenessdetectorsdk.utilities.utils.LogUtil;
import com.oliveapp.face.livenessdetectorsdk.utilities.utils.PackageNameManager;

/**
 * Created by huxin on 2018/8/13.
 */
public class NewCircularCountDownProgressBar extends RelativeLayout{
    private String a;
    private long b = 10000L;
    private View c;
    private ProgressBar d;
    private TextView e;
    private ObjectAnimator f;

    public NewCircularCountDownProgressBar(Context context) {
        super(context);
        this.a();
    }

    public NewCircularCountDownProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.a();
    }

    public NewCircularCountDownProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.a();
    }
    private void a() {
        this.a = PackageNameManager.getPackageName();
        this.c = inflate(this.getContext(), this.getResources().getIdentifier("oliveapp_circular_count_down_progress_bar", "layout", this.a), this);
        this.d = (ProgressBar)this.findViewById(this.getResources().getIdentifier("oliveapp_timeoutProgressbar", "id", this.a));
        this.e = (TextView)this.findViewById(this.getResources().getIdentifier("oliveapp_countdownTextView", "id", this.a));
    }

    public void start(long countDownMillisecond) {
        if(this.f != null) {
            this.f.cancel();
        }

        this.f = ObjectAnimator.ofInt(this.d, "progress", new int[]{1000, 0});
        this.f.setDuration(countDownMillisecond);
        this.f.setInterpolator(new LinearInterpolator());
        this.b = countDownMillisecond;
        this.f.start();

        try {
            this.e.postDelayed(new Runnable() {
                public void run() {
                    if(NewCircularCountDownProgressBar.this.d != null && NewCircularCountDownProgressBar.this.e != null) {
                        try {
                            LogUtil.e("CircularCountDownProgress", "test: " + NewCircularCountDownProgressBar.this.d.getProgress() + ", " + NewCircularCountDownProgressBar.this.d);
                            NewCircularCountDownProgressBar.this.b();
                            NewCircularCountDownProgressBar.this.e.postDelayed(this, 400L);
                        } catch (NullPointerException var2) {
                            LogUtil.w("CircularCountDownProgress", "update countdown timer text" + var2);
                        }

                    }
                }
            }, 200L);
        } catch (NullPointerException var4) {
            LogUtil.e("CircularCountDownProgress", "Fail to start timer", var4);
        }

    }

    public void stop() {
        if(this.f != null) {
            this.f.cancel();
        }

    }

    public void destory() {
        this.stop();
        this.e = null;
        this.d = null;
        this.f = null;
        this.f = null;
    }

    public void restart() {
        this.stop();
        this.start(this.b);
    }

    public int getProgress() {
        return this.d.getProgress();
    }

    public void setProgress(int progress) {
        this.d.setProgress(progress);
    }

    private void b() {
        long var1 = (long)Math.ceil((double)((float)this.b / 1000.0F * (float)this.d.getProgress() / (float)this.d.getMax()));
        if(var1 <= 0L) {
            this.e.setText("End");
        } else {
            this.e.setTextKeepState(String.valueOf(var1 + "s"));
        }
    }

    public void setRemainTimeSecond(int remainTimeMilliSecond, int totalTimeMilliSecond) {
        this.b = (long)totalTimeMilliSecond;
        this.d.setProgress(1000 * remainTimeMilliSecond / totalTimeMilliSecond);
        this.b();
    }
}
