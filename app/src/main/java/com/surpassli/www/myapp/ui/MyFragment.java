package com.surpassli.www.myapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.surpassli.www.myapp.AppVariables;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.databinding.FragmentMyBinding;
import com.surpassli.www.myapp.gson.Person;
import com.surpassli.www.myapp.support.utils.HttpUtil;
import com.surpassli.www.myapp.support.utils.MD5.MD5;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dell on 2017/1/6.
 */
public class MyFragment extends Fragment {

    private final static String TAG = "MyFragment";
    private FragmentMyBinding myBinding;
    private View view;
    private long resultTime;
    private String sign;
    private long mytime;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, container, false);
//        getData();
        return view;
    }

    private void getNetTime() {
        HttpUtil.sendGetOkhttp("https://dlpu-aao-api.xu42.cn/v1/time", new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: " + "获取时间失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "onResponse: " + "获取时间成功");
                String result = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if ("Success".equals(jsonObject.getString("message"))) {
                        AppVariables.time = jsonObject.getInt("time");
                        Log.i(TAG, "AppVariables.time: " + AppVariables.time);
                        getData();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getNetTime();
    }

    private void getData() {
        mytime = System.currentTimeMillis() / 1000;//获取系统时间的10位的时间戳
        resultTime = AppVariables.time - mytime;
        Log.i(TAG, "time~~~~: " + AppVariables.time);
        Log.i(TAG, "mytime: " + mytime);
        Log.i(TAG, "resultTime: " + resultTime);
        Log.i(TAG, "getUserId: " + AppVariables.userId);
        Log.i(TAG, "getToken: " + AppVariables.token);
//        AppApi.MY_ACCOUNT + "userId= "+ AppVariables.userId + "&sign=" + sign + "&timestamp=" + mytime + resultTime
        try {
            String timestamp = String.valueOf(mytime) + String.valueOf(resultTime);
            Log.i(TAG, "timestamp: " + timestamp.toString());
            sign =  MD5.getMD5(AppVariables.key + AppVariables.token +timestamp);
            Log.i(TAG, "getData: " + AppApi.MY_ACCOUNT + "userId="+"1"+"&sign=" + sign + "&timestamp=" + mytime + resultTime);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        HttpUtil.sendGetOkhttp("https://dlpu-aao-api.xu42.cn/v1/user/courseScore?userId=2&sign=a0d0996f45c06161542b4ab5c66789ec&timestamp=1484556730", new okhttp3.Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                Log.i(TAG, "MyFragment_onFailure: " + "获取数据失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "MyFragment_onResponse: "+"获取数据成功");
                Log.i(TAG, "onResponse: "+response.body().string());
            }
        });
    }
}
