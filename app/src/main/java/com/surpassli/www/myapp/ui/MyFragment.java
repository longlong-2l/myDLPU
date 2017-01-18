package com.surpassli.www.myapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.surpassli.www.myapp.AppVariables;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.databinding.FragmentMyBinding;
import com.surpassli.www.myapp.support.utils.HttpUtil;
import com.surpassli.www.myapp.support.utils.MD5.MD5;
import com.surpassli.www.myapp.ui.Account.Course_Result_Activity;
import com.surpassli.www.myapp.ui.Account.School_Roll_Activity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by dell on 2017/1/6.
 */
public class MyFragment extends Fragment implements View.OnClickListener{

//    private final static String TAG = "MyFragment";
//    private FragmentMyBinding myBinding;
    private View view;
//    private long resultTime;
//    private String sign;
//    private long mytime;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, container, false);
        initView();
        return view;
    }

    private void initView() {
        view.findViewById(R.id.tv_xuejikapian).setOnClickListener(this);
        view.findViewById(R.id.tv_course_grade).setOnClickListener(this);
        view.findViewById(R.id.tv_level_grade).setOnClickListener(this);
        view.findViewById(R.id.tv_course_table).setOnClickListener(this);
        view.findViewById(R.id.tv_current_week).setOnClickListener(this);
        view.findViewById(R.id.tv_exam_plan).setOnClickListener(this);
        view.findViewById(R.id.tv_password_reset).setOnClickListener(this);
        view.findViewById(R.id.tv_login_out).setOnClickListener(this);
    }

//    private void getNetTime() {
//        HttpUtil.sendGetOkhttp("https://dlpu-aao-api.xu42.cn/v1/time", new okhttp3.Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.i(TAG, "onFailure: " + "获取系统授时失败");
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Log.i(TAG, "onResponse: " + "获取系统授时成功");
//                String result = response.body().string();
//                try {
//                    JSONObject jsonObject = new JSONObject(result);
//                    if ("Success".equals(jsonObject.getString("message"))) {
//                        AppVariables.time = jsonObject.getInt("time");
//                        Log.i(TAG, "AppVariables.time: " + AppVariables.time);
//                        getData();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

//    @Override
//    public void onResume() {
//        super.onResume();
////        getNetTime();
//    }

//    private void getData() {
//        mytime = System.currentTimeMillis() / 1000;//获取系统时间的10位的时间戳
//        resultTime = AppVariables.time - mytime;
//        String timestamp = String.valueOf(mytime + resultTime);
//        AppVariables.sign= MD5.getMd5(AppVariables.key + AppVariables.token + timestamp);
//        Log.i(TAG, "getData: " + AppApi.MY_ACCOUNT + "userId=" + AppVariables.userId + "&sign=" + AppVariables.sign + "&timestamp=" + timestamp);
//        HttpUtil.sendGetOkhttp_header(AppApi.MY_ACCOUNT + "userId=" + AppVariables.userId + "&sign=" + sign + "&timestamp=" + timestamp, new okhttp3.Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.i(TAG, "MyFragment_onFailure: " + "获取数据失败");
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Log.i(TAG, "MyFragment_onResponse: " + "获取数据成功");
//                Log.i(TAG, "onResponse: " + response.body().string());
//            }
//        });
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_xuejikapian:
                startActivity(new Intent(getActivity(), School_Roll_Activity.class));
                break;
            case R.id.tv_course_grade:
                startActivity( new Intent(getActivity(), Course_Result_Activity.class));
                break;
        }
    }
}
