package com.surpassli.www.myapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.databinding.FragmentMyBinding;
import com.surpassli.www.myapp.support.utils.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by dell on 2017/1/6.
 */
public class MyFragment extends Fragment {

    private final static String TAG = "MyFragment";
    private FragmentMyBinding myBinding;
    private View view;
    private int time;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my,container,false);
        getNetTime();
        return view;
    }

    private void getNetTime() {
        HttpUtil.sendGetOkhttp("https://dlpu-aao-api.xu42.cn/v1/time", new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: "+"获取时间失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "onResponse: "+"获取时间成功");
                String result = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if("Success".equals(jsonObject.getString("message"))){
                        time = jsonObject.getInt("time");
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
        getData();
    }

    private void getData() {
//            HttpUtil.sendGetOkhttp();
    }

    public String getTime(){

        long time=System.currentTimeMillis()/1000;//获取系统时间的10位的时间戳

        String  str=String.valueOf(time);

        return str;

    }
}
