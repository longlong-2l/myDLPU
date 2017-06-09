package com.surpassli.www.myapp.ui.Account;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.surpassli.www.myapp.AppVariables;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.model.Account.Course_Result_bean;
import com.surpassli.www.myapp.support.adapter.CourseAdapter.Course_Adapter;
import com.surpassli.www.myapp.support.adapter.CourseAdapter.Course_Result_Adapter;
import com.surpassli.www.myapp.support.utils.HttpUtil;
import com.surpassli.www.myapp.support.utils.MD5.MD5;
import com.surpassli.www.myapp.support.utils.ProgressDialog.MyProgressDialog;
import com.surpassli.www.myapp.support.utils.Utility;
import com.surpassli.www.myapp.ui.Base.BaseToolBarActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by SurpassLi on 2017/1/17.
 */
public class Course_Result_Activity extends BaseToolBarActivity {

    private static final String TAG = "Course_Result_Activity";
    //    private RecyclerView mRecycleView;
    private ListView lv_course;
    private TextView mEmpty;
    private Button bt_course_resultSearch;
    private List<Course_Result_bean> mCourse_Result_list;
    private Course_Result_Adapter mCourse_result_adapter;
    private Course_Adapter course_adapter;
    private long mytime;
    private String term;
    private Spinner myCourseResultSpinner;
    private ArrayAdapter<String> arr_adapter;
    private static final String[] m = {"2015-2016-1", "2015-2016-2", "2016-2017-1", "2016-2017-2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_result);
        initToolBar();
        setToolbarTitle("课程成绩");
        MyProgressDialog.showProgressDialog(Course_Result_Activity.this);
        initView();
//        getData(term);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initView() {
        lv_course = (ListView) findViewById(R.id.lv_course);
        mEmpty = (TextView) findViewById(R.id.tv_course_result_empty);
        myCourseResultSpinner = (Spinner) findViewById(R.id.sp_course_result);
        bt_course_resultSearch = (Button) findViewById(R.id.bt_course_resultSearch);
//        mRecycleView = (RecyclerView) findViewById(R.id.rv_course_recycleView);
        //适配器
        arr_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, m);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        myCourseResultSpinner.setAdapter(arr_adapter);
        myCourseResultSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                term = m[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        bt_course_resultSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(term);
            }
        });
    }

    private void getData(String term) {
        mCourse_Result_list = new ArrayList<Course_Result_bean>();
        mytime = System.currentTimeMillis() / 1000;//获取系统时间的10位的时间戳
        String timestamp = String.valueOf(mytime + AppVariables.time_cha);
        AppVariables.sign = MD5.getMd5(AppVariables.key + AppVariables.token + timestamp);
        Log.i(TAG, "initView: " + "userId=" + AppVariables.userId + "==token=" + AppVariables.token + "==&sign=" + AppVariables.sign + "==timestamp=" + timestamp);
        HttpUtil.sendGetOkHttp_header_sk(AppApi.MY_COURSE + "userId=" + AppVariables.userId + "&sign=" + AppVariables.sign + "&timestamp=" + timestamp, term, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.closeDialog();
                Log.i(TAG, "onFailure: " + "获取课程信息返回数据失败：" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "onResponse: " + "获取课程信息返回数据成功");
                String res = response.body().string();
                mCourse_Result_list = Utility.handCourse_Course_Result(res);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        mCourse_result_adapter = new Course_Result_Adapter(Course_Result_Activity.this, mCourse_Result_list);
                        //设置RecycleView 以ListView的方式显示
//                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Course_Result_Activity.this, LinearLayoutManager.VERTICAL, false);
//                        mRecycleView.setLayoutManager(layoutManager);
//                        mRecycleView.addItemDecoration(new DividerItemDecoration(Course_Result_Activity.this, DividerItemDecoration.VERTICAL_LIST));//设置分割线
//                        mRecycleView.setAdapter(mCourse_result_adapter);
//                        mRecycleView.setItemAnimator(new DefaultItemAnimator());//设置动画效果为默认动画
                        if (mCourse_Result_list == null || mCourse_Result_list.isEmpty()) {
                            mEmpty.setVisibility(View.VISIBLE);
                            lv_course.setVisibility(View.GONE);
                        } else {
                            mEmpty.setVisibility(View.GONE);
                            lv_course.setVisibility(View.VISIBLE);
                            course_adapter = new Course_Adapter(Course_Result_Activity.this, mCourse_Result_list);
                            lv_course.setAdapter(course_adapter);
                        }
                        MyProgressDialog.closeDialog();
                    }
                });
            }
        });
    }
}
