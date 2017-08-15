package com.surpassli.www.myapp.model.Home;

import com.surpassli.www.myapp.database.dao.Home.SchoolNewsDAO;
import com.surpassli.www.myapp.model.IModel;

import java.util.List;

/**
 * Created by SurpassLi on 2017/8/15.
 * School_News_Model
 */

public class School_News_Model implements IModel<School_News_Model> {

    private SchoolNewsDAO schoolNewsDAO;
    private String school_news_title;
    private String school_news_url;
    private String school_news_time;

    public School_News_Model() {
        schoolNewsDAO = new SchoolNewsDAO();
    }

    public School_News_Model(String url, String title, String time) {
        this();
        this.school_news_url = url;
        this.school_news_title = title;
        this.school_news_time = time;
    }

    @Override
    public boolean cacheAll(List<School_News_Model> list) {
        return schoolNewsDAO.cacheAll(list);
    }

    @Override
    public boolean clearCache() {
        return schoolNewsDAO.clearCache();
    }

    @Override
    public void loadFromCache() {
        schoolNewsDAO.loadFromCache();
    }

    @Override
    public void loadFromNet() {
        schoolNewsDAO.loadFromNet();
    }

    public String getSchool_news_title() {
        return school_news_title;
    }

    public void setSchool_news_title(String school_news_title) {
        this.school_news_title = school_news_title;
    }

    public String getSchool_news_url() {
        return school_news_url;
    }

    public void setSchool_news_url(String school_news_url) {
        this.school_news_url = school_news_url;
    }

    public String getSchool_news_time() {
        return school_news_time;
    }

    public void setSchool_news_time(String school_news_time) {
        this.school_news_time = school_news_time;
    }
}
