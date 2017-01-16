package com.surpassli.www.myapp.gson.login;

/**
 * Created by SurpassLi on 2017/1/15.
 */
public class Person {
    private int userid;
    private String username ;
    private String token;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
