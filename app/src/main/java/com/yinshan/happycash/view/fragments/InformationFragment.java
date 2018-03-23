package com.yinshan.happycash.view.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseFragment;
import com.yinshan.happycash.widget.userdefined.ProfilProgressView;

import butterknife.BindView;


/**
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 *        ┃　　　┃   神兽保佑
 *        ┃　　　┃   代码无BUG！
 *        ┃　　　┗━━━┓
 *        ┃　　　　　　　┣┓
 *        ┃　　　　　　　┏┛
 *        ┗┓┓┏━┳┓┏┛
 *           ┃┫┫　┃┫┫
 *           ┗┻┛　┗┻┛
 *
 *  @author  admin
 *  on 2018/1/31
 *
 */

public class InformationFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    float progress;

    @BindView(R.id.progressView)
    ProfilProgressView mProgressView;
    @BindView(R.id.progressText)
    TextView mProgressText;

    @Override
    protected void initView() {
        progress = 0.5f;
        mProgressView.setCurrentProgress(progress);
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append(String.valueOf(progress*100));
        spannableString.append("%");
//        AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(20);
//        AbsoluteSizeSpan sizeSpan2 = new AbsoluteSizeSpan(10);
//        spannableString.setSpan(sizeSpan, 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        spannableString.setSpan(sizeSpan2, 3, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mProgressText.setText(spannableString);
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_information;
    }

}
