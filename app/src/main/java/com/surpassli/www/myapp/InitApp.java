package com.surpassli.www.myapp;

import android.content.Context;

import org.litepal.LitePalApplication;

/**
 * Created by SurpassLi on 2017/2/20.
 * InitApp
 */

public class InitApp extends LitePalApplication{
    public static Context AppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        AppContext = getApplicationContext();
    }
}
