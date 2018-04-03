package com.yinshan.happycash.widget.droplistview;

import android.content.Context;
import android.support.v7.widget.ListPopupWindow;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;


import com.yinshan.happycash.R;

import java.util.List;

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
 *  on 2018/4/2
 *
 */

public class DrapListViewAdapter {

    private final List<String> mStrings;
    private final DropListView          mParentView;
    private final int                        mLayoutId;
    private final Context mContext;
    private ListPopupWindow mView;
//    private       AlertDialog                mAlertDialog;
    private ListAdapter mAdapter;
    private       int                        mSelectedPosition;
//    private       WindowManager              mWindowManager;
//    private       WindowManager.LayoutParams mParams;

    public DrapListViewAdapter(final DropListView view, int layoutId, final List<String> strs){
        if (view == null) {
            throw new IllegalArgumentException("view cann't be null");
        }
        mContext = view.getContext();
        mSelectedPosition = -1;
        mStrings = strs;
        mParentView = view;
        mLayoutId = 0;
        //mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        mView = new ListPopupWindow(mContext);
        mView.setAnchorView(mParentView);
        mView.setDropDownGravity(Gravity.BOTTOM);
        mView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSelectedPosition = position;
                mParentView.setText(mStrings.get(position));
                mView.dismiss();
            }
        });
        mAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return mStrings == null ? 0:mStrings.size();
            }

            @Override
            public String getItem(int position) {
                return mStrings.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(mContext).inflate(mLayoutId == 0 ? R.layout.item_default_banda_drop_list : mLayoutId, parent,false);
                }
                ((TextView)convertView).setText(mStrings.get(position));
                return convertView;
            }
        };
        mView.setAdapter(mAdapter);
    }
    public void show() {
        mView.show();
    }

    public void setSeletedItem(int seletedItem) {
        if(seletedItem < mStrings.size()){
            mSelectedPosition = seletedItem;
            mParentView.setText(mStrings.get(seletedItem));
        }
    }

    public String getSelectedItem() {
        if (mSelectedPosition != -1) {
            return mStrings.get(mSelectedPosition);
        }
        return null;
    }
}
