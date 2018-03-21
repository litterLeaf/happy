package com.yinshan.happycash.view.main;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseActivity;
import com.yinshan.happycash.view.fragments.HotLineFragment;
import com.yinshan.happycash.view.fragments.InformationFragment;
import com.yinshan.happycash.view.fragments.LoanInProcessFragment;
import com.yinshan.happycash.view.fragments.LoaningFragment;
import com.yinshan.happycash.view.me.MeFragment;
import com.yinshan.happycash.view.unloan.UnLoanFragment;
import com.yinshan.happycash.widget.DataGenerator;

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
 * @author admin
 *         on 2018/1/11
 */
public class MainActivity extends BaseActivity  {

    @BindView(R.id.bottom_tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.fragment_container)
    FrameLayout homeContainer;

   private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private UnLoanFragment unLoanFrag;
    private LoaningFragment loaningFrag;

    private LoanInProcessFragment processFragment;
    private InformationFragment inforFragament;
    private MeFragment meFrag;

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void secondLayout() {

    }

    @Override
    protected void secondInit() {

    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        initTabLayout();
    }

    private void initTabLayout() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                onTabItemSelected(tab.getPosition());

                //改变Tab 状态
                for(int i=0;i< mTabLayout.getTabCount();i++){
                    View view = mTabLayout.getTabAt(i).getCustomView();
                    ImageView icon = view.findViewById(R.id.tab_content_image);
                    if(i == tab.getPosition()){ // 选中状态
                        icon.setImageResource(DataGenerator.mTabResPressed[i]);
                    }else{// 未选中状态
                        icon.setImageResource(DataGenerator.mTabRes[i]);
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        for(int i=0;i<4;i++){
            mTabLayout.addTab(mTabLayout.newTab().setCustomView(DataGenerator.getTabView(this,i)));
        }
    }

    private void onTabItemSelected(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new UnLoanFragment();
                break;
            case 1:
                fragment = new InformationFragment();
                break;
            case 2:
                fragment = new MeFragment();
                break;
            case 3:
                fragment =new HotLineFragment();
                break;
        }
        if(fragment!=null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
        }
    }

}
