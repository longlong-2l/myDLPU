package com.surpassli.www.myapp.model.Education;

import com.surpassli.www.myapp.database.dao.Education.EducationFileDAO;
import com.surpassli.www.myapp.model.IModel;

import java.util.List;

/**
 * Created by SurpassLi on 2017/8/31.
 * EducationFileModel
 */

public class EducationFileModel implements IModel<EducationFileModel> {

    private EducationFileDAO fileDAO;
    private String file_title;
    private String file_time;
    private String file_url;

    public EducationFileModel() {
        fileDAO = new EducationFileDAO();
    }

    public EducationFileModel(String notice_url, String notice_title, String notice_time) {
        this();
        this.file_title = notice_title;
        this.file_time = notice_time;
        this.file_url = notice_url;
    }

    @Override
    public boolean cacheAll(List<EducationFileModel> list) {
        return fileDAO.cacheAll(list);
    }

    @Override
    public boolean clearCache() {
        return fileDAO.clearCache();
    }

    @Override
    public void loadFromCache() {
        fileDAO.loadFromCache();
    }

    @Override
    public void loadFromNet() {
        fileDAO.loadFromNet();
    }

    public String getFile_title() {
        return file_title;
    }

    public void setFile_title(String file_title) {
        this.file_title = file_title;
    }

    public String getFile_time() {
        return file_time;
    }

    public void setFile_time(String file_time) {
        this.file_time = file_time;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }
}
