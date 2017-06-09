package com.surpassli.www.myapp.model.Account;

/**
 * Created by SurpassLi on 2017/1/21.
 */
public class Exam_Manager {

    private String order_number;            //序号

    private String exam_number;             //考试场次

    private String course_number;           //课程编号

    private String course_name;             //课程名称

    private String exam_time;               //考试时间

    private String exam_address;            //考场

    private String exam_card_number;        //准卡证号

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getExam_number() {
        return exam_number;
    }

    public void setExam_number(String exam_number) {
        this.exam_number = exam_number;
    }

    public String getCourse_number() {
        return course_number;
    }

    public void setCourse_number(String course_number) {
        this.course_number = course_number;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getExam_time() {
        return exam_time;
    }

    public void setExam_time(String exam_time) {
        this.exam_time = exam_time;
    }

    public String getExam_address() {
        return exam_address;
    }

    public void setExam_address(String exam_address) {
        this.exam_address = exam_address;
    }

    public String getExam_card_number() {
        return exam_card_number;
    }

    public void setExam_card_number(String exam_card_number) {
        this.exam_card_number = exam_card_number;
    }
}
