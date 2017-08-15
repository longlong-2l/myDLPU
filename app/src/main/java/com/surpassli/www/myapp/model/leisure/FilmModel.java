package com.surpassli.www.myapp.model.leisure;

import com.surpassli.www.myapp.database.dao.leisure.FilmDAO;
import com.surpassli.www.myapp.model.IModel;

import java.util.List;

/**
 * Created by SurpassLi on 2017/8/11.
 * FilmModel
 */

public class FilmModel implements IModel<FilmModel> {
    private FilmDAO filmDAO;
    private String url;//内容url
    private String title;//标题
    private String readingCount;//阅读量
    private String detail;//内容
    private String topPic;//标题图片url

    public FilmModel(String url, String title, String readingCount, String detail, String topPic) {
        this();
        this.url = url;
        this.title = title;
        this.readingCount = readingCount;
        this.detail = detail;
        this.topPic = topPic;
    }

    public FilmModel() {
        filmDAO = new FilmDAO();
    }

    @Override
    public boolean cacheAll(List<FilmModel> list) {
        return filmDAO.cacheAll(list);
    }

    @Override
    public boolean clearCache() {
        return filmDAO.clearCache();
    }

    @Override
    public void loadFromCache() {
        filmDAO.loadFromCache();
    }

    @Override
    public void loadFromNet() {
        filmDAO.loadFromNet();
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

    public String getReadingCount() {
        return readingCount;
    }

    public void setReadingCount(String readingCount) {
        this.readingCount = readingCount;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTopPic() {
        return topPic;
    }

    public void setTopPic(String topPic) {
        this.topPic = topPic;
    }
}
