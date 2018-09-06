package com.yinshan.happycash.framework;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.yinshan.happycash.R;

/**
 * Created by huxin on 2018/3/15.
 */

public abstract class BaseSingleActivity extends BaseActivity{

    TextView mTitle;
    RelativeLayout mBtnBack;
    FrameLayout mContentLayout;
    protected LinearLayout mLowestBg;
    protected ScrollView mScrollView;

    @Override
    protected void secondLayout() {
        mBtnBack = (RelativeLayout)findViewById(R.id.btnBack);
        mTitle = (TextView)findViewById(R.id.title);
        mContentLayout = (FrameLayout)findViewById(R.id.fragment_content);
        mLowestBg = (LinearLayout)findViewById(R.id.lowest_bg);
        mScrollView = (ScrollView)findViewById(R.id.scrollView);

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mTitle.setText(bindTitle());
        setmContentLayout(bindDownLayout());
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        init();
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_single_base;
    }

    public void setmContentLayout(int resId){
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(resId,null);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        contentView.setLayoutParams(layoutParams);
        mContentLayout.addView(contentView);
    }

    protected abstract String bindTitle();

    protected abstract int bindDownLayout();

    protected void init(){
    }
}
