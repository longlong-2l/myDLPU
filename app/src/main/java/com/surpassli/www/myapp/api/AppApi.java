package com.surpassli.www.myapp.api;

/**
 * Created by dell on 2017/1/10.
 */
public class AppApi {

    public static final String HOST = "https://dlpu-aao-api.xu42.cn/v1";
    /**
     * 登录
     */
    public static final String LOGIN = HOST + "/token?";
    /**
     * 账户信息
     */
    public static final String MY_ACCOUNT = HOST + "/user/info?";
    /**
     * 课程信息
     */
    public static final String MY_COURSE = HOST + "/user/courseScore?";
}
