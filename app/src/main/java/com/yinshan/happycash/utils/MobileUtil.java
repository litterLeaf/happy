package com.yinshan.happycash.utils;

/**
 * Created by huxin on 2018/8/9.
 */
public class MobileUtil {

    public static String trimMobile(String mobile) {
        String _mobile = mobile.trim().replace("+", "");
        if (_mobile.startsWith("62")) {
            _mobile = _mobile.replaceFirst("62", "0");
        }
        if (!_mobile.startsWith("0")) {
            _mobile = "0" + _mobile;
        }
        return _mobile;
    }
}
