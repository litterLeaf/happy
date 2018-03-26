package com.yinshan.happycash.utils;

import javax.annotation.Nullable;

/**
 * Created by huxin on 2018/3/26.
 */

public class StringUtil {

    public StringUtil() {
    }

    public static String nullToEmpty(@Nullable final String string) {
        return string == null ? "" : string;
    }

    @Nullable
    public static String emptyToNull(@Nullable final String string) {
        return isNullOrEmpty(string) ? null : string;
    }

    public static boolean isNullOrEmpty(@Nullable final String string) {
        return (string == null) || (string.length() == 0);
    }
}
