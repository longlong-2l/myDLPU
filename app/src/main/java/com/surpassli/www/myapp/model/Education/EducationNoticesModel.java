package com.surpassli.www.myapp.model.Education;

import com.surpassli.www.myapp.database.dao.Education.EducationNoticesDAO;
import com.surpassli.www.myapp.database.dao.Home.NoticeDAO;
import com.surpassli.www.myapp.model.IModel;

import java.util.List;

/**
 * Created by SurpassLi on 2017/8/31.
 * EducationNoticesModel
 */

public class EducationNoticesModel implements IModel<EducationNoticesModel> {

    private EducationNoticesDAO noticesDAO;
    private String notices_title;
    private String notices_time;
    private String notices_url;

    public EducationNoticesModel() {
        noticesDAO = new EducationNoticesDAO();
    }

    public EducationNoticesModel(String notice_url, String notice_title, String notice_time) {
        this();
        this.notices_title = notice_title;
        this.notices_time = notice_time;
        this.notices_url = notice_url;
    }

    @Override
    public boolean cacheAll(List<EducationNoticesModel> list) {
        return noticesDAO.cacheAll(list);
    }

    @Override
    public boolean clearCache() {
        return noticesDAO.clearCache();
    }

    @Override
    public void loadFromCache() {
        noticesDAO.loadFromCache();
    }

    @Override
    public void loadFromNet() {
        noticesDAO.loadFromNet();
    }

    public String getNotices_title() {
        return notices_title;
    }

    public void setNotices_title(String notices_title) {
        this.notices_title = notices_title;
    }

    public String getNotices_time() {
        return notices_time;
    }

    public void setNotices_time(String notices_time) {
        this.notices_time = notices_time;
    }

    public String getNotices_url() {
        return notices_url;
    }

    public void setNotices_url(String notices_url) {
        this.notices_url = notices_url;
    }
}
