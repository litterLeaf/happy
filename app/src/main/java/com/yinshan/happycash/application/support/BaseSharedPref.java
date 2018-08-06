package com.yinshan.happycash.application.support;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * Created by huxin on 2018/5/2.
 */

public class BaseSharedPref {

    private SharedPreferences mSP;

    public BaseSharedPref(Context context, String fileName, int mode) {
        mSP = context.getSharedPreferences(fileName, mode);
    }

    public void write(String key, String value) {
        SharedPreferences.Editor editor = mSP.edit();
        editor.putString(key, value);
        editor.apply();
    }
    public void write(String key, boolean value) {
        SharedPreferences.Editor editor = mSP.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }
    public void write(String key, int value) {
        SharedPreferences.Editor editor = mSP.edit();
        editor.putInt(key, value);
        editor.apply();
    }
    public void write(String key, long value) {
        SharedPreferences.Editor editor = mSP.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public void write(Map<String, Object> map) {
        SharedPreferences.Editor editor = mSP.edit();

        for (String key : map.keySet()) {
            Object object = map.get(key);
            if (object instanceof Integer) {
                editor.putInt(key, (int) object);
            } else if (object instanceof String) {
                editor.putString(key, (String) object);
            } else if (object instanceof Boolean) {
                editor.putBoolean(key, (Boolean) object);
            } else if (object instanceof Long) {
                editor.putLong(key, (Long) object);
            }
        }
        editor.apply();
    }

    public String readString(String key) {
        return mSP.getString(key, "");
    }

    public String readArrayString(String key) {
        return mSP.getString(key, "[]");
    }

    public boolean readBoolean(String key) {
        return mSP.getBoolean(key, false);
    }

    public boolean readBoolean(String key, boolean defaultValue) {
        return mSP.getBoolean(key, defaultValue);
    }
    public int readInt(String key) {
        return mSP.getInt(key, 0);
    }

    public int readInt(String key, int defVal) {
        return mSP.getInt(key, defVal);
    }

    public long readLong(String key) {
        return mSP.getLong(key, 0);
    }

    public void clear() {
        SharedPreferences.Editor editor = mSP.edit();
        editor.clear();
        editor.apply();
    }
}
