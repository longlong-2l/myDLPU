package com.surpassli.www.myapp.support.utils.net.callback.CommonHttpClient;

import com.surpassli.www.myapp.support.listener.DisposeDataHandle;
import com.surpassli.www.myapp.support.utils.net.callback.callback.CommonJsonCallBack;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

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
