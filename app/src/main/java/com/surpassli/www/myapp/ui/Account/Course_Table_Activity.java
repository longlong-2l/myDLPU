package com.surpassli.www.myapp.ui.Account;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.surpassli.www.myapp.AppVariables;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.model.Level_Grade.Course_Table;
import com.surpassli.www.myapp.support.adapter.CourseAdapter.Course_table_recyclerview;
import com.surpassli.www.myapp.support.utils.HttpUtil;
import com.surpassli.www.myapp.support.utils.MD5.MD5;
import com.surpassli.www.myapp.support.utils.Utility;
import com.surpassli.www.myapp.ui.Base.BaseToolBarActivity;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by SurpassLi on 2017/1/22.
 */
public class Course_Table_Activity extends BaseToolBarActivity {
    private ArrayList<Course_Table> course_table_been_list;
    private EditText et_course_table;
    private RecyclerView rv_course_table;
    private Button bt_search;
    private Course_table_recyclerview course_table_recyclerview;
    private static final String TAG = "Course_Table_Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_table);
        initToolBar();
        setToolbarTitle("课程表");
        initView();
    }
    public void initView(){
        et_course_table = (EditText) findViewById(R.id.et_course_table);
        rv_course_table = (RecyclerView) findViewById(R.id.rv_course_table);
        bt_search = (Button) findViewById(R.id.bt_Search);
        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
    }

    public void getData() {
        String xueqi = et_course_table.getText().toString();
        course_table_been_list = new ArrayList<Course_Table>();
        long mytime = System.currentTimeMillis()/1000;
        String timestamp = String.valueOf(mytime + AppVariables.time_cha);
        AppVariables.sign = MD5.getMd5(AppVariables.key + AppVariables.token + timestamp);
        HttpUtil.sendGetOkHttp_header_swk(xueqi,AppApi.MY_COURSE_TABLE + "userId=" + AppVariables.userId + "&sign=" + AppVariables.sign + "&timestamp=" + timestamp, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: " + "网络有问题");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "onResponse: " + "连接课程表信息成功");
                String res = response.body().string();
                course_table_been_list = (ArrayList<Course_Table>) Utility.course_table_15_16_1(res);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        course_table_recyclerview = new Course_table_recyclerview(course_table_been_list,Course_Table_Activity.this);
                        rv_course_table.setAdapter(course_table_recyclerview);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Course_Table_Activity.this, LinearLayoutManager.VERTICAL, false);
                        rv_course_table.setLayoutManager(layoutManager);
                    }
                });
            }
        });
    }
}
