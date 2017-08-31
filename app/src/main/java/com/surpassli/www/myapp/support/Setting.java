package com.surpassli.www.myapp.support;

import android.content.Context;
import android.content.SharedPreferences;

import com.surpassli.www.myapp.InitApp;

/**
 * Created by SurpassLi on 2017/8/8.
 * Setting
 */

public class Setting {

    public static boolean isAutoRefresh = false;

    public static boolean isExitConfirm = true;

    public static boolean isLightMode = false;

    public static boolean needRecreate = true;

    public static boolean autoRefresh = false;

    public static final String  AUTO_REFRESH = "auto_refresh";

    public static final String EXIT_CONFIRM = "exit_confirm";

    public static final String LIGHT_MODE = "light_mode";

    private static final String XML_NAME = "settings";

    public static final String LANGUAGE = "language";

    private static SharedPreferences mPrefs;

    private static Setting mySetting;

    public static Setting getInstance() {
        if (mySetting == null) {
            synchronized (Setting.class) {
                if (mySetting == null) {
                    mySetting = new Setting(InitApp.AppContext);
                }
            }
        }
        return mySetting;
    }

    private Setting(Context context) {
        mPrefs = context.getSharedPreferences(XML_NAME, Context.MODE_PRIVATE);
    }

    public Setting PutBoolean(String key, boolean value){
        mPrefs.edit().putBoolean(key,value).apply();
        return this;
    }

    public boolean getBoolean(String key, boolean def){
        return mPrefs.getBoolean(key, def);
    }

    public Setting putInt(String key, int value) {
        mPrefs.edit().putInt(key, value).apply();
        return this;
    }

    public int getInt(String key, int defValue) {
        return mPrefs.getInt(key, defValue);
    }
}
