package com.yinshan.happycash.framework;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListAdapter;
import android.widget.ListView;


import com.yinshan.happycash.R;
import com.yinshan.happycash.analytic.event.MobAgent;
import com.yinshan.happycash.analytic.event.MobEvent;
import com.yinshan.happycash.application.AppManager;
import com.yinshan.happycash.network.api.UserApi;
import com.yinshan.happycash.network.common.RxHttpUtils;
import com.yinshan.happycash.utils.LoggerWrapper;
import com.yinshan.happycash.utils.SPKeyUtils;
import com.yinshan.happycash.utils.SPUtils;
import com.yinshan.happycash.utils.ToastUtils;
import com.yinshan.happycash.view.login.LoginActivity;
import com.yinshan.happycash.widget.inter.IBaseView;
import com.yinshan.happycash.widget.userdefined.BandaEditText;
import com.yinshan.happycash.widget.userdefined.GoEditTextListener;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

public abstract class BaseActivity extends RxSupportActivity implements IBaseView{
    /**
     * 当前Activity渲染的视图View
     **/
    private View mContextView = null;
    protected final String TAG = this.getClass().getSimpleName();
    private Unbinder unbinder;

    private AlertDialog alertDialog;

    /**
     * 是否处理请求返回的数据（避免页面destory后请求返回的数据刷新ui导致crash）
     */
    protected boolean shouldHandleResponseData = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);


        if (mContextView == null) {
            mContextView = LayoutInflater.from(this).inflate(bindLayout(), null);
        }
        setContentView(mContextView);
        secondLayout();

        ButterKnife.bind(this);
        unbinder = ButterKnife.bind(this, mContextView);
        initView(mContextView, savedInstanceState);

        secondInit();

        AppManager.getInstance().addActivity(this);
    }

    /**
     * [初始化控件]
     *
     * @param view
     * @param savedInstanceState
     */
    protected abstract void initView(final View view, Bundle savedInstanceState);
    /**
     * [绑定布局]
     *
     * @return
     */
    protected abstract int bindLayout();

    //子页面的Layout
    protected abstract void secondLayout();

    //子页面的INIT
    protected abstract void secondInit();


    @CallSuper
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null)
            unbinder.unbind();
        shouldHandleResponseData = false;
    }

    @Override
    public void finish() {
        super.finish();
        exitAnimtion();
    }
    /**
     * 默认退出动画 由于大部分页面是右进左出 而如果有的页面是特殊进入动画 则只需要重写这个方法为空即可
     */
    public void exitAnimtion() {
        //动画
//        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
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
    public  void mStartActivity(Context packageContext, Class<?> cls){
        Intent intent = new Intent(packageContext,cls);
        startActivity(intent);
    }
    public  void mStartActivity(Context packageContext, Class<?> cls,Bundle bundle){
        Intent intent = new Intent(packageContext,cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void dismissLoadingDialog() {
        if (null != alertDialog && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }

    @Override
    public void showLoadingDialog() {
        if(alertDialog!=null&&alertDialog.isShowing()){
            return;
        }
        if(alertDialog==null){
            alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable());
            alertDialog.setCancelable(true);
            alertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_SEARCH || keyCode == KeyEvent.KEYCODE_BACK)
                        return true;
                    return false;
                }
            });
        }
        alertDialog.show();
        alertDialog.setContentView(R.layout.loading_alert);
        alertDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public boolean isShouldHandleResponseData() {
        return shouldHandleResponseData;
    }

    public void onFocusChange(View view,final String i){
        view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    String mobAgent=i+ MobEvent.START;
                    MobAgent.onEvent(mobAgent);
                } else {
                    String mobAgent=i+MobEvent.END;
                    MobAgent.onEvent(mobAgent);
                }
            }
        });
    }

    public void onPasteListener(BandaEditText view, final String i){
        view.addListener(new GoEditTextListener() {
            @Override
            public void onPaste() {
                String mobAgent=MobEvent.PASTE+i;
                MobAgent.onEvent(mobAgent);
            }
        });
    }
    /**
     * 注入消息
     */
    public void inputMessage(){
//        Message message = Message.createReceiveMessage(Message.Type.TXT);
//        //从本地获取保存的string
//        String str = SPUtils.get("rob_welcome","");
//        EMTextMessageBody body = null;
//        if(!TextUtils.isEmpty(str)){
//            if(!isRobotMenu(str)){
//                //文字消息直接去设置给消息
//                body = new EMTextMessageBody(str);
//            }else{
//                //菜单消息需要设置给消息扩展
//                try{
//                    body = new EMTextMessageBody("");
//                    JSONObject msgtype = new JSONObject(str);
//                    message.setAttribute("msgtype",msgtype);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//            message.setFrom(SPKeyUtils.IMSERVICE);
//            message.addBody(body);
//            message.setMsgTime(System.currentTimeMillis());
//            message.setStatus(Message.Status.SUCCESS);
//            message.setMsgId(UUID.randomUUID().toString());
//            ChatClient.getInstance().chatManager().saveMessage(message);
//        }
    }
    private boolean isRobotMenu(String str){
        try {
            JSONObject json = new JSONObject(str);
            JSONObject obj = json.getJSONObject("choice");
        }catch (Exception e){
            return false;
        }
        return true;
    }

}
