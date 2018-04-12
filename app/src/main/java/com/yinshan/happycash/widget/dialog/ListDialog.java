package com.yinshan.happycash.widget.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.utils.ScreenUtils;

import java.util.List;

/**
 * Created by huxin on 2018/4/12.
 */

public abstract class ListDialog extends Dialog{

    Context mContext;
    List<String> mList;

    public ListDialog(@NonNull Context context, int themeResId,List<String> list) {
        super(context);
        mContext = context;
        mList = list;
        init();
    }

    private void init(){
        View view = View.inflate(mContext,R.layout.dialog_list,null);
        setContentView(view);
        ListView listView = (ListView) view.findViewById(R.id.listView);
        ListDialogAdapter adapter = new ListDialogAdapter(mContext,mList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dismiss();
                clickIndex(position);
            }
        });
    }

    public abstract void clickIndex(int index);
}
