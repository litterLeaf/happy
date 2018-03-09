package com.yinshan.happycash.widget.logger;



public interface BaseLogStrategy {
    void i(String tag, String message);

    void e(String tag, String message);

    void d(String tag, String message);

    void v(String tag, String message);

    void w(String tag, String message);
}
