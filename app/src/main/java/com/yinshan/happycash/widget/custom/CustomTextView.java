package com.yinshan.happycash.widget.custom;

import android.content.Context;
import android.graphics.Paint;
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
                line++;
                if(countLength+safeRowLength>=str.length()){
                    break;
                }else{
                    //递归
                    if(getPaint().measureText(str.substring(countLength,safeRowLength))>screenW){
                        getPaint().measureText(str.substring(countLength,safeRowLength-1));
                    }else{
                        getPaint().measureText(str.substring(countLength,safeRowLength+1));
                    }
                }
            }

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

    //返回本行阶段数据个数，返回-1，代表本行，返回-2代表加一行，返回其他大于0的数据代表本行截断数据个数
    private int getSafeRowLength(Paint paint, String str, int countLength,int guessCount, int textWidth,boolean isIgnoreScale){
        int strLength = str.length();
        if(isIgnoreScale){

        }
        //判断是否超过字数长度
        if(countLength+guessCount>str.length())
            //不合理字数，超过行宽
            if(paint.measureText(str.substring(countLength,str.length()-guessCount))>textWidth){
                getSafeRowLength(paint,str,countLength,str.length()-guessCount-1,textWidth,false);
            //达到最后一行了
            }else{
                return -1;
            }
        //没有超过字数长度，正常
        else{
            //不合理字数，超过行宽
            if(paint.measureText(str.substring(countLength,countLength+guessCount))>textWidth)
                getSafeRowLength(paint,str,countLength,guessCount-1,textWidth,false);
            //正好等于行宽
            else if(paint.measureText(str.substring(countLength,countLength+guessCount))==textWidth){
                return guessCount;
            //当前小于行宽
            }else{
                //如果可以进行下一位空格与否判断，最正常的情况
                if((countLength+guessCount+1)<=str.length()){
                    //如果下一个字小于行宽，递归取值
                    if(paint.measureText(str.substring(countLength,countLength+guessCount+1))<textWidth){
                        getSafeRowLength(paint,str,countLength,str.length()-guessCount+1,textWidth,false);
                    //如果下一个字等于行宽
                    }else if(paint.measureText(str.substring(countLength,countLength+guessCount+1))==textWidth&&(countLength+guessCount+2)<=strLength&&str.charAt(countLength+guessCount+1)==' '){
                        return guessCount+1;
                    //如果下一个字大于行宽
                    }else{
                        //正常情况
                        if(guessCount<3)
                            return -1;
                        else{
                            if(str.charAt(countLength+guessCount-1)==' '){
                                return guessCount;
                            }else{
                                getSafeRowLength(paint,str,countLength,str.length()-guessCount-1,textWidth,true);
                            }
                        }
                    }
                //到最后一行了，只多一个字
                }else{
                    //小于行宽
                    if(paint.measureText(str.substring(countLength,countLength+guessCount-1))<textWidth){
                        getSafeRowLength(paint,str,countLength,guessCount-1,textWidth,false);
                    //因为只多了一个字，
                    }else if(paint.measureText(str.substring(countLength,countLength+guessCount-1))==textWidth){
                        return guessCount-1;
                    }
                    //不可能大于，上面已经判断了
                    else {
                        return -1;
                    }
                }
            }
        }
        return -1;
    }
}
