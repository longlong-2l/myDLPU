package com.surpassli.www.myapp.support.utils;


import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by SurpassLi on 2017/1/16.
 */
public class HttpUtil {
    /**
     * Get方法
     * @param url
     * @param callback
     */
    public static void sendGetOkhttp(String url,okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request res = new Request.Builder().url(url).build();
        client.newCall(res).enqueue(callback);
    }
}
