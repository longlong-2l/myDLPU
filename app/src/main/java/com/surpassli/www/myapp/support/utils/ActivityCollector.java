package com.surpassli.www.myapp.support.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SurpassLi on 2017/2/4.
 * 一个Activity的管理类，还没用上
 */
public class ActivityCollector {
    public static List<Activity> actvities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        actvities.add(activity);
    }

    public static void removeActivity(Activity activity){
        actvities.remove(activity);
    }
    public static void finishAll(){
        for(Activity activity:actvities){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
