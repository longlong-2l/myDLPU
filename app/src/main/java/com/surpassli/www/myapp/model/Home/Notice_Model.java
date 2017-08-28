package com.surpassli.www.myapp.model.Home;

import com.surpassli.www.myapp.database.dao.Home.NoticeDAO;
import com.surpassli.www.myapp.model.IModel;

import java.util.List;

/**
 * Created by SurpassLi on 2017/8/15.
 * Notice_Model
 */

public class Notice_Model implements IModel<Notice_Model> {

    private NoticeDAO noticeDAO;
    private String notice_title;
    private String notice_time;
    private String notice_url;

    public Notice_Model() {
        noticeDAO = new NoticeDAO();
    }

    public Notice_Model(String notice_url,String notice_title, String notice_time) {
        this();
        this.notice_title = notice_title;
        this.notice_time = notice_time;
        this.notice_url = notice_url;
    }

    @Override
    public boolean cacheAll(List<Notice_Model> list) {
        return noticeDAO.cacheAll(list);
    }

    @Override
    public boolean clearCache() {
        return noticeDAO.clearCache();
    }

    @Override
    public void loadFromCache() {
        noticeDAO.loadFromCache();
    }

    @Override
    public void loadFromNet() {
        noticeDAO.loadFromNet();
    }

    public String getNotice_title() {
        return notice_title;
    }

    public void setNotice_title(String notice_title) {
        this.notice_title = notice_title;
    }

    public String getNotice_time() {
        return notice_time;
    }

    public void setNotice_time(String notice_time) {
        this.notice_time = notice_time;
    }

    public String getNotice_url() {
        return notice_url;
    }

    public void setNotice_url(String notice_url) {
        this.notice_url = notice_url;
    }
}
