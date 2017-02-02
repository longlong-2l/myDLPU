package com.surpassli.www.myapp.model.Course_Table;

/**
 * Created by SurpassLi on 2017/1/21.
 */
public class Level_Grade {

    private String order_number;            //序号

    private String exam_name;               //等级考试名字

    private String grade_pen;               //分数类考试笔试

    private String grade_computer;         //分数类考试机试

    private String grade_all;               //分数类成绩总成绩

    private String level_grade_pen;        //等级类成绩笔试

    private String level_grade_computer;  //等级类成绩机试

    private String level_grade_all;        //等级类总成绩

    private String exam_date;               //考试日期

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getExam_name() {
        return exam_name;
    }

    public void setExam_name(String exam_name) {
        this.exam_name = exam_name;
    }

    public String getGrade_pen() {
        return grade_pen;
    }

    public void setGrade_pen(String grade_pen) {
        this.grade_pen = grade_pen;
    }

    public String getGrade_computer() {
        return grade_computer;
    }

    public void setGrade_computer(String grade_computer) {
        this.grade_computer = grade_computer;
    }

    public String getGrade_all() {
        return grade_all;
    }

    public void setGrade_all(String grade_all) {
        this.grade_all = grade_all;
    }

    public String getLevel_grade_pen() {
        return level_grade_pen;
    }

    public void setLevel_grade_pen(String level_grade_pen) {
        this.level_grade_pen = level_grade_pen;
    }

    public String getLevel_grade_computer() {
        return level_grade_computer;
    }

    public void setLevel_grade_computer(String level_grade_computer) {
        this.level_grade_computer = level_grade_computer;
    }

    public String getLevel_grade_all() {
        return level_grade_all;
    }

    public void setLevel_grade_all(String level_grade_all) {
        this.level_grade_all = level_grade_all;
    }

    public String getExam_date() {
        return exam_date;
    }

    public void setExam_date(String exam_date) {
        this.exam_date = exam_date;
    }
}
