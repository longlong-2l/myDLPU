package com.surpassli.www.myapp.database.dao;

import android.content.Context;

import com.surpassli.www.myapp.database.DataBaseHelper;
import com.surpassli.www.myapp.model.AccountTableModel;

import java.util.List;

/**
 * Created by SurpassLi on 2017/1/9.
 */
public abstract  class AccountDAO implements DAO<AccountTableModel> {
    public static final String TAG = "AccountDAO";
    private Context context;
    DataBaseHelper dataBaseHelper;

    public AccountDAO(Context context) {
        this.context = context;
//        dataBaseHelper = new DataBaseHelper(context);
    }

    public void add(){

    }

    //将数据写入到数据库中
    public boolean cacheall(List<AccountTableModel> list) {
        return false;
    }

    //清除缓存
    @Override
    public boolean clearCache() {
        return false;
    }

    //从数据库获取数据，若获取成功则添加到list,发送成功消息，失败则发送失败消息(EventBus)
    @Override
    public void loadFromCache() {

    }

    //从网络获取数据,若拉取成功则将拉取得数据放入list中并发送成功消息，并缓存数据，失败则发送失败消息(EventBus)
    @Override
    public void loadFromNet() {

    }
}
