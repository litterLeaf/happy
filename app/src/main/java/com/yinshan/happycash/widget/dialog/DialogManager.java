package com.yinshan.happycash.widget.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.application.AppApplication;
import com.yinshan.happycash.utils.ScreenUtils;
import com.yinshan.happycash.widget.common.ToastManager;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by huxin on 2018/4/26.
 */

public class DialogManager {
    public static Dialog newDialog(Context context, View view) {
        Dialog dialog = new AlertDialog.Builder(context)
                .setView(view)
                .setCancelable(true)
                .create();
        if (!dialog.isShowing()) {
            dialog.show();
        }
        return dialog;
    }

    public static Dialog newDialog(Context context, View view, int themeResId) {
        Dialog dialog = new AlertDialog.Builder(context, themeResId)
                .setView(view)
                .setCancelable(true)
                .create();
        if (!dialog.isShowing()) {
            dialog.show();
        }
        return dialog;
    }

    private static Dialog createDialog(Context context, View view, float heightFloat) {
        AlertDialog  dialog = new AlertDialog.Builder(context)
                .setView(view)
                .setCancelable(true)
                .create();
        Window dialogWindow = dialog.getWindow();

        if (!dialog.isShowing()) {
            dialog.show();
        }
        dialog.setCanceledOnTouchOutside(true);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.height = (int) (ScreenUtils.getScreenHeight(context)*heightFloat);
        lp.width = ScreenUtils.getScreenWidth(context);
        dialogWindow.setAttributes(lp);

        return dialog;
    }

    public static void loanAgreementDialog(Context context,int type){
        if (!(context instanceof Activity)) {
            return;
        }
        View view = View.inflate(context, R.layout.dialog_show_agreement_dialog, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) (AppApplication.mScreenWidth * 0.8),
                (int) (AppApplication.mScreenHeight* 0.7));
        params.gravity = Gravity.CENTER;
        view.setLayoutParams(params);
        TextView title = view.findViewById(R.id.agreement_title);
        TextView content = view.findViewById(R.id.agreement_content);

        if(type==0){
            title.setText("Kebijakan privasi");
            content.setText(AppApplication.appContext.getResources().getString(R.string.loan_agreement_data_privacy));
        }else if(type==1) {
            title.setText("Syarat dan Ketentuan");
            content.setText(AppApplication.appContext.getResources().getString(R.string.loan_agreement_terms_conditions));
        }

        DialogManager.createDialog(context, view,0.8f);
    }

    public static DialogBuilder newListViewDialog(Context context) {
        return new DialogBuilder(context);
    }

    public static PopupWindow newAlertDialog(final Context context, int dialog_ensure_logout, final AlertDialogListener alertDialogListener) {
        View view = View.inflate(context, R.layout.dialog_alert, null);
        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        popupWindow.setContentView(view);
//        popupWindow.setWidth(getResources().getDisplayMetrics().widthPixels);
//        view.measure(0,0);
//        popupWindow.setHeight(view.getMeasuredHeight());
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setAnimationStyle(R.style.popupwindow_anim_style);
        view.findViewById(R.id.btn_dialog_check_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                alertDialogListener.onCancel();
            }
        });
        view.findViewById(R.id.btn_dialog_check_ensurn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                alertDialogListener.onEnsure();
            }
        });
        TextView textView = (TextView) view.findViewById(R.id.tv_dialog_check_text);
        textView.setText(context.getString(dialog_ensure_logout));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (context instanceof Activity) {
                    Activity activity = (Activity) context;
                    WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
                    attributes.alpha = 1.0f;
                    activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                    activity.getWindow().setAttributes(attributes);
                }
            }
        });
        return popupWindow;
    }


    public static void show(Context context,PopupWindow popupWindow,View token){
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
            attributes.alpha = 0.6f;
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            activity.getWindow().setAttributes(attributes);
        }
        popupWindow.showAtLocation(token, Gravity.BOTTOM, 0, 0);
//        popupWindow.update();
    }
    public static class DialogBuilder {
        private Context     mContext;
        private ListView    mListView;
        private View        mView;
        private boolean     isExpanded;
        private int         mGravity;
        private BaseAdapter mAdapter;
        private int         mHeader;

        public DialogBuilder(Context context) {
            mContext = context;
            mView = LayoutInflater.from(context).inflate(R.layout.dialog_list_view, null, false);
            mListView = (ListView) mView.findViewById(R.id.lv_dialog_view);
        }

        public DialogBuilder setContentHolder(Holder holder) {

            return this;
        }

        public DialogBuilder setExpanded(boolean isExpanded) {
            this.isExpanded = isExpanded;
            return this;
        }

        public DialogBuilder setAdapter(BaseAdapter adapter) {
            if (adapter == null) {
                throw new IllegalArgumentException("adapter cann't be null");
            }
            mAdapter = adapter;
            mListView.setAdapter(adapter);
            return this;
        }

        public DialogBuilder setGravity(int gravity) {
            this.mGravity = gravity;
            return this;
        }

        public Dialog create() {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, 0);
            if (mAdapter.getCount() <= 0) {
                params.width = (int) (mContext.getResources().getDisplayMetrics().widthPixels * 0.6f);
                params.height = 1;
                params.gravity = mGravity;
            } else {
                View view = mAdapter.getView(0, null, mListView);
                view.measure(0, 0);
                params.width = view.getMeasuredWidth();
                int height = (view.getMeasuredHeight() + mListView.getDividerHeight()) * mAdapter.getCount();
                if (height > (int) (mContext.getResources().getDisplayMetrics().heightPixels * 0.6f) && isExpanded) {
                    params.height = (int) (mContext.getResources().getDisplayMetrics().heightPixels * 0.6f);
                } else {
                    params.height = height;
                }
                params.gravity = mGravity;
            }

            mView.setLayoutParams(params);
            if (mHeader != 0) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
                    mListView.addHeaderView(LayoutInflater.from(mContext).inflate(mHeader, mListView, false));
                }
            }
            Dialog dialog = new AlertDialog.Builder(mContext)
                    .setView(mView)
                    .setCancelable(true)
                    .create();
            return dialog;
        }

        public DialogBuilder setHeader(int header) {
            mHeader = header;
            return this;
        }
    }
}
