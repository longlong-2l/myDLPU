package com.surpassli.www.myapp.gson;

/**
 * Created by SurpassLi on 2017/1/16.
 */
public class Person {
    public  String username;

    public  String message;

    public int userId;

    public  String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public  int getUserId() {
        return userId;
    }

    public  void setUserId(int userId) {
        this.userId = userId;
    }

    public  String getUsername() {
        return username;
    }

    public  void setUsername(String username) {
        this.username = username;
    }

    public  String getMessage() {
        return message;
    }

    public  void setMessage(String message) {
        this.message = message;
    }
}
