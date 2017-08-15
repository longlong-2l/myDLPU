package com.surpassli.www.myapp.model.leisure;

import com.surpassli.www.myapp.database.dao.leisure.ScienceDAO;
import com.surpassli.www.myapp.model.IModel;

import java.util.List;

/**
 * Created by SurpassLi on 2017/8/9.
 * ScienceModel
 */

public class ScienceModel implements IModel<ScienceModel>{

    private ScienceDAO scienceDAO;
    private int replies_count;
    private Image_info image_info = new Image_info();
    private String url;
    private String title;
    private String scienceDetails;

    public String getScienceDetails() {
        return scienceDetails;
    }

    public void setScienceDetails(String scienceDetails) {
        this.scienceDetails = scienceDetails;
    }

    public ScienceModel() {
        scienceDAO = new ScienceDAO();
    }

    @Override
    public boolean cacheAll(List<ScienceModel> list) {
        return scienceDAO.cacheAll(list);
    }

    @Override
    public boolean clearCache() {
        return scienceDAO.clearCache();
    }

    @Override
    public void loadFromCache() {
        scienceDAO.loadFromCache();
    }

    @Override
    public void loadFromNet() {
        scienceDAO.loadFromNet();
    }

    public class Image_info {
        String url;

        public String getUrl() {
            return url;
        }
        public void setUrl(String url) {
            this.url = url;
        }
    }

    public int getReplies_count() {
        return replies_count;
    }

    public void setReplies_count(int replies_count) {
        this.replies_count = replies_count;
    }

    public Image_info getImage_info() {
        return image_info;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
