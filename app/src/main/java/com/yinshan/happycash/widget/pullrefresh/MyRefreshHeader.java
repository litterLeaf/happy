package com.yinshan.happycash.widget.pullrefresh;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.yinshan.happycash.R;

/**
 * Created by huxin on 2018/4/3.
 */

public class MyRefreshHeader extends FrameLayout implements RefreshHeader{

    public MyRefreshHeader(Context context){
        this(context,null);
    }

    public MyRefreshHeader(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        inflate(context, R.layout.refresh_header,this);
    }

    @Override
    public void reset() {

    }

    @Override
    public void pull() {

    }

    @Override
    public void refreshing() {

    }

    @Override
    public void onPositionChange(float currentPos, float lastPos, float refreshPos, boolean isTouch, State state) {

    }

    @Override
    public void complete() {

    }
}
