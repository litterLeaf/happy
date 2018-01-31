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
import com.yinshan.happycash.widget.BottomBar;
import com.yinshan.happycash.widget.BottomBarTab;

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


    @BindView(R.id.bottom_bar)
    BottomBar mBottomBar;
    @BindView(R.id.id_fragment_loan)
    FrameLayout fragmentLoan;
    private SupportFragment[] mFragments = new SupportFragment[4];
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        SupportFragment firstFragment = findFragment(UnLoanFragment.class);
        if (savedInstanceState == null) {
            mFragments[0] = UnLoanFragment.newInstance("unLoan", "");
            mFragments[1] = InformationFragment.newInstance("information", "");
            mFragments[2] = MineFragment.newInstance("mine", "");
            mFragments[3] = HotLineFragment.newInstance("online", "");
            loadMultipleRootFragment(R.id.id_fragment_loan, 0, mFragments[0], mFragments[1], mFragments[2],mFragments[3]);
        } else {
            mFragments[0] =firstFragment;
            mFragments[1] = findFragment(InformationFragment.class);
            mFragments[2] = findFragment(MineFragment.class);
            mFragments[3] = findFragment(HotLineFragment.class);
        }
        mBottomBar
                .addItem(new BottomBarTab(this, R.drawable.ic_tab_loan_selector, getString(R.string.textview_loan)))
                .addItem(new BottomBarTab(this, R.drawable.ic_tab_cerification_selector, getString(R.string.textview_certification)))
                .addItem(new BottomBarTab(this, R.drawable.ic_tab_me_selector, getString(R.string.textview_me)))
                .addItem(new BottomBarTab(this, R.drawable.ic_tab_onlineqa_serector, getString(R.string.textview_online_QA)));

        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                if(prePosition==3){
                    showHideFragment(mFragments[position], mFragments[prePosition]);
                }else {
                    showHideFragment(mFragments[position], mFragments[prePosition]);
                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
            }
        });
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }
}
