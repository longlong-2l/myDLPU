package com.surpassli.www.myapp.model.Level_Grade;

/**
 * Created by SurpassLi on 2017/1/22.
 */
public class Course_Table {

    private String Course_name;

    private String Course_time;

    private String Course_address;

    private String Course_teacher;

    private String week_day;

    private String class_time;

    public String getWeek_day() {
        return week_day;
    }

    public void setWeek_day(String week_day) {
        this.week_day = week_day;
    }

    public String getClass_time() {
        return class_time;
    }

    public void setClass_time(String class_time) {
        this.class_time = class_time;
    }

    public String getCourse_teacher() {
        return Course_teacher;
    }

    public void setCourse_teacher(String course_teacher) {
        Course_teacher = course_teacher;
    }

    public String getCourse_address() {
        return Course_address;
    }

    public void setCourse_address(String course_address) {
        Course_address = course_address;
    }

    public String getCourse_time() {
        return Course_time;
    }

    public void setCourse_time(String course_time) {
        Course_time = course_time;
    }

    public String getCourse_name() {
        return Course_name;
    }

    public void setCourse_name(String course_name) {
        Course_name = course_name;
    }
}
