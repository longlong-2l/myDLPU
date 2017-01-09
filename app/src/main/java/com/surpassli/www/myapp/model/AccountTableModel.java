package com.surpassli.www.myapp.model;

import java.util.List;

/**
 * Created by SurpassLi on 2017/1/9.
 */
public class AccountTableModel {
    private int id;
    private String username;
    private String password;
    private boolean login;
//    //往数据库里写数据
//    @Override
//    public boolean cacheall(List<AccountTableModel> list) {
//        return false;
//    }
//    //清除缓存
//    @Override
//    public boolean clearCache() {
//        return false;
//    }
//    //从数据库里获取数据
//    @Override
//    public void loadFromCache() {
//
//    }
//    //从网络拉取数据
//    @Override
//    public void loadFromNet() {
//
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }
}
