package com.yinshan.happycash.framework;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;


import com.yinshan.happycash.utils.SPKeyUtils;
import com.yinshan.happycash.view.login.LoginActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

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


public abstract class BaseFragment extends RxSupportFragment  {


    private static final int LOGIN_INTERCEPT = 1005;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(bindLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    protected abstract void initView();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * [绑定布局]
     *
     * @return
     */
    protected abstract int bindLayout();

    protected void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public  void mStartActivity( Class<?> cls){
        Intent intent = new Intent(getActivity(),cls);
        startActivity(intent);
    }
    public  void mStartActivity( Class<?> cls,Bundle bundle){
        Intent intent = new Intent(getActivity(),cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void changeToForResult(Class cls,int requestCode){
        changeToForResult(cls,requestCode,false);
    }

    public void changeToForResult(Class cls,int requestCode,boolean checkLogin){
        Intent intent = new Intent(getActivity(),cls);
        if(checkLogin&&!isLogin()){
            intent.putExtra(SPKeyUtils.REQUEST_CODE,requestCode);
            changeTo(intent,true);
        }else{
            startActivityForResult(intent,requestCode);
        }
    }

    /**
     * 检查登录状态的跳转，登录则跳转，否则留在本页
     * @param intent
     * @param checkLogin 是否检查登录状态
     */
    public void changeTo(Intent intent,boolean checkLogin){
        if (checkLogin) {
            if (!isLogin()) {
                Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
                loginIntent.putExtra(SPKeyUtils.TEMP_INTENT,intent);
                startActivityForResult(loginIntent,LOGIN_INTERCEPT);
            }else{
                startActivity(intent);
            }
        }else {
            startActivity(intent);
        }

    }

    public boolean isLogin(){
        return TokenManager.getInstance().hasLogin();
    }
}
