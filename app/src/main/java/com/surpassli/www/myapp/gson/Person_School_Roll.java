package com.surpassli.www.myapp.gson;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by SurpassLi on 2017/1/16.
 */
public class Person_School_Roll {
    private static final String TAG = "Person_School_Roll";

    private String academy;//学院

    private String major;//专业

    private String educational_system;//学制

    private String grades;//班级

    private String student_id;//学号

    private String name;//姓名

    private String sex;//姓别

    private String name_pinyin;//姓名拼音

    private String birthday;//出生日期

    private String intoSchool_date;//入学日期

    private String intoSchool_num;//入学考号

    private String idcard;//身份证编号

    public Person_School_Roll(String response) {
        JSONArray jsonArray = null;
        try {
           JSONObject jsonObject = new JSONObject(response);
            if ("Success".equals(jsonObject.getString("message"))) {
                try {
                    jsonArray = jsonObject.getJSONArray("data");
                    student_id = jsonArray.getString(0);
                    academy = jsonArray.getString(2);
                    major = jsonArray.getString(3);
                    educational_system = jsonArray.getString(4);
                    grades = jsonArray.getString(5);
                    name = jsonArray.getString(8);
                    sex = jsonArray.getString(10);
                    name_pinyin = jsonArray.getString(12);
                    birthday = jsonArray.getString(14);
                    intoSchool_date = jsonArray.getString(177);
                    intoSchool_num = jsonArray.getString(181);
                    idcard = jsonArray.getString(183);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i(TAG, "Person_School_Roll: " + "json解析出现问题");
                }
            } else {
                Log.i(TAG, "Person_School_Roll: " + "数据获取有误");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public String getIdcard() {
        return idcard;
    }

    public String getAcademy() {
        return academy;
    }

    public String getMajor() {
        return major;
    }

    public String getEducational_system() {
        return educational_system;
    }

    public String getGrades() {
        return grades;
    }

    public String getStudent_id() {
        return student_id;
    }

    public String getname() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getName_pinyin() {
        return name_pinyin;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getIntoSchool_date() {
        return intoSchool_date;
    }

    public String getIntoSchool_num() {
        return intoSchool_num;
    }

}
