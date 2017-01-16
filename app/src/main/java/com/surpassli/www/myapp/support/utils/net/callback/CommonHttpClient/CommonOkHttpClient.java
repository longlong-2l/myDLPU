package com.surpassli.www.myapp.support.utils.net.callback.CommonHttpClient;

import android.util.Log;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.support.listener.DisposeDataHandle;
import com.surpassli.www.myapp.support.utils.net.callback.callback.CommonJsonCallBack;

import java.io.IOException;

/**
 * Created by dell on 2017/1/10.
 */
public class CommonOkHttpClient {
    private static final int TIME_OUT = 30;
    private static OkHttpClient mOkHttpClient;

    static {
         mOkHttpClient = new OkHttpClient();
    }

    public static Call Get(Request request, DisposeDataHandle handle){
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallBack(handle));
        return call;
    }

}
