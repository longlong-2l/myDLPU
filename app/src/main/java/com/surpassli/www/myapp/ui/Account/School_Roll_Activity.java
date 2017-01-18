package com.surpassli.www.myapp.ui.Account;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;

import com.surpassli.www.myapp.AppVariables;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.databinding.ActivitySchoolRollBinding;
import com.surpassli.www.myapp.gson.Person_School_Roll;
import com.surpassli.www.myapp.support.utils.HttpUtil;
import com.surpassli.www.myapp.support.utils.MD5.MD5;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by SurpassLi on 2017/1/17.
 */
public class School_Roll_Activity extends Activity {
    private ActivitySchoolRollBinding School_roll_binding;
    private static final String TAG = "School_Roll_Activity";
    private long resultTime;
    private String sign;
    private long mytime;
    private Person_School_Roll person_school_roll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        School_roll_binding = DataBindingUtil.setContentView(School_Roll_Activity.this,R.layout.activity_school_roll);
//        getNetTime();
        getData();
    }

    private void getNetTime() {
        HttpUtil.sendGetOkhttp(AppApi.TIME, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: " + "获取系统授时失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "onResponse: " + "获取系统授时成功");
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

    private void getData() {
        mytime = System.currentTimeMillis() / 1000;//获取系统时间的10位的时间戳
        resultTime = AppVariables.time - mytime;
        String timestamp = String.valueOf(mytime + resultTime);
        sign = MD5.getMd5(AppVariables.key + AppVariables.token + timestamp);
        Log.i(TAG, "getData: " + AppApi.MY_ACCOUNT + "userId=" + AppVariables.userId + "&sign=" + sign + "&timestamp=" + timestamp);
        HttpUtil.sendGetOkhttp_header(AppApi.MY_ACCOUNT + "userId=" + AppVariables.userId + "&sign=" + sign + "&timestamp=" + timestamp, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "MyFragment_onFailure: " + "获取数据失败：" + e.getMessage().toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "MyFragment_onResponse: " + "获取数据成功");
                String data = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    person_school_roll = new Person_School_Roll(jsonObject);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            initView();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void initView(){
        School_roll_binding.tvAcademy.setText(person_school_roll.getAcademy());
        School_roll_binding.tvMajor.setText(person_school_roll.getMajor());
        School_roll_binding.tvEducationalSystem.setText(person_school_roll.getEducational_system());
        School_roll_binding.tvGrades.setText(person_school_roll.getGrades());
        School_roll_binding.tvStudentId.setText("学号：" + person_school_roll.getStudent_id());
        School_roll_binding.tvName.setText("姓名：" + person_school_roll.getname());
        School_roll_binding.tvSex.setText("性别：" + person_school_roll.getSex());
        School_roll_binding.tvNamePinyin.setText("姓名拼音：" + person_school_roll.getName_pinyin());
        School_roll_binding.tvBirthday.setText("出生日期：" + person_school_roll.getBirthday());
        School_roll_binding.tvIntoSchoolDate.setText("入学日期：" + person_school_roll.getIntoSchool_date());
        School_roll_binding.tvIntoSchoolNum.setText("入学考号：" + person_school_roll.getIntoSchool_num());
        School_roll_binding.tvIdcard.setText("身份证编号：" + person_school_roll.getIdcard());
    }
}
