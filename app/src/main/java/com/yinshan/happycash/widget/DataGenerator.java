package com.yinshan.happycash.widget;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.application.AppApplication;

/**
 * Created by admin on 2018/3/20.
 */

public class DataGenerator {
    public static final int []mTabRes = new int[]{R.mipmap.tb_loan,R.mipmap.tb_info,R.mipmap.tb_me,R.mipmap.tb_chat};
    public static final int []mTabResPressed = new int[]{R.mipmap.tb_loan_select,R.mipmap.tb_info_select,
            R.mipmap.tb_me_select,R.mipmap.tb_chat_select};
    public static final String []mTabTitle = new String[]{
            AppApplication.appContext.getString(R.string.textview_loan),
            AppApplication.appContext.getString(R.string.textview_certification),
            AppApplication.appContext.getString(R.string.textview_me),
            AppApplication.appContext.getString(R.string.textview_online_QA),};
    /**
     * 获取Tab 显示的内容
     * @param context
     * @param position
     * @return
     */
    public static View getTabView(Context context, int position){
        View view = LayoutInflater.from(context).inflate(R.layout.tab_content_layout,null);
        ImageView tabIcon =  view.findViewById(R.id.tab_content_image);
        tabIcon.setImageResource(DataGenerator.mTabRes[position]);
        TextView tabText = view.findViewById(R.id.tab_content_text);
        tabText.setText(mTabTitle[position]);
        return view;
    }
}