package com.surpassli.www.myapp.support.utils;

import android.util.Log;

import com.surpassli.www.myapp.model.Level_Grade.Course_Table;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by longl on 2017/6/8.
 */

public class Course_Table_json {

    private static final String TAG = "Course_Table_json";
    private static ArrayList<Course_Table> course_table_been_list;

    /**
     * 获取课程表2015-2016-1
     */
    public static List<Course_Table> course_table_15_16_1(String response) {
        Course_Table course_table;
        course_table_been_list = new ArrayList<Course_Table>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray0;
            if ("Success".equals(jsonObject.getString("message"))) {
                Log.i(TAG, "onResponse: " + "访问课程表信息成功");
                if (jsonObject.isNull("data")) {
                    return course_table_been_list;
                }
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                if (jsonArray == null) {
                    return course_table_been_list;
                }
                for (int i = 0; i < jsonArray.length(); i++) {
                    if (i == 0) {
                        jsonArray0 = jsonArray.getJSONArray(i);
                        for (int j = 0; j < jsonArray0.length(); j++) {
                            JSONArray jsonArray2 = jsonArray0.getJSONArray(j);
                            course_table = new Course_Table();
                            course_table.setCourse_name(jsonArray2.getString(0));
                            course_table.setCourse_time(jsonArray2.getString(1));
                            course_table.setCourse_address(jsonArray2.getString(2));
                            course_table.setCourse_teacher(jsonArray2.getString(3));
                            course_table.setClass_time("1,2节");
                            if (j == 0) {
                                course_table.setWeek_day("周一上课");
                            } else if (j == 1) {
                                course_table.setWeek_day("周二上课");
                            } else if (j == 2) {
                                course_table.setWeek_day("周三上课");
                            } else if (j == 3) {
                                course_table.setWeek_day("周四上课");
                            } else if (j == 4) {
                                course_table.setWeek_day("周五上课");
                            }
                            course_table_been_list.add(course_table);
                        }
                    } else {
                        JSONObject jsonobject1 = jsonArray.getJSONObject(i);
                        for (int j = 0; j < 5; j++) {
                            if (jsonobject1.has(String.valueOf(j))) {
                                JSONArray array = jsonobject1.getJSONArray(String.valueOf(j));
                                course_table = new Course_Table();
                                course_table.setCourse_name(array.getString(0));
                                course_table.setCourse_time(array.getString(1));
                                course_table.setCourse_address(array.getString(2));
                                course_table.setCourse_teacher(array.getString(3));
                                if (i == 1) {
                                    course_table.setClass_time("3,4节");
                                } else if (i == 2) {
                                    course_table.setClass_time("5,6节");
                                } else if (i == 3) {
                                    course_table.setClass_time("7,8节");
                                }
                                if (j == 0) {
                                    course_table.setWeek_day("周一上课");
                                } else if (j == 1) {
                                    course_table.setWeek_day("周二上课");
                                } else if (j == 2) {
                                    course_table.setWeek_day("周三上课");
                                } else if (j == 3) {
                                    course_table.setWeek_day("周四上课");
                                } else if (j == 4) {
                                    course_table.setWeek_day("周五上课");
                                }
                                course_table_been_list.add(course_table);
                            }
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
        Course_Table course_table;
        course_table_been_list = new ArrayList<Course_Table>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray0;
            if ("Success".equals(jsonObject.getString("message"))) {
                Log.i(TAG, "onResponse: " + "访问课程表信息成功");
                if (jsonObject.isNull("data")) {
                    return course_table_been_list;
                }
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                if (jsonArray == null) {
                    return course_table_been_list;
                }
                for (int i = 0; i < jsonArray.length(); i++) {
                    if (i == 0 || i == 2) {
                        jsonArray0 = jsonArray.getJSONArray(i);
                        for (int j = 0; j < jsonArray0.length(); j++) {
                            JSONArray jsonArray2 = jsonArray0.getJSONArray(j);
                            course_table = new Course_Table();
                            course_table.setCourse_name(jsonArray2.getString(0));
                            course_table.setCourse_time(jsonArray2.getString(1));
                            course_table.setCourse_address(jsonArray2.getString(2));
                            course_table.setCourse_teacher(jsonArray2.getString(3));
                            if (i == 0) {
                                course_table.setClass_time("1,2节");
                            } else if (i == 2) {
                                course_table.setClass_time("5,6节");
                            }
                            if (j == 0) {
                                course_table.setWeek_day("周一上课");
                            } else if (j == 1) {
                                course_table.setWeek_day("周二上课");
                            } else if (j == 2) {
                                course_table.setWeek_day("周三上课");
                            } else if (j == 3) {
                                course_table.setWeek_day("周四上课");
                            } else if (j == 4) {
                                course_table.setWeek_day("周五上课");
                            }
                            course_table_been_list.add(course_table);
                        }
                    } else {
                        JSONObject jsonobject1 = jsonArray.getJSONObject(i);
                        for (int j = 0; j < 5; j++) {
                            if (jsonobject1.has(String.valueOf(j))) {
                                JSONArray array = jsonobject1.getJSONArray(String.valueOf(j));
                                course_table = new Course_Table();
                                course_table.setCourse_name(array.getString(0));
                                course_table.setCourse_time(array.getString(1));
                                course_table.setCourse_address(array.getString(2));
                                course_table.setCourse_teacher(array.getString(3));
                                if (i == 1) {
                                    course_table.setClass_time("3,4节");
                                } else if (i == 3) {
                                    course_table.setClass_time("7,8节");
                                }
                                if (j == 0) {
                                    course_table.setWeek_day("周一上课");
                                } else if (j == 1) {
                                    course_table.setWeek_day("周二上课");
                                } else if (j == 2) {
                                    course_table.setWeek_day("周三上课");
                                } else if (j == 3) {
                                    course_table.setWeek_day("周四上课");
                                } else if (j == 4) {
                                    course_table.setWeek_day("周五上课");
                                }
                                course_table_been_list.add(course_table);
                            }
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
     * 获取课程表2016-2017-1
     */
    public static List<Course_Table> course_table_16_17_1(String response) {
        Course_Table course_table;
        course_table_been_list = new ArrayList<Course_Table>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray0;
            if ("Success".equals(jsonObject.getString("message"))) {
                Log.i(TAG, "onResponse: " + "访问课程表信息成功");
                if (jsonObject.isNull("data")){
                    return course_table_been_list;
                }
                JSONObject jsonobject = jsonObject.getJSONObject("data");
                if (jsonobject == null) {
                    return course_table_been_list;
                }
                for (int i = 0; i < 4; i++) {
                    JSONObject jsonobject1 = jsonobject.getJSONObject(String.valueOf(i));
                    for (int j = 0; j < 5; j++) {
                        if (jsonobject1.has(String.valueOf(j))) {
                            JSONArray array = jsonobject1.getJSONArray(String.valueOf(j));
                            course_table = new Course_Table();
                            course_table.setCourse_name(array.getString(0));
                            course_table.setCourse_time(array.getString(1));
                            course_table.setCourse_address(array.getString(2));
                            course_table.setCourse_teacher(array.getString(3));
                            if (i == 0) {
                                course_table.setClass_time("1,2节");
                            } else if (i == 1) {
                                course_table.setClass_time("3,4节");
                            } else if (i == 2) {
                                course_table.setClass_time("5,6节");
                            } else if (i == 3) {
                                course_table.setClass_time("7,8节");
                            }
                            if (j == 0) {
                                course_table.setWeek_day("周一上课");
                            } else if (j == 1) {
                                course_table.setWeek_day("周二上课");
                            } else if (j == 2) {
                                course_table.setWeek_day("周三上课");
                            } else if (j == 3) {
                                course_table.setWeek_day("周四上课");
                            } else if (j == 4) {
                                course_table.setWeek_day("周五上课");
                            }
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

    /*
     * 获取课程表2016-2017-2
     */
    public static List<Course_Table> course_table_16_17_2(String response) {
        Course_Table course_table;
        course_table_been_list = new ArrayList<Course_Table>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray0;
            if ("Success".equals(jsonObject.getString("message"))) {
                Log.i(TAG, "onResponse: " + "访问课程表信息成功");
                if (jsonObject.isNull("data")){
                    return course_table_been_list;
                }
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                if (jsonArray == null) {
                    return course_table_been_list;
                }
                    for (int i = 0; i < jsonArray.length(); i++) {
                        if (i == 0 || i == 2) {
                            jsonArray0 = jsonArray.getJSONArray(i);
                            for (int j = 0; j < jsonArray0.length(); j++) {
                                JSONArray jsonArray2 = jsonArray0.getJSONArray(j);
                                course_table = new Course_Table();
                                course_table.setCourse_name(jsonArray2.getString(0));
                                course_table.setCourse_time(jsonArray2.getString(1));
                                course_table.setCourse_address(jsonArray2.getString(2));
                                course_table.setCourse_teacher(jsonArray2.getString(3));
                                if (i == 0) {
                                    course_table.setClass_time("1,2节");
                                } else if (i == 2) {
                                    course_table.setClass_time("5,6节");
                                }
                                if (j == 0) {
                                    course_table.setWeek_day("周一上课");
                                } else if (j == 1) {
                                    course_table.setWeek_day("周二上课");
                                } else if (j == 2) {
                                    course_table.setWeek_day("周三上课");
                                } else if (j == 3) {
                                    course_table.setWeek_day("周四上课");
                                } else if (j == 4) {
                                    course_table.setWeek_day("周五上课");
                                }
                                course_table_been_list.add(course_table);
                            }
                        } else {
                            JSONObject jsonobject1 = jsonArray.getJSONObject(i);
                            for (int j = 0; j < 5; j++) {
                                if (jsonobject1.has(String.valueOf(j))) {
                                    JSONArray array = jsonobject1.getJSONArray(String.valueOf(j));
                                    course_table = new Course_Table();
                                    course_table.setCourse_name(array.getString(0));
                                    course_table.setCourse_time(array.getString(1));
                                    course_table.setCourse_address(array.getString(2));
                                    course_table.setCourse_teacher(array.getString(3));
                                    if (i == 1) {
                                        course_table.setClass_time("3,4节");
                                    } else if (i == 3) {
                                        course_table.setClass_time("7,8节");
                                    }
                                    if (j == 0) {
                                        course_table.setWeek_day("周一上课");
                                    } else if (j == 1) {
                                        course_table.setWeek_day("周二上课");
                                    } else if (j == 2) {
                                        course_table.setWeek_day("周三上课");
                                    } else if (j == 3) {
                                        course_table.setWeek_day("周四上课");
                                    } else if (j == 4) {
                                        course_table.setWeek_day("周五上课");
                                    }
                                    course_table_been_list.add(course_table);
                                }
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
}
