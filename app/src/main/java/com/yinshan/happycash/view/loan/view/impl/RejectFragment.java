package com.yinshan.happycash.view.loan.view.impl;

import android.text.TextUtils;
import android.view.View;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseFragment;
import com.yinshan.happycash.utils.SPKeyUtils;
import com.yinshan.happycash.utils.SPUtils;
import com.yinshan.happycash.view.main.model.LastLoanAppBean;
import com.yinshan.happycash.view.me.model.LoanDetailBean;
import com.yinshan.happycash.view.me.presenter.LoanDetailPresenter;
import com.yinshan.happycash.view.me.view.ILoanDetailView;

/**
 * Created by admin on 2018/3/20.
 */

public class RejectFragment extends BaseFragment implements ILoanDetailView{

    LoanDetailPresenter mDetailPresenter;

    @Override
    protected void initView() {
        mDetailPresenter = new LoanDetailPresenter(getActivity(),this);
        LastLoanAppBean bean = SPUtils.getInstance().getObject(SPKeyUtils.LOANAPPBEAN,LastLoanAppBean.class);
        if(bean!=null&& !TextUtils.isEmpty(bean.getLoanAppId()))
            mDetailPresenter.getDetail(Long.valueOf(bean.getLoanAppId()));
    }

    @Override
    protected void initUIValue(View view) {

    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_reject;
    }

    @Override
    public void showDetail(LoanDetailBean value) {

    }
}
