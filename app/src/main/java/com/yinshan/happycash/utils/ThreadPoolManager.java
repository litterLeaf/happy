package com.yinshan.happycash.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yyhuang on 2017/6/22  14:19.
 */

public class ThreadPoolManager {

    static ExecutorService fixedThreadPool = Executors.newCachedThreadPool();

    private ThreadPoolManager() {

    }

    public static ExecutorService getInstance() {
        if (fixedThreadPool == null) {
            fixedThreadPool = Executors.newCachedThreadPool();
        }
        return fixedThreadPool;
    }

    public void execute(Runnable runnable) {
        fixedThreadPool.execute(runnable);
    }

}
