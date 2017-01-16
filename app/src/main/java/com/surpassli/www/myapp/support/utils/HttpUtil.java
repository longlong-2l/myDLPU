package com.surpassli.www.myapp.support.utils;


import com.surpassli.www.myapp.AppVariables;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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

    public static void sendGetOkhttp_header(String url,okhttp3.Callback callback){
        OkHttpClient client = genericClient();
        Request res = new Request.Builder().url(url).build();
        client.newCall(res).enqueue(callback);
    }

    public static OkHttpClient genericClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("key", AppVariables.key)
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();
        return httpClient;
    }
}
