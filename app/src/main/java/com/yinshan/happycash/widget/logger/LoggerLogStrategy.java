package com.yinshan.happycash.widget.logger;

import com.orhanobut.logger.Logger;

public class LoggerLogStrategy implements BaseLogStrategy {

    public LoggerLogStrategy() {
        Logger.init().methodCount(3);
    }

    @Override
    public void i(String tag, String message) {
        Logger.t(tag).d(message);
    }

    @Override
    public void e(String tag, String message) {
        Logger.t(tag).e(message);//只有e才能输出null
    }

    @Override
    public void d(String tag, String message) {
        Logger.t(tag).d(message);
    }

    @Override
    public void v(String tag, String message) {
        Logger.t(tag).v(message);
    }

    @Override
    public void w(String tag, String message) {
        Logger.t(tag).w(message);
    }
}
