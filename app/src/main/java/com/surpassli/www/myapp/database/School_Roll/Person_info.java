package com.surpassli.www.myapp.database.School_Roll;

import org.litepal.crud.DataSupport;

/**
 * Created by SurpassLi on 2017/2/1.
 */
public class Person_info extends DataSupport{
    private static final String TAG = "Person_info";

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

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getEducational_system() {
        return educational_system;
    }

    public void setEducational_system(String educational_system) {
        this.educational_system = educational_system;
    }

    public String getGrades() {
        return grades;
    }

    public void setGrades(String grades) {
        this.grades = grades;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName_pinyin() {
        return name_pinyin;
    }

    public void setName_pinyin(String name_pinyin) {
        this.name_pinyin = name_pinyin;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIntoSchool_date() {
        return intoSchool_date;
    }

    public void setIntoSchool_date(String intoSchool_date) {
        this.intoSchool_date = intoSchool_date;
    }

    public String getIntoSchool_num() {
        return intoSchool_num;
    }

    public void setIntoSchool_num(String intoSchool_num) {
        this.intoSchool_num = intoSchool_num;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }
}
