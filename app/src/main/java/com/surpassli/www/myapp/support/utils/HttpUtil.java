package com.surpassli.www.myapp.support.utils;


import com.surpassli.www.myapp.AppVariables;

import java.io.IOException;

import okhttp3.FormBody;
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

    /**
     * 只带有key的header
     * @param url
     * @param callback
     */
    public static void sendGetOkHttp_header(String url,okhttp3.Callback callback){
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

    /**
     * 带有key、semester、week的header,查课程表
     * @param url
     * @param callback
     */
    public static void sendGetOkHttp_header_swk(String semester,String url,okhttp3.Callback callback){
        OkHttpClient client = genericClient_swk(semester);
        Request res = new Request.Builder().url(url).build();
        client.newCall(res).enqueue(callback);
    }
    public static OkHttpClient genericClient_swk(final String semester) {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("semester", semester)
                                .addHeader("week", "2")
                                .addHeader("key", AppVariables.key)
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();
        return httpClient;
    }

    /**
     * 带有key、semester的header,用于课程成绩查询，考试安排
     * @param url
     * @param callback
     */
    public static void sendGetOkHttp_header_sk(String url,okhttp3.Callback callback){
        OkHttpClient client = genericClient_sk();
        Request res = new Request.Builder().url(url).build();
        client.newCall(res).enqueue(callback);
    }

    public static OkHttpClient genericClient_sk() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("semester", "2015-2016-2")
                                .addHeader("key", AppVariables.key)
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();
        return httpClient;
    }

    public static void postChangePassWd(String url, String sign, String timestamp, String newpasswd,okhttp3.Callback callback) {
        OkHttpClient okHttpClient = genericClient_nk(newpasswd);
        FormBody.Builder formBodyBuild = new FormBody.Builder();
        formBodyBuild.add("userId", String.valueOf(AppVariables.userId));
        formBodyBuild.add("sign", sign);
        formBodyBuild.add("timestamp", timestamp);
        Request request = new Request.Builder().url(url).post(formBodyBuild.build()).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public static OkHttpClient genericClient_nk(final String newpasswd){
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("new", newpasswd)
                                .addHeader("key", AppVariables.key)
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();
        return httpClient;
    }
}
