package com.surpassli.www.myapp.ui.Account;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.surpassli.www.myapp.AppVariables;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.model.Level_Grade.Course_Table;
import com.surpassli.www.myapp.support.adapter.CourseAdapter.Course_table_recyclerview;
import com.surpassli.www.myapp.support.utils.Course_Table_json;
import com.surpassli.www.myapp.support.utils.HttpUtil;
import com.surpassli.www.myapp.support.utils.MD5.MD5;
import com.surpassli.www.myapp.support.utils.ProgressDialog.MyProgressDialog;
import com.surpassli.www.myapp.ui.Base.BaseToolBarActivity;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by SurpassLi on 2017/1/22.
 */
public class Course_Table_Activity extends BaseToolBarActivity {

    private static final String TAG = "Course_Table_Activity";
    private ArrayList<Course_Table> course_table_been_list;
    private RecyclerView rv_course_table;
    private TextView mEmpty;
    private Button bt_search;
//    private Button bt_choice;
    private String term;
    private Course_table_recyclerview course_table_recyclerview;
    private Spinner myCourseTableSpinner;
    private ArrayAdapter<String> arr_adapter;
    private static final String[] m={"2015-2016-1","2015-2016-2","2016-2017-1","2016-2017-2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_table);
        initToolBar();
        setToolbarTitle("课程表");
        initView();
    }
    public void initView(){
        rv_course_table = (RecyclerView) findViewById(R.id.rv_course_table);
        myCourseTableSpinner = (Spinner) findViewById(R.id.sp_course_table);
        mEmpty = (TextView) findViewById(R.id.tv_course_empty);
        bt_search = (Button) findViewById(R.id.bt_Search);
//        bt_choice = (Button) findViewById(R.id.bt_choice);
        //适配器
        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, m);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        myCourseTableSpinner.setAdapter(arr_adapter);
        myCourseTableSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                term = m[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*bt_choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(bt_choice);
            }
        });*/
        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(term);
            }
        });
    }

    public void getData(final String term) {
        MyProgressDialog.showProgressDialog(Course_Table_Activity.this);
        course_table_been_list = new ArrayList<Course_Table>();
        long myTime = System.currentTimeMillis()/1000;
        String timesTamp = String.valueOf(myTime + AppVariables.time_cha);
        AppVariables.sign = MD5.getMd5(AppVariables.key + AppVariables.token + timesTamp);
        HttpUtil.sendGetOkHttp_header_swk(term,AppApi.MY_COURSE_TABLE + "userId=" + AppVariables.userId + "&sign=" + AppVariables.sign + "&timestamp=" + timesTamp, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: " + "网络有问题");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MyProgressDialog.closeDialog();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "onResponse: " + "连接课程表信息成功");
                String res = response.body().string();
                if ("2015-2016-1".equals(term)) {
                    course_table_been_list = (ArrayList<Course_Table>) Course_Table_json.course_table_15_16_1(res);
                }else if("2015-2016-2".equals(term)){
                    course_table_been_list = (ArrayList<Course_Table>) Course_Table_json.course_table_15_16_2(res);
                }else if("2016-2017-1".equals(term)){
                    course_table_been_list = (ArrayList<Course_Table>) Course_Table_json.course_table_16_17_1(res);
                }else if("2016-2017-2".equals(term)){
                    course_table_been_list = (ArrayList<Course_Table>) Course_Table_json.course_table_16_17_2(res);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        course_table_recyclerview = new Course_table_recyclerview(course_table_been_list, Course_Table_Activity.this);
                        if (course_table_been_list == null || course_table_been_list.isEmpty()) {
                            mEmpty.setVisibility(View.VISIBLE);
                            rv_course_table.setVisibility(View.GONE);
                        } else {
                            mEmpty.setVisibility(View.GONE);
                            rv_course_table.setVisibility(View.VISIBLE);
                            rv_course_table.setAdapter(course_table_recyclerview);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Course_Table_Activity.this, LinearLayoutManager.VERTICAL, false);
                            rv_course_table.setLayoutManager(layoutManager);
                        }
                        MyProgressDialog.closeDialog();
                    }
                });
            }
        });
    }

    /*private void showPopupMenu(View view){
        //指定pop显示的位置
        final PopupMenu popupMenu = new PopupMenu(this,view);
        //menu布局
        popupMenu.getMenuInflater().inflate(R.menu.menu_course_table,popupMenu.getMenu());
        //popmenu的点击事件
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                term = item.getTitle().toString();
                bt_choice.setText(term);
                return false;
            }
        });
        popupMenu.show();
    }*/
}
