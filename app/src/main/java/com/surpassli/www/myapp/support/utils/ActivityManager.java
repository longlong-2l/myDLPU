package com.surpassli.www.myapp.support.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by SurpassLi on 2017/8/3.
 * Description: Manager Activity
 */

public class ActivityManager {
    private static Stack<Activity> activityStack;
    private static ActivityManager instance;

    private ActivityManager() {
    }

    public static synchronized ActivityManager getInstance() {
        if (instance == null) {
            synchronized (ActivityManager.class) {
                if (instance == null) {
                    instance = new ActivityManager();
                }
            }
        }
        return instance;
    }

    /**
     * 添加Activity
     * @param activity activity
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 结束某个Activity
     * @param activity activity
     */
    public void finishOneActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 结束指定类名的Activity
     * @param mClass class name
     */
    public void finishOneActiviyByClass(Class<?> mClass) {
        Activity removeActivity = null;
        if (activityStack != null) {
            if (activityStack.size() > 0) {
                for (Activity activity : activityStack) {
                    if (activity.getClass().equals(mClass)) {
                        removeActivity = activity;
                    }
                }
            }
        }
        finishOneActivity(removeActivity);
    }

    /**
     * 是否拥有这个Activity
     * @param mClass class name
     * @return boolean
     */
    public boolean isHasActivity(Class<?> mClass) {
        if (activityStack != null) {
            if (activityStack.size() > 0) {
                for (Activity activity : activityStack) {
                    if (activity.getClass().equals(mClass)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0; i < activityStack.size(); i++) {
            if (activityStack.get(i) != null) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }
}
