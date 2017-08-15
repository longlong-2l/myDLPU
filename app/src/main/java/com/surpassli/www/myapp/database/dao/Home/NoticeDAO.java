package com.surpassli.www.myapp.database.dao.Home;

import com.surpassli.www.myapp.database.dao.DAO;

import java.util.List;

/**
 * Created by SurpassLi on 2017/8/15.
 * NoticeDAO
 */

public class NoticeDAO implements DAO<NoticeDAO> {
    @Override
    public boolean cacheAll(List<NoticeDAO> list) {
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

    }
}
