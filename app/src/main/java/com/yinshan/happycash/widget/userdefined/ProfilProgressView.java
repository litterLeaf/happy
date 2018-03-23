package com.yinshan.happycash.widget.userdefined;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.yinshan.happycash.R;

/**
 * Created by huxin on 2018/3/23.
 */

public class ProfilProgressView extends View {

    Context mContext;


    public ProfilProgressView(Context context) {
        super(context);
        mContext = context;
    }

    public ProfilProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public ProfilProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint circlePaint = new Paint();
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setColor(getResources().getColor(R.color.app_white));
        canvas.drawCircle(getWidth()/2,getHeight()/2,getWidth()/2,circlePaint);

        Paint arcPaint = new Paint();
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setAntiAlias(true);//取消锯齿
        arcPaint.setColor(getResources().getColor(R.color.app_blue));
        int arcWidth = 4;
        arcPaint.setStrokeWidth(arcWidth);

        RectF oval = new RectF(arcWidth,arcWidth,getWidth()-arcWidth,getHeight()-arcWidth);
        canvas.drawArc(oval,360,440,false,arcPaint);
    }
}
