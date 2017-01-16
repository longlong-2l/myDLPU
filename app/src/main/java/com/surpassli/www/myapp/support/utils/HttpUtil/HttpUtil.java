package com.surpassli.www.myapp.support.utils.HttpUtil;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by SurpassLi on 2017/1/15.
 */
public class HttpUtil {
    public static  void sendOkHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
