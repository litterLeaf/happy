package com.yinshan.happycash.widget.logger;

import android.content.Context;

import com.yinshan.happycash.utils.DebugUtil;


public class LogUtil {
    private boolean LOG = false;
    private BaseLogStrategy baseLogStrategy;
    private volatile static LogUtil instance;
    private String defaultTag = "";

    public void init(Context ctx) {
        baseLogStrategy = new LoggerLogStrategy();
        if (DebugUtil.isApkInDebug(ctx)) {
            LOG = true;
        } else {
            LOG = false;
        }
    }


    public void initTag(String tag) {
        defaultTag = tag;
    }

    public static LogUtil getInstance() {
        if (instance == null) {
            synchronized (LogUtil.class) {
                if (instance == null)
                    instance = new LogUtil();
            }
        }
        return instance;
    }

    public void setStrategy(BaseLogStrategy strategy) {
        baseLogStrategy = strategy;
    }

    public void i(String tag, String message) {
        if (LOG) baseLogStrategy.i(tag, message);
    }

    public void e(String tag, String message) {
        if (LOG) baseLogStrategy.e(tag, message);
    }

    public void d(String tag, String message) {
        if (LOG) baseLogStrategy.d(tag, message);
    }

    public void v(String tag, String message) {
        if (LOG) baseLogStrategy.v(tag, message);
    }

    public void w(String tag, String message) {
        if (LOG) baseLogStrategy.w(tag, message);
    }

    public void i(String message) {
        if (LOG) baseLogStrategy.i(defaultTag, message);
    }

    public void e(String message) {
        if (LOG) baseLogStrategy.e(defaultTag, message);
    }

    public void d(String message) {
        if (LOG) baseLogStrategy.d(defaultTag, message);
    }

    public void v(String message) {
        if (LOG) baseLogStrategy.v(defaultTag, message);
    }

    public void w(String message) {
        if (LOG) baseLogStrategy.w(defaultTag, message);
    }
}