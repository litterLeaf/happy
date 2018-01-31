package com.yinshan.happycash.framework;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;
import com.yinshan.happycash.R;

import javax.annotation.Nonnull;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import me.yokeyword.fragmentation.SupportActivity;

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

public abstract class BaseActivity extends RxSupportActivity {
    /**
     * 当前Activity渲染的视图View
     **/
    private View mContextView = null;
    protected final String TAG = this.getClass().getSimpleName();
    private Unbinder unbinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (mContextView == null) {
            mContextView = LayoutInflater.from(this).inflate(bindLayout(), null);
        }
        setContentView(mContextView);
        ButterKnife.bind(this);
        unbinder = ButterKnife.bind(this, mContextView);
        initView(mContextView, savedInstanceState);
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
    @CallSuper
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null)
            unbinder.unbind();
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
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }
}
