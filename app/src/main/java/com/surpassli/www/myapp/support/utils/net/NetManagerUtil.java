package com.surpassli.www.myapp.support.utils.net;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;

/**
 * Created by SurpassLi on 2017/8/9.
 * NetManagerUtil
 */

public class NetManagerUtil {
    public static OkHttpClient netClient;
    private static Gson mGson;

    static {
        netClient = new OkHttpClient();
//        netClient.setFollowRedirects(false);
        mGson = new Gson();
    }
}
