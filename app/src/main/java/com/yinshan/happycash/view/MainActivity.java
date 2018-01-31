package com.yinshan.happycash.view;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.network.api.UserApi;
import com.yinshan.happycash.network.common.RxHttpUtils;
import com.yinshan.happycash.framework.BaseActivity;
import com.yinshan.happycash.view.fragments.HotLineFragment;
import com.yinshan.happycash.view.fragments.InformationFragment;
import com.yinshan.happycash.view.fragments.MineFragment;
import com.yinshan.happycash.view.fragments.UnLoanFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.yokeyword.fragmentation.SupportFragment;
import okhttp3.ResponseBody;

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
 * ┃　　　┃   神兽保佑
 * ┃　　　┃   代码无BUG！
 * ┃　　　┗━━━┓
 * ┃　　　　　　　┣┓
 * ┃　　　　　　　┏┛
 * ┗┓┓┏━┳┓┏┛
 * ┃┫┫　┃┫┫
 * ┗┻┛　┗┻┛
 *
 * @author admin
 *         on 2018/1/11
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.id_textview_tab_loan)
    TextView textViewLoan;
    @BindView(R.id.id_textview_tab_certification)
    TextView textViewCertification;
    @BindView(R.id.id_textview_tab_me)
    TextView textViewMe;
    @BindView(R.id.id_textview_tab_online_qa)
    TextView textViewOnlineQA;
    @BindView(R.id.id_fragment_loan)
    FrameLayout fragmentLoan;
    private SupportFragment[] mFragments = new SupportFragment[4];

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            mFragments[0] = UnLoanFragment.newInstance("home", "");
            mFragments[1] = InformationFragment.newInstance("plan&loan", "");
            mFragments[2] = MineFragment.newInstance("user", "");
            loadMultipleRootFragment(R.id.id_fragment_loan, 0, mFragments[0], mFragments[1], mFragments[2]);
        } else {
            mFragments[0] = findFragment(UnLoanFragment.class);
            mFragments[1] = findFragment(InformationFragment.class);
            mFragments[2] = findFragment(MineFragment.class);
        }
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }
    @OnClick(R.id.id_textview_tab_loan)
    public void click() {

    }
}
