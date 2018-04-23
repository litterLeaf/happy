package com.yinshan.happycash.view.main;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2018/4/23.
 */

public class QuestionActivity extends BaseActivity {
    @BindView(R.id.textView2)
    TextView textView2;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_question;
    }

    @Override
    protected void secondLayout() {

    }

    @Override
    protected void secondInit() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
