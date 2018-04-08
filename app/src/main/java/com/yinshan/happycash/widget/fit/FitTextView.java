package com.yinshan.happycash.widget.fit;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by huxin on 2018/4/8.
 */

public class FitTextView extends TextView{
    public FitTextView(Context context) {
        super(context);
    }

    public FitTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FitTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Layout layout = getLayout();
        if(layout!=null){
            int height = (int) Math.ceil(getMaxLineHeight(getText().toString(),widthMeasureSpec));
            int width = getMeasuredWidth();
            setMeasuredDimension(width,height);
        }
    }

    private float getMaxLineHeight(String str,int width){
        float screenW = ((Activity)getContext()).getWindowManager().getDefaultDisplay().getWidth();
        float paddingLeft = ((LinearLayout)this.getParent()).getPaddingLeft();
        float paddingRight = ((LinearLayout)this.getParent()).getPaddingRight();
        int line = (int)Math.ceil((getPaint().measureText(str)/(screenW-paddingLeft-paddingRight)));
        float height = (this.getPaint().getFontMetrics().descent-getPaint().getFontMetrics().ascent)*line;
        return height;
    }
}
