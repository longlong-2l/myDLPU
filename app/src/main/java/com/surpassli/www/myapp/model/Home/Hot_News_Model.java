package com.surpassli.www.myapp.model.Home;

import com.surpassli.www.myapp.database.dao.Home.HotNewsDAO;
import com.surpassli.www.myapp.model.IModel;

import java.util.List;

/**
 * Created by SurpassLi on 2017/8/15.
 * Notice_Model
 */

public class Hot_News_Model implements IModel<Hot_News_Model> {

    private HotNewsDAO hotNewsDAO;
    private String Hot_News_title;
    private String Hot_News_time;
    private String Hot_News_url;

    public Hot_News_Model() {
        hotNewsDAO = new HotNewsDAO();
    }

    public Hot_News_Model(String hot_news_url, String hot_news_title, String hot_news_time) {
        this();
        this.Hot_News_title = hot_news_title;
        this.Hot_News_time = hot_news_time;
        this.Hot_News_url = hot_news_url;
    }

    @Override
    public boolean cacheAll(List<Hot_News_Model> list) {
        return hotNewsDAO.cacheAll(list);
    }

    @Override
    public boolean clearCache() {
        return hotNewsDAO.clearCache();
    }

    @Override
    public void loadFromCache() {
        hotNewsDAO.loadFromCache();
    }

    @Override
    public void loadFromNet() {
        hotNewsDAO.loadFromNet();
    }

    public String getHot_News_title() {
        return Hot_News_title;
    }

    public void setHot_News_title(String hot_News_title) {
        Hot_News_title = hot_News_title;
    }

    public String getHot_News_time() {
        return Hot_News_time;
    }

    public void setHot_News_time(String hot_News_time) {
        Hot_News_time = hot_News_time;
    }

    public String getHot_News_url() {
        return Hot_News_url;
    }

    public void setHot_News_url(String hot_News_url) {
        Hot_News_url = hot_News_url;
    }
}
