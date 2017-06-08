package com.surpassli.www.myapp.model;

/**
 * Created by SurpasLi on 2017/2/17.
 */

public class ExerciseYard {

    private String name;
    private String content;
    private String subject;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public ExerciseYard(){}

    public ExerciseYard(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
