package com.yinshan.happycash.widget.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yinshan.happycash.utils.DensityUtil;
import com.yinshan.happycash.utils.MyDebugUtils;
import com.yinshan.happycash.utils.ScreenUtils;

/**
 * Created by huxin on 2018/9/6.
 */
public class CustomTextView extends AppCompatTextView{

    Context mContext;
    boolean isSN;

    public CustomTextView(Context context) {
        super(context);
        mContext = context;
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public void setIsSN(boolean flag){
        isSN = flag;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        Layout layout = getLayout();
        if (layout != null) {
            int height = (int)Math.ceil(getMaxLineHeight(this.getText().toString()))
                    + getCompoundPaddingTop() + getCompoundPaddingBottom();
            int width = getMeasuredWidth();
            setMeasuredDimension(width, height);
        }
    }

    private float getMaxLineHeight(String str) {
        float height = 0.0f;
        float screenW = ScreenUtils.getScreenWidth(mContext)-DensityUtil.dip2px(mContext,50+31+10);
        float paddingLeft = ((LinearLayout)this.getParent()).getPaddingLeft();
        float paddingReft = ((LinearLayout)this.getParent()).getPaddingRight();
//这里具体this.getPaint()要注意使用，要看你的TextView在什么位置，这个是拿TextView父控件的Padding的，为了更准确的算出换行
        int line = (int) Math.ceil( (this.getPaint().measureText(str)/(screenW-paddingLeft-paddingReft)));

        if(line==0) {
            height = (this.getPaint().getFontMetrics().descent - this.getPaint().getFontMetrics().ascent) * 1 + DensityUtil.dip2px(mContext, 6);
        }else {
            if(isSN)
                line++;
            height = (this.getPaint().getFontMetrics().descent - this.getPaint().getFontMetrics().ascent) * line + (line - 1) * DensityUtil.dip2px(mContext, 6);
        }
        MyDebugUtils.v("stepHeight item text is "+height +"   "+str+"    "+this.getPaint().measureText(str));
        return height;
    }
}
