package com.yinshan.happycash.view.loan.view.impl.support;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.widget.TextView;

import com.yinshan.happycash.utils.ScreenUtils;

/**
 * Created by huxin on 2018/4/8.
 */

public class ApplyFitTextView extends TextView{
    public ApplyFitTextView(Context context) {
        super(context);
    }

    public ApplyFitTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ApplyFitTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Layout layout = getLayout();
        if (layout != null) {
            int height = (int) Math.ceil(getMaxLineHeight(this.getText().toString()))
                    + getCompoundPaddingTop() + getCompoundPaddingBottom();
            int width = getMeasuredWidth();
            setMeasuredDimension(width, height);
        }
    }

    private float getMaxLineHeight(String str) {
        float screenW = ((Activity)getContext()).getWindowManager().getDefaultDisplay().getWidth();

        //这里具体this.getPaint()要注意使用，要看你的TextView在什么位置，这个是拿TextView父控件的Padding的，为了更准确的算出换行
        int line = (int) Math.ceil( (this.getPaint().measureText(str)/(screenW- ScreenUtils.dip2px(getContext(),25+30+15+15+15+25))));
        float height = (this.getPaint().getFontMetrics().descent-this.getPaint().getFontMetrics().ascent)*line;
        return height;
    }
}
