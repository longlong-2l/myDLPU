package com.surpassli.www.myapp.model.Home;

import com.surpassli.www.myapp.database.dao.Home.NoticeDAO;
import com.surpassli.www.myapp.database.dao.Home.TrendsDAO;
import com.surpassli.www.myapp.model.IModel;

import java.util.List;

/**
 * Created by SurpassLi on 2017/8/15.
 * Notice_Model
 */

public class Trends_Model implements IModel<Trends_Model> {

    private TrendsDAO trendsDAO;
    private String trends_title;
    private String trends_time;
    private String trends_url;

    public Trends_Model() {
        trendsDAO = new TrendsDAO();
    }

    public Trends_Model(String trends_url, String trends_title, String trends_time) {
        this();
        this.trends_title = trends_title;
        this.trends_time = trends_time;
        this.trends_url = trends_url;
    }

    @Override
    public boolean cacheAll(List<Trends_Model> list) {
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
