package com.surpassli.www.myapp;

import com.surpassli.www.myapp.support.utils.MD5.MD5;

/**
 * Created by dell on 2017/1/16.
 */
public class AppVariables {

    public static boolean isLogin = false;

    public static int userId;

    public static String token;

    public static String username;

    public static String key = "6e6809937b013e5522232329a816989e";

    public static String sign ;

    public static long time;

    public static void clear() {
        isLogin = false;
        userId = 0;
        token = "";
        username = "";
        time = 0;
    }
}
