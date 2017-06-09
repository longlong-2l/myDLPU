package com.surpassli.www.myapp.ui.Account;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.surpassli.www.myapp.AppVariables;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.model.Account.Exam_Manager;
import com.surpassli.www.myapp.support.adapter.CourseAdapter.Exam_Manager_Adpater;
import com.surpassli.www.myapp.support.utils.HttpUtil;
import com.surpassli.www.myapp.support.utils.MD5.MD5;
import com.surpassli.www.myapp.support.utils.ProgressDialog.MyProgressDialog;
import com.surpassli.www.myapp.support.utils.Utility;
import com.surpassli.www.myapp.ui.Base.BaseToolBarActivity;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Response;

public class ExamManagerActivity extends BaseToolBarActivity {

    private ArrayList<Exam_Manager> exam_managers_been_list;
    private Exam_Manager_Adpater exam_manager_adapter;
    private RecyclerView rv_exam_manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_manager);
        initToolBar();
        setToolbarTitle("考试安排");
        initView();
        handExamManager();
    }

    private void initView() {
        rv_exam_manager = (RecyclerView) findViewById(R.id.rv_exam_manager);
    }

    private void handExamManager() {
        MyProgressDialog.showProgressDialog(ExamManagerActivity.this);
        long mytime = System.currentTimeMillis()/1000;
        String timestamp = String.valueOf(mytime + AppVariables.time_cha);
        String sign = MD5.getMd5(AppVariables.key + AppVariables.token + timestamp);
        HttpUtil.sendGetOkHttp_header_sk(AppApi.MY_EXAM_PLAN + "userId="+AppVariables.userId + "&sign="+ sign +"&timestamp=" + timestamp,"2016-2017-2",new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MyProgressDialog.closeDialog();
                        Toast.makeText(ExamManagerActivity.this,"网络出现问题，请检查网络设置...",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("CurrentWeekActivity", "onResponse: " + "成功访问接口");
                String res = response.body().string();
                exam_managers_been_list = (ArrayList<Exam_Manager>) Utility.handExamManager(res);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        exam_manager_adapter = new Exam_Manager_Adpater(ExamManagerActivity.this, exam_managers_been_list);
                        rv_exam_manager.setAdapter(exam_manager_adapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ExamManagerActivity.this, LinearLayoutManager.VERTICAL, false);
                        rv_exam_manager.setLayoutManager(layoutManager);
                        MyProgressDialog.closeDialog();
                    }
                });
            }
        });
    }
}
