package com.surpassli.www.myapp.ui.Account;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import com.surpassli.www.myapp.AppVariables;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.databinding.ActivityCourseTableBinding;
import com.surpassli.www.myapp.model.Course_Table.Course_Table_Bean;
import com.surpassli.www.myapp.support.adapter.CourseAdapter.Course_table_recyclerview;
import com.surpassli.www.myapp.support.utils.HttpUtil;
import com.surpassli.www.myapp.support.utils.MD5.MD5;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by SurpassLi on 2017/1/22.
 */
public class Course_Table_Activity extends Activity {
    private ArrayList<Course_Table_Bean> course_table_been_list;
    private ActivityCourseTableBinding myCTBinding;
    private static final String TAG = "Course_Table_Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myCTBinding = DataBindingUtil.setContentView(this, R.layout.activity_course_table);
    }

    public void getData() {
        course_table_been_list = new ArrayList<Course_Table_Bean>();
        long mytime = System.currentTimeMillis()/1000;
        String timestamp = String.valueOf(mytime + AppVariables.time_cha);
        AppVariables.sign = MD5.getMd5(AppVariables.key + AppVariables.token + timestamp);
        HttpUtil.sendGetOkhttp_header(AppApi.MY_COURSE_TABLE + "", new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: " + "网络有问题");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "onResponse: " + "连接课程表信息成功");
                String res = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(res);
                    if ("Success".equals(jsonObject.getString("message"))) {
                        Log.i(TAG, "onResponse: " + "访问课程表信息成功");
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        JSONArray jsonArray1;
                        Course_Table_Bean course_table_bean;
                        for (int i = 0; i < jsonArray.length(); i++) {
                            jsonArray1 = jsonArray.getJSONArray(i);
                            course_table_bean = new Course_Table_Bean();
                            course_table_bean.setCourse_name(jsonArray1.getString(0));
                            course_table_bean.setCourse_time(jsonArray1.getString(1));
                            course_table_bean.setCourse_address(jsonArray1.getString(2));
                            course_table_bean.setCourse_teacher(jsonArray1.getString(3));
                            course_table_been_list.add(course_table_bean);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Course_table_recyclerview adapter = new Course_table_recyclerview(course_table_been_list, Course_Table_Activity.this);
                                myCTBinding.rvCourseTable.setAdapter(adapter);
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
