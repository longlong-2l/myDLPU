package com.surpassli.www.myapp.ui.Account;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.surpassli.www.myapp.AppVariables;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.adapter.Course_Adapter;
import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.gson.Course_Result_bean;
import com.surpassli.www.myapp.support.utils.HttpUtil;
import com.surpassli.www.myapp.support.utils.MD5.MD5;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by SurpassLi on 2017/1/17.
 */
public class Course_Result_Activity extends Activity {
    //    private RecyclerView mRecycleView;
    private ListView lv_course;
    private List<Course_Result_bean> mCourse_Result_list;
    //    private Course_Result_Adapter mCourse_result_adapter;
    private Course_Adapter course_adapter;
    private long mytime;
    private long resultTime;
    private static final String TAG = "Course_Result_Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_result);
        initView();
    }

    private void initView() {
//        mRecycleView = (RecyclerView) findViewById(R.id.rv_course_recycleView);
        lv_course = (ListView) findViewById(R.id.lv_course);
        mCourse_Result_list = new ArrayList<Course_Result_bean>();
        mytime = System.currentTimeMillis() / 1000;//获取系统时间的10位的时间戳
        resultTime = AppVariables.time - mytime;
        String timestamp = String.valueOf(mytime + resultTime);
        AppVariables.sign = MD5.getMd5(AppVariables.key + AppVariables.token + timestamp);
        HttpUtil.sendGetOkhttp_header(AppApi.MY_COURSE + "userId=" + AppVariables.userId + "&sign=" + AppVariables.sign + "&timestamp=" + timestamp, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: " + "获取课程信息返回数据失败：" + e.getMessage().toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "onResponse: " + "获取课程信息返回数据成功");
                String res = response.body().string();
                Log.i(TAG, "课程信息: " + res);
                Course_Result_bean course_result_bean;
                try {
                    course_result_bean = new Course_Result_bean();
                    JSONObject jsonObject = new JSONObject(res);
                    JSONArray jsonArray1;
                    if (jsonObject.getString("message").equals("Success")) {
                        Log.i(TAG, "onResponse: " + "获取课程正确信息");
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            jsonArray1 = jsonArray.getJSONArray(i);
                            course_result_bean.num = jsonArray1.getString(0);
                            course_result_bean.No1_date = jsonArray1.getString(1);
                            course_result_bean.course = jsonArray1.getString(2);
                            course_result_bean.course_name = jsonArray1.getString(3);
                            course_result_bean.course_score = jsonArray1.getString(4);
                            course_result_bean.score_flag = jsonArray1.getString(5);
                            course_result_bean.course_credit = jsonArray1.getString(6);
                            course_result_bean.course_period = jsonArray1.getString(7);
                            course_result_bean.exam_type = jsonArray1.getString(8);
                            course_result_bean.course_property = jsonArray1.getString(9);
                            course_result_bean.course_nature = jsonArray1.getString(10);
                            course_result_bean.exam_nature = jsonArray1.getString(11);
                            course_result_bean.again_term = jsonArray1.getString(12);
//                            Course_Result_bean course_result_bean = new Course_Result_bean(jsonArray,i);
                            mCourse_Result_list.add(course_result_bean);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
//        mCourse_result_adapter = new Course_Result_Adapter(this,mCourse_Result_list);
//        mRecycleView.setAdapter(mCourse_result_adapter);
        course_adapter = new Course_Adapter(Course_Result_Activity.this, mCourse_Result_list);
        lv_course.setAdapter(course_adapter);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);//设置RecycleView 以ListView的方式显示
//        mRecycleView.setLayoutManager(layoutManager);
//        mRecycleView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));//设置分割线
//        mRecycleView.setItemAnimator(new DefaultItemAnimator());//设置动画效果为默认动画
    }
}
