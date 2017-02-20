package com.surpassli.www.myapp.model.ExerciseYardModel;

import com.surpassli.www.myapp.database.dao.ExerciseDAO;
import com.surpassli.www.myapp.model.IModel;

import java.util.List;

/**
 * Created by SurpassLi on 2017/2/20.
 */

public class ExerciseModel {

    private String name;
    private String content;
    private String subject;

    public ExerciseModel(String content, String name, String subject) {
        this.content = content;
        this.name = name;
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
