package com.surpassli.www.myapp.support.utils.net.callback.callback;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.surpassli.www.myapp.support.AppException;
import com.surpassli.www.myapp.support.listener.DisposeDataHandle;
import com.surpassli.www.myapp.support.listener.DisposeDataListener;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by SurpassLi on 2017/1/10.
 */
public class CommonJsonCallBack implements Callback {
    protected final String RESULT_CODE = "ecode";
    protected final String ERROR_MSG = "emsg";
    protected final int RESULT_CODE_VALUE = 0;
    protected final String EMPTY_MSG="";

    private DisposeDataListener mListener;
    private Class<?> mClass;
    private Handler mDelieverHandler;

    /**
     * the java layer exception, do not same to the logic error
     */
    protected final int NETWORK_ERROR = -1; // the network relative error
    protected final int JSON_ERROR = -2; // the JSON relative error
    protected final int OTHER_ERROR = -3; // the unknow error

    public CommonJsonCallBack(DisposeDataHandle handle){
        this.mListener = handle.mListener;
        this.mClass = handle.mClass;
        this.mDelieverHandler = new Handler(Looper.getMainLooper());//返回到主线程中
    }

//    @Override
//    public void onFailure(Request request, final IOException e) {
//        mDelieverHandler.post(new Runnable() {
//            @Override
//            public void run() {
//                mListener.onFailure(e);
//            }
//        });
//    }
//
//    @Override
//    public void onResponse(Response response) throws IOException {
//        //子线程中
//        final  String  result = response.body().string();
//        mDelieverHandler.post(new Runnable() {
//            @Override
//            public void run() {
//                handleResponse(result);
//            }
//        });
//    }
    private void handleResponse(String result) {
        if (result == null || "".equals(result)){
                mListener.onFailure(new AppException(NETWORK_ERROR,EMPTY_MSG));
        }
        try {
            JSONObject result2 = new JSONObject(result);
            if (result2.has(RESULT_CODE)) {
                if (result2.optInt(RESULT_CODE) == RESULT_CODE_VALUE) {
                    if (mClass == null) {
                        mListener.onSuccess(result);
                    } else {
                        Object obj = ResponseEntityToModule.parseJsonObjectToModule(result2, mClass);
                        if (obj != null) {
                            mListener.onSuccess(obj);
                        } else {
                            mListener.onFailure(new AppException(JSON_ERROR, EMPTY_MSG));
                        }
                    }
                } else {
                    if (result2.has(ERROR_MSG)) {
                        mListener.onFailure(
                                new AppException(result2.optInt(RESULT_CODE), result2.optString(ERROR_MSG)));
                    } else {
                        mListener.onFailure(new AppException(result2.optInt(RESULT_CODE), EMPTY_MSG));
                    }
                }
            } else {
                if (result2.has(ERROR_MSG)) {
                    mListener.onFailure(new AppException(OTHER_ERROR, result2.optString(ERROR_MSG)));
                }
            }
        } catch (Exception e) {
            mListener.onFailure(new AppException(OTHER_ERROR, e.getMessage()));
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call call, IOException e) {
        Log.i("TAG", "onFailure: "+ "获取数据失败");
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
//子线程中
        final  String  result = response.body().string();
        mDelieverHandler.post(new Runnable() {
            @Override
            public void run() {
                handleResponse(result);
            }
        });
    }
}

