package com.surpassli.www.myapp.model.leisure;

import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.model.IModel;
import com.surpassli.www.myapp.support.utils.HttpUtil;

import java.util.List;

/**
 * Created by SurpassLi on 2017/8/9.
 * ScienceModel
 */

public class ScienceModel implements IModel<ScienceModel>{

    private int replies_count;
    private Image_info image_info = new Image_info();
    private String url;
    private String title;
//    private String scienceDetails;

//    public void setScienceDetails(String scienceDetails) {
//        this.scienceDetails = scienceDetails;
//    }
//
//    public String getScienceDetails() {
//        return scienceDetails;
//    }

    @Override
    public boolean cacheAll(List<ScienceModel> list) {
        return false;
    }

    @Override
    public boolean clearCache() {
        return false;
    }

    @Override
    public void loadFromCache() {

    }

    @Override
    public void loadFromNet() {
//        HttpUtil.sendGetOkhttp(AppApi.SCIENCE_URL);
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
