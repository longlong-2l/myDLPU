package com.surpassli.www.myapp.ui.Account;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.surpassli.www.myapp.AppVariables;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.model.Account.Person_School_Roll;
import com.surpassli.www.myapp.support.utils.HttpUtil;
import com.surpassli.www.myapp.support.utils.MD5.MD5;
import com.surpassli.www.myapp.support.utils.ProgressDialog.MyProgressDialog;
import com.surpassli.www.myapp.ui.Base.BaseToolBarActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by SurpassLi on 2017/1/17.
 */
public class School_Roll_Activity extends BaseToolBarActivity {
    private static final String TAG = "School_Roll_Activity";
    private String sign;
    private long mytime;
    private Person_School_Roll person_school_roll;
    private TextView tv_academy;
    private TextView tv_major;
    private TextView tv_educational_system;
    private TextView tv_grades;
    private TextView tv_student_id;
    private TextView tv_name;
    private TextView tv_sex;
    private TextView tv_name_pinyin;
    private TextView tv_birthday;
    private TextView tv_intoSchool_date;
    private TextView tv_intoSchool_num;
    private TextView tv_idcard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_roll);
        initToolBar();
        setToolbarTitle("学籍卡片");
        initView();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String personalInfo = prefs.getString("personalinfo", null);
        if (personalInfo != null) {
            //有缓存时直接解析数据
            person_school_roll = new Person_School_Roll(personalInfo);
            initView(person_school_roll);
        } else {
            //无缓存时从服务器解析数据
            getData();
        }
    }

    private void initView() {
        tv_academy = (TextView) findViewById(R.id.tv_academy);
        tv_major = (TextView) findViewById(R.id.tv_major);
        tv_educational_system = (TextView) findViewById(R.id.tv_educational_system);
        tv_grades = (TextView) findViewById(R.id.tv_grades);
        tv_student_id = (TextView) findViewById(R.id.tv_student_id);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        tv_name_pinyin = (TextView) findViewById(R.id.tv_name_pinyin);
        tv_birthday = (TextView) findViewById(R.id.tv_birthday);
        tv_intoSchool_date = (TextView) findViewById(R.id.tv_intoSchool_date);
        tv_intoSchool_num = (TextView) findViewById(R.id.tv_intoSchool_num);
        tv_idcard = (TextView) findViewById(R.id.tv_idcard);
    }

    private void getData() {
        MyProgressDialog.showProgressDialog(School_Roll_Activity.this);
        mytime = System.currentTimeMillis() / 1000;//获取系统时间的10位的时间戳
        String timestamp = String.valueOf(mytime + AppVariables.time_cha);
        sign = MD5.getMd5(AppVariables.key + AppVariables.token + timestamp);

        Log.i(TAG, "getData: " + AppApi.MY_ACCOUNT + "userId=" + AppVariables.userId + "&sign=" + sign + "&timestamp=" + timestamp);
        HttpUtil.sendGetOkHttp_header(AppApi.MY_ACCOUNT + "userId=" + AppVariables.userId + "&sign=" + sign + "&timestamp=" + timestamp, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyProgressDialog.closeDialog();
                Toast.makeText(School_Roll_Activity.this, "加载失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                person_school_roll = new Person_School_Roll(data);
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(School_Roll_Activity.this).edit();
                editor.putString("personalinfo", data);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initView(person_school_roll);
                    }
                });
            }
        });
    }

    public void initView(Person_School_Roll person_school_roll) {
        tv_academy.setText(person_school_roll.getAcademy());
        tv_major.setText(person_school_roll.getMajor());
        tv_educational_system.setText(person_school_roll.getEducational_system());
        tv_grades.setText(person_school_roll.getGrades());
        tv_student_id.setText("学号：" + person_school_roll.getStudent_id());
        tv_name.setText("姓名：" + person_school_roll.getname());
        tv_sex.setText("性别：" + person_school_roll.getSex());
        tv_name_pinyin.setText("姓名拼音：" + person_school_roll.getName_pinyin());
        tv_birthday.setText("出生日期：" + person_school_roll.getBirthday());
        tv_intoSchool_date.setText("入学日期：" + person_school_roll.getIntoSchool_date());
        tv_intoSchool_num.setText("入学考号：" + person_school_roll.getIntoSchool_num());
        tv_idcard.setText("身份证编号：" + person_school_roll.getIdcard());
        MyProgressDialog.closeDialog();
    }
}
