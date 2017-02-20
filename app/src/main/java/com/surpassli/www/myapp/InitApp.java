package com.surpassli.www.myapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by SurpassLi on 2017/2/20.
 */

public class InitApp extends Application{
    public static Context AppContext;
    @Override
    public void onCreate() {
        super.onCreate();
        AppContext = getApplicationContext();
    }
}
