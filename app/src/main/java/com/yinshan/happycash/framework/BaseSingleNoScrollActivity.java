package com.yinshan.happycash.framework;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yinshan.happycash.R;

/**
 * Created by huxin on 2018/3/16.
 */

public abstract class BaseSingleNoScrollActivity extends BaseActivity{

    TextView title;
    RelativeLayout btnBack;
    FrameLayout contentLayout;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        btnBack = (RelativeLayout)findViewById(R.id.btnBack);
        title = (TextView)findViewById(R.id.title);
        contentLayout = (FrameLayout)findViewById(R.id.fragment_content);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title.setText(bindTitle());
        setContentLayout(bindDownLayout());
        init();
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_single_no_scroll_base;
    }

    public void setContentLayout(int resId){
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(resId,null);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        contentView.setLayoutParams(layoutParams);
        contentLayout.addView(contentView);
    }

    protected abstract String bindTitle();

    protected abstract int bindDownLayout();

    protected abstract void init();
}
