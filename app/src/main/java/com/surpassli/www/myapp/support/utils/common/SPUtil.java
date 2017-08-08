package com.surpassli.www.myapp.support.utils.common;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences工具
 * Created by lilong on 2016/2/7.
 */
public class SPUtil {
    private Context context;

    public SPUtil(Context context) {
        super();
        this.context = context;
    }

    /**
     * 初始化SharedPreferences对象
     *
     * create at 2016/2/7 14:26
     */
    private SharedPreferences initSP(String spName) {
        return context.getSharedPreferences(spName, 0);
    }

    /**
     * 获取int型Sharedpreferences
     *
     * create at 2016/2/7 14:27
     */
    public int getIntSP(String spName, String spKey) {
        SharedPreferences sp = initSP(spName);
        return sp.getInt(spKey, 0);
    }

    /**
     * 获取long型Sharedpreferences
     *
     * create at 2016/2/7 14:27
     */
    public long getLongSP(String spName, String spKey) {
        SharedPreferences sp = initSP(spName);
        return sp.getLong(spKey, 0);
    }

    /**
     * 获取float型Sharedpreferences
     *
     * create at 2016/2/7 14:27
     */
    public float getFloatSP(String spName, String spKey) {
        SharedPreferences sp = initSP(spName);
        return sp.getFloat(spKey, 0);
    }

    /**
     * 获取boolean型Sharedpreferences
     *
     * create at 2016/2/7 14:27
     */
    public boolean getBooleanSP(String spName, String spKey) {
        SharedPreferences sp = initSP(spName);
        return sp.getBoolean(spKey, false);
    }

    /**
     * 获取String型SharedPreferences
     *
     * create at 2016/2/7 14:55
     */
    public String getStringSP(String spName, String spKey) {
        SharedPreferences sp = initSP(spName);
        return sp.getString(spKey, null);
    }

    /**
     * 填充int型SharedPreferences
     *
     * create at 2016/2/7 14:59
     */
    public void putIntSP(String spName, String spKey, int value) {
        SharedPreferences sp = initSP(spName);
        SharedPreferences.Editor ed = sp.edit();
        ed.putInt(spKey, value);
        ed.apply();
    }

    /**
     * 填充float型SharedPreferences
     *
     * create at 2016/2/7 14:59
     */
    public void putFloatSP(String spName, String spKey, float value) {
        SharedPreferences sp = initSP(spName);
        SharedPreferences.Editor ed = sp.edit();
        ed.putFloat(spKey, value);
        ed.apply();
    }

    /**
     * 填充boolean型SharedPreferences
     *
     * create at 2016/2/7 14:59
     */
    public void putBooleanSP(String spName, String spKey, boolean value) {
        SharedPreferences sp = initSP(spName);
        SharedPreferences.Editor ed = sp.edit();
        ed.putBoolean(spKey, value);
        ed.apply();
    }

    /**
     * 填充String型SharedPreferences
     *
     * create at 2016/2/7 14:59
     */
    public void putStringSP(String spName, String spKey, String value) {
        SharedPreferences sp = initSP(spName);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString(spKey, value);
        ed.apply();
    }

    /**
     * 填充long型SharedPreferences
     *
     * create at 2016/2/7 14:59
     */
    public void putLongSP(String spName, String spKey, long value) {
        SharedPreferences sp = initSP(spName);
        SharedPreferences.Editor ed = sp.edit();
        ed.putLong(spKey, value);
        ed.apply();
    }

    /**
    *清空sp文件
    *create at 2016/2/15 22:25
    */
    public void clearSP(String spName){
        SharedPreferences sp = initSP(spName);
        SharedPreferences.Editor ed = sp.edit();
        ed.clear();
        ed.apply();
    }
}
