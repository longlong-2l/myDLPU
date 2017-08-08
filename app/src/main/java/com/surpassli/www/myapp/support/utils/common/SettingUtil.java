package com.surpassli.www.myapp.support.utils.common;

import android.content.Context;
import android.content.res.Configuration;

import com.surpassli.www.myapp.support.Setting;

import java.util.Locale;

/**
 * Created by SurpassLi on 2017/8/8.
 * SettingUtil
 */

public class SettingUtil {

    public static void changeLanguage(Context context, int language) {
        String myLanguage = null;
        String country = null;
        switch (language){
            case 1:
                myLanguage = "zh";
                country = "CN";
                break;
            default:
                myLanguage = "en";
                country = "US";
                break;
        }
        Locale locale = new Locale(myLanguage,country);
        Configuration configuration = context.getResources().getConfiguration();
        configuration.locale = locale;
        context.getApplicationContext().getResources().updateConfiguration(configuration,context.getResources().getDisplayMetrics());
    }

    public static int getCurrentLanguage(){
        int lang = Setting.getInstance().getInt(Setting.LANGUAGE ,-1);
        if (lang == -1){
            String language = Locale.getDefault().getLanguage();
            String country = Locale.getDefault().getCountry();
            if (language.equalsIgnoreCase("en")) {
                if (country.equalsIgnoreCase("US")) {
                    lang = 1;
                }
            }else{
                lang = 0;
            }
        }
        return lang;
    }
}
