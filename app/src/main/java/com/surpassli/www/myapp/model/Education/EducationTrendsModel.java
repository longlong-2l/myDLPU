package com.surpassli.www.myapp.model.Education;

import com.surpassli.www.myapp.database.dao.Education.EducationTrendsDAO;
import com.surpassli.www.myapp.model.IModel;

import java.util.List;

/**
 * Created by SurpassLi on 2017/8/31.
 * EducationTrendsModel
 */

public class EducationTrendsModel implements IModel<EducationTrendsModel> {
    private EducationTrendsDAO trendsDAO;
    private String trends_title;
    private String trends_time;
    private String trends_url;

    public EducationTrendsModel() {
        trendsDAO = new EducationTrendsDAO();
    }

    public EducationTrendsModel(String url, String title, String time) {
        this();
        this.trends_title = title;
        this.trends_url = url;
        this.trends_time = time;
    }

    @Override
    public boolean cacheAll(List<EducationTrendsModel> list) {
        return trendsDAO.cacheAll(list);
    }

    @Override
    public boolean clearCache() {
        return trendsDAO.clearCache();
    }

    @Override
    public void loadFromCache() {
        trendsDAO.loadFromCache();
    }

    @Override
    public void loadFromNet() {
        trendsDAO.loadFromNet();
    }

    public String getTrends_title() {
        return trends_title;
    }

    public void setTrends_title(String trends_title) {
        this.trends_title = trends_title;
    }

    public String getTrends_time() {
        return trends_time;
    }

    public void setTrends_time(String trends_time) {
        this.trends_time = trends_time;
    }

    public String getTrends_url() {
        return trends_url;
    }

    public void setTrends_url(String trends_url) {
        this.trends_url = trends_url;
    }
}
