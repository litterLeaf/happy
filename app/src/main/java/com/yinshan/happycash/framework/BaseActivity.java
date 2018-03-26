package com.yinshan.happycash.framework;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.yinshan.happycash.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

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

    private CompositeDisposable disposables2Stop;// 管理Stop取消订阅者者
    private CompositeDisposable disposables2Destroy;// 管理Destroy取消订阅者者

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

    @Override
    protected void onStart() {
        super.onStart();
        if (disposables2Stop != null) {
            throw new IllegalStateException("onStart called multiple times");
        }
        disposables2Stop = new CompositeDisposable();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (disposables2Stop == null) {
            throw new IllegalStateException("onStop called multiple times or onStart not called");
        }
        disposables2Stop.dispose();
        disposables2Stop = null;
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null)
            unbinder.unbind();
        if (disposables2Destroy == null) {
            throw new IllegalStateException( "onDestroy called multiple times or onCreate not called");
        }
        disposables2Destroy.dispose();
        disposables2Destroy = null;
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


    private <T> Observable<T> createData(final T t) {
        return Observable.create(subscriber -> {
            try {
                subscriber.onNext(t);
                subscriber.onComplete();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }

    public boolean addRxStop(Disposable disposable) {
        if (disposables2Stop == null) {
            throw new IllegalStateException("addUtilStop should be called between onStart and onStop");
        }
        disposables2Stop.add(disposable);
        return true;
    }

    public boolean addRxDestroy(Disposable disposable) {
        if (disposables2Destroy == null) {
            throw new IllegalStateException("addUtilDestroy should be called between onCreate and onDestroy");
        }
        disposables2Destroy.add(disposable);
        return true;
    }

    public void remove(Disposable disposable) {
        if (disposables2Stop == null && disposables2Destroy == null) {
            throw new IllegalStateException("remove should not be called after onDestroy");
        }
        if (disposables2Stop != null) {
            disposables2Stop.remove(disposable);
        }
        if (disposables2Destroy != null) {
            disposables2Destroy.remove(disposable);
        }
    }
}
