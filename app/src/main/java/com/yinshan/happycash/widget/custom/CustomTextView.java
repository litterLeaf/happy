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
        int mode = MeasureSpec.getMode(heightMeasureSpec);

        Layout layout = getLayout();
        if (layout != null) {
            int height = (int)Math.ceil(getMaxLineHeight(this.getText().toString(),mode))
                    + getCompoundPaddingTop() + getCompoundPaddingBottom();
            int width = getMeasuredWidth();
            setMeasuredDimension(width, height);
        }
    }

    private float getMaxLineHeight(String str,int mode) {
        float height = 0.0f;
        float screenW = ScreenUtils.getScreenWidth(mContext)-DensityUtil.dip2px(mContext,50+31);
        float paddingLeft = ((LinearLayout)this.getParent()).getPaddingLeft();
        float paddingReft = ((LinearLayout)this.getParent()).getPaddingRight();
//这里具体this.getPaint()要注意使用，要看你的TextView在什么位置，这个是拿TextView父控件的Padding的，为了更准确的算出换行
        int line = 0;

        float rowLength = screenW*str.length()/getPaint().measureText(str);

        String testStr = "1234567890";
        String w = testStr.substring(0,3);
        int l = testStr.lastIndexOf('5');


        if(getPaint().measureText(str)<=screenW){
            line = 1;
        }else{
            //安全的文字长度，保证兼容性
            int safeRowLength = (int) (rowLength -2);

            while(getPaint().measureText(str.substring(0,safeRowLength))<screenW){
                safeRowLength++;
            }
            safeRowLength = safeRowLength - 1;

            MyDebugUtils.v("stepHeight safeRowLength "+safeRowLength);

            int countLength = 0;

            while(countLength<str.length()){
//                MyDebugUtils.v("stepHeight1 "+countLength);
                line++;
                if(countLength+safeRowLength>=str.length()) {
                    MyDebugUtils.v("stepHeight x "+str.substring(countLength,str.length()));
                    countLength = countLength + safeRowLength;
                    break;
                }else{
                    if(str.charAt(countLength+safeRowLength-1)!=' '){
                        String substring = str.substring(countLength, countLength + safeRowLength);
                        int lastIndex = substring.lastIndexOf(' ');
//                        if(lastIndex>(safeRowLength-20))
                        if(lastIndex==0||lastIndex==-1) {
                            MyDebugUtils.v("stepHeight x "+str.substring(countLength,countLength+safeRowLength));
                            countLength = countLength + safeRowLength;
                        }else {
                            MyDebugUtils.v("stepHeight x "+str.substring(countLength,countLength + lastIndex + 1));
                            countLength = countLength + lastIndex + 1;
                        }
                    }else{
                        MyDebugUtils.v("stepHeight x "+str.substring(countLength,countLength+safeRowLength));
                        countLength = countLength + safeRowLength;
                    }
                }

            }
        }

//        if(mode == MeasureSpec.UNSPECIFIED){
//             line = (int) Math.ceil( (this.getPaint().measureText(str)/(screenW-paddingLeft-paddingReft-getPaddingLeft()-getPaddingRight())));
//        }else{
//            line = (int) Math.ceil( (this.getPaint().measureText(str)/(screenW-getPaddingLeft()-getPaddingRight())));
//        }
        if(line==1) {
            height = (this.getPaint().getFontMetrics().descent - this.getPaint().getFontMetrics().ascent) * 1 + DensityUtil.dip2px(mContext, 2);
        }else {
//            if(isSN)
//                line++;
            height = (this.getPaint().getFontMetrics().descent - this.getPaint().getFontMetrics().ascent+DensityUtil.dip2px(mContext, 2)) * line ;
        }
        MyDebugUtils.v("stepHeight item text is "+height +"   "+str+"    "+this.getPaint().measureText(str)+ "     "+line+"   "+ScreenUtils.getScreenWidth(mContext)+"  "+screenW+"  "+mode+"  "+paddingLeft+"  "+paddingReft+"  "+getPaddingLeft()+"  "+getPaddingRight());
        return height;
    }
}
