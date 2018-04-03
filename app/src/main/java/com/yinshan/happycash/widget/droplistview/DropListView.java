package com.yinshan.happycash.widget.droplistview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;

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

public class DropListView extends AppCompatTextView {
    private DrapListViewAdapter mAdapter;

    public DropListView(Context context) {
        this(context, null);
    }

    public DropListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }


    public DropListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    public DropListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs) {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAdapter != null) {
                    mAdapter.show();
                }
            }
        });

    }


    public void setAdapter(DrapListViewAdapter adapter) {
        if (adapter == null) {
            throw new IllegalArgumentException("adapter can't be null");
        }
        mAdapter = adapter;
    }

    public void setSelectedItem(int position) {
        if (mAdapter != null) {
            mAdapter.setSeletedItem(position);
        }
    }

    public String getSelectedItem() {
        if (mAdapter != null) {
            return mAdapter.getSelectedItem();
        }
        return null;
    }
}
