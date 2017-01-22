package com.surpassli.www.myapp.model.Account;

/**
 * Created by SurpassLi on 2017/1/18.
 */
public class Course_Result_bean {

    public String num;              //序号

    public String No1_date;        //首修学期

    public String course;          //课程编号

    public String course_name;        //课程名称

    public String course_score;       //成绩

    public String score_flag;        //成绩标识

    public  String course_credit;     //学分

    public String course_period;      //总学时

    public String exam_type;          //考核方式

    public String course_property;   //课程属性

    public String course_nature;     //课程性质

    public String exam_nature;       //考试性质

    public String again_term;        //补重学期

//    public Course_Result_bean(JSONArray jsonArray,int i) {
//        try {
//            JSONArray jsonArray1 = jsonArray.getJSONArray(i);
//                Course_Result_bean.num = jsonArray1.getString(0);
//                Course_Result_bean.No1_date = jsonArray1.getString(1);
//                Course_Result_bean.course = jsonArray1.getString(2);
//                Course_Result_bean.course_name = jsonArray1.getString(3);
//                Course_Result_bean.course_score = jsonArray1.getString(4);
//                Course_Result_bean.score_flag = jsonArray1.getString(5);
//                Course_Result_bean.course_credit = jsonArray1.getString(6);
//                Course_Result_bean.course_period = jsonArray1.getString(7);
//                Course_Result_bean.exam_type = jsonArray1.getString(8);
//                Course_Result_bean.course_property = jsonArray1.getString(9);
//                Course_Result_bean.course_nature = jsonArray1.getString(10);
//                Course_Result_bean.exam_nature = jsonArray1.getString(11);
//                Course_Result_bean.again_term = jsonArray1.getString(12);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getNo1_date() {
        return No1_date;
    }

    public void setNo1_date(String no1_date) {
        No1_date = no1_date;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_score() {
        return course_score;
    }

    public void setCourse_score(String course_score) {
        this.course_score = course_score;
    }

    public String getScore_flag() {
        return score_flag;
    }

    public void setScore_flag(String score_flag) {
        this.score_flag = score_flag;
    }

    public String getCourse_credit() {
        return course_credit;
    }

    public void setCourse_credit(String course_credit) {
        this.course_credit = course_credit;
    }

    public String getCourse_period() {
        return course_period;
    }

    public void setCourse_period(String course_period) {
        this.course_period = course_period;
    }

    public String getExam_type() {
        return exam_type;
    }

    public void setExam_type(String exam_type) {
        this.exam_type = exam_type;
    }

    public String getCourse_property() {
        return course_property;
    }

    public void setCourse_property(String course_property) {
        this.course_property = course_property;
    }

    public String getCourse_nature() {
        return course_nature;
    }

    public void setCourse_nature(String course_nature) {
        this.course_nature = course_nature;
    }

    public String getExam_nature() {
        return exam_nature;
    }

    public void setExam_nature(String exam_nature) {
        this.exam_nature = exam_nature;
    }

    public String getAgain_term() {
        return again_term;
    }

    public void setAgain_term(String again_term) {
        this.again_term = again_term;
    }
}
