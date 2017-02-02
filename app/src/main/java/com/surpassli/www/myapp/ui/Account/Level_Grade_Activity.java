package com.surpassli.www.myapp.ui.Account;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;

import com.surpassli.www.myapp.AppVariables;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.databinding.ActivityLevelGradeBinding;
import com.surpassli.www.myapp.model.Course_Table.Level_Grade;
import com.surpassli.www.myapp.support.adapter.CourseAdapter.Level_Grade_Adapter_listview;
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
 * Created by SurpassLi on 2017/1/20.
 */
public class Level_Grade_Activity extends Activity {

    private ArrayList<Level_Grade> myLevel_grades;
    private Level_Grade_Adapter_listview myCourse_table_adapter_listview;
    private ActivityLevelGradeBinding MyLevelGradeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyLevelGradeBinding = DataBindingUtil.setContentView(this, R.layout.activity_level_grade);
        getData();
    }

    private void getData() {
        myLevel_grades = new ArrayList<Level_Grade>();
        long mytime = System.currentTimeMillis() / 1000;//获取系统时间的10位的时间戳
        String timestamp = String.valueOf(mytime + AppVariables.time_cha);
        AppVariables.sign = MD5.getMd5(AppVariables.key + AppVariables.token + timestamp);
        Log.i("Level", "getData: " + AppApi.MY_ACCOUNT + "userId=" + AppVariables.userId + "&sign=" + AppVariables.sign  + "&timestamp=" + timestamp);
        HttpUtil.sendGetOkHttp_header(AppApi.MY_LEVEL_GRADE + "userId=" + AppVariables.userId + "&sign=" + AppVariables.sign + "&timestamp=" + timestamp, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("Level_Grade_Activity", "onFailure: " + "网络有问题~~");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                Level_Grade level_grade_bean;
                try {
                    JSONObject jsonObject = new JSONObject(res);
                    if ("Success".equals(jsonObject.getString("message"))) {
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONArray jsonArray1 = jsonArray.getJSONArray(i);
                            level_grade_bean = new Level_Grade();
                            level_grade_bean.setOrder_number(jsonArray1.getString(0));
                            level_grade_bean.setExam_name(jsonArray1.getString(1));
                            level_grade_bean.setGrade_pen(jsonArray1.getString(2));
                            level_grade_bean.setGrade_computer(jsonArray1.getString(3));
                            level_grade_bean.setGrade_all(jsonArray1.getString(4));
                            level_grade_bean.setLevel_grade_pen(jsonArray1.getString(5));
                            level_grade_bean.setLevel_grade_computer(jsonArray1.getString(6));
                            level_grade_bean.setLevel_grade_all(jsonArray1.getString(7));
                            level_grade_bean.setExam_date(jsonArray1.getString(8));
                            myLevel_grades.add(level_grade_bean);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                myCourse_table_adapter_listview = new Level_Grade_Adapter_listview(Level_Grade_Activity.this, myLevel_grades);
                                MyLevelGradeBinding.lvLevelExam.setAdapter(myCourse_table_adapter_listview);

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
