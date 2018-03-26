package com.yinshan.happycash.framework;

import io.reactivex.ObservableTransformer;

/**
 * Created by HOME on 2016/8/9.
 */

public interface MvpBaseView {
    /**
     * RxSupportFragment/RxActivity 中方法,声明在view中 便于在mvp中的presenter里调用
     *
     * @param <T> T
     * @return
     */
//    <T> ObservableTransformer<T, T> bindToLifecycle();

//    /**
//     * 由于无法区分是fragment的event还是activity的暂时隐藏
//     * RxSupportFragment/RxActivity 中方法,声明在view中 便于在mvp中的presenter里调用
//     *
//     * @param <T> T
//     * @return
//     */
//    <T> ObservableTransformer<T, T> bindUntilEvent(ActivityEvent event);
//
//    <T> ObservableTransformer<T, T> bindUntilEvent(@NonNull FragmentEvent event);
}
