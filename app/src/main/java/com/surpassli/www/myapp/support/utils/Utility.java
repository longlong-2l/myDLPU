package com.surpassli.www.myapp.support.utils;

import android.util.Log;

import com.surpassli.www.myapp.database.School_Roll.Person_info;
import com.surpassli.www.myapp.gson.Course_Result_bean;
import com.surpassli.www.myapp.model.Course_Table.Level_Grade;
import com.surpassli.www.myapp.model.Level_Grade.Course_Table;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SurpassLi on 2017/2/1.
 */
public class Utility {

    private static final String TAG = "Utility";

    private static List<Course_Result_bean> mCourse_Result_list;

    private static ArrayList<Level_Grade> myLevel_course_been;

    private static ArrayList<Course_Table> course_table_been_list;

    /**
     * 获取学籍卡片
     */
    public static boolean handPerson_info(String response) {
        JSONArray jsonArray;
        try {
            JSONObject jsonObject = new JSONObject(response);
            if ("Success".equals(jsonObject.getString("message"))) {
                jsonArray = jsonObject.getJSONArray("date");
                Person_info person_info = new Person_info();
                person_info.setStudent_id(jsonArray.getString(0));
                person_info.setAcademy(jsonArray.getString(2));
                person_info.setMajor(jsonArray.getString(3));
                person_info.setEducational_system(jsonArray.getString(4));
                person_info.setGrades(jsonArray.getString(5));
                person_info.setName(jsonArray.getString(8));
                person_info.setSex(jsonArray.getString(10));
                person_info.setName_pinyin(jsonArray.getString(12));
                person_info.setBirthday(jsonArray.getString(14));
                person_info.setIntoSchool_date(jsonArray.getString(177));
                person_info.setIntoSchool_num(jsonArray.getString(181));
                person_info.setIdcard(jsonArray.getString(183));
                person_info.save();
            } else {
                Log.i(TAG, "Person_School_Roll: " + "数据获取有误");
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            Log.i(TAG, "Person_School_Roll: " + "json解析出现问题");
        }
        return false;
    }

    /**
     * 获取课程成绩
     */
    public static List<Course_Result_bean> handCourse_Course_Result(String response) {
        mCourse_Result_list = new ArrayList<Course_Result_bean>();
        Course_Result_bean course_result_bean;
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray1;
            if (jsonObject.getString("message").equals("Success")) {
                Log.i("", "onResponse: " + "获取课程正确信息");
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonArray1 = jsonArray.getJSONArray(i);
                    course_result_bean = new Course_Result_bean();
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
                    mCourse_Result_list.add(course_result_bean);
                }
                return mCourse_Result_list;
            } else {
                Log.i(TAG, "Utility: " + "获取课程成绩有误");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取等级成绩
     */
    public static List<Level_Grade> handLevel_Grade(String response) {
        myLevel_course_been = new ArrayList<Level_Grade>();
        Level_Grade level_grade;
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response);
            if ("Success".equals(jsonObject.getString("message"))) {
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONArray jsonArray1 = jsonArray.getJSONArray(i);
                    level_grade = new Level_Grade();
                    level_grade.setOrder_number(jsonArray1.getString(0));
                    level_grade.setExam_name(jsonArray1.getString(1));
                    level_grade.setGrade_pen(jsonArray1.getString(2));
                    level_grade.setGrade_computer(jsonArray1.getString(3));
                    level_grade.setGrade_all(jsonArray1.getString(4));
                    level_grade.setLevel_grade_pen(jsonArray1.getString(5));
                    level_grade.setLevel_grade_computer(jsonArray1.getString(6));
                    level_grade.setLevel_grade_all(jsonArray1.getString(7));
                    level_grade.setExam_date(jsonArray1.getString(8));
                    myLevel_course_been.add(level_grade);
                }
                return myLevel_course_been;
            } else {
                Log.i(TAG, "Utility: " + "获取等级成绩有误");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取课程表2016-2017-1
     */
    public static List<Course_Table> course_table_16_17_1(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray0;
            JSONObject jsonObject1;
            JSONObject jsonObject2;
            JSONObject jsonObject3;
            if ("Success".equals(jsonObject.getString("message"))) {
                Log.i(TAG, "onResponse: " + "访问课程表信息成功");
                jsonObject1 = jsonObject.getJSONObject("data");
                jsonObject2 = jsonObject1.getJSONObject("0");
                for (int i = 0; i < jsonObject2.length(); i++) {
                    jsonArray0 = jsonObject2.getJSONArray("1");
                    for (int j = 0; j < jsonArray0.length(); j++) {
                        jsonArray0.getJSONArray(j);
                    }
                }
                jsonObject2.getJSONArray("1");
            }
            ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取课程表2015-2016-1
     */
    public static List<Course_Table> course_table_15_16_1(String response) {
        Course_Table course_table;
        course_table_been_list = new ArrayList<Course_Table>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray0;
            JSONObject jsonObject1;
            if ("Success".equals(jsonObject.getString("message"))) {
                Log.i(TAG, "onResponse: " + "访问课程表信息成功");
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                jsonArray0 = jsonArray.getJSONArray(0);
                for (int i = 0; i < jsonArray0.length(); i++) {
                    JSONArray jsonArray2 = jsonArray0.getJSONArray(i);
                    course_table = new Course_Table();
                    course_table.setCourse_name(jsonArray2.getString(0));
                    course_table.setCourse_time(jsonArray2.getString(1));
                    course_table.setCourse_address(jsonArray2.getString(2));
                    course_table.setCourse_teacher(jsonArray2.getString(3));
                    course_table_been_list.add(course_table);
                }
                for (int n = 1; n < jsonArray.length(); n++) {
                    jsonObject1 = jsonArray.getJSONObject(n);
                    for (int i = 0; i < 5; i++) {
                        JSONArray jsonArray1 = jsonObject1.optJSONArray(String.valueOf(i));
                        if (jsonArray1 == null | "".equals(jsonArray1)) {
                            continue;
                        } else {
                            course_table = new Course_Table();
                            course_table.setCourse_name(jsonArray1.getString(0));
                            course_table.setCourse_time(jsonArray1.getString(1));
                            course_table.setCourse_address(jsonArray1.getString(2));
                            course_table.setCourse_teacher(jsonArray1.getString(3));
                            course_table_been_list.add(course_table);
                        }
                    }
                }
                return course_table_been_list;
            } else {
                Log.i(TAG, "Utility: " + "获取课程表有误");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取课程表2015-2016-2
     */
    public static List<Course_Table> course_table_15_16_2(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray0;
            JSONArray jsonArray1;
            JSONObject jsonObject1;
            if ("Success".equals(jsonObject.getString("message"))) {
                Log.i(TAG, "onResponse: " + "访问课程表信息成功");
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                jsonArray0 = jsonArray.getJSONArray(0);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 获取当前周次
     */
//    public static handCurrentweek(String response){
//
//    }
    /**
     * 获取考试安排
     */

    /**
     * 密码修改
     */
}
