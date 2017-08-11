package com.surpassli.www.myapp.api;

/**
 * Created by dell on 2017/1/10.
 */
public class AppApi {


    public static final String HOST = "https://dlpu-aao-api.xu42.cn/v1";

    /**
     * 时间戳
     */
    public static final String TIME = HOST + "/time";
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

    /**
     * 等级成绩
     */
    public static final String MY_LEVEL_GRADE = HOST + "/user/levelScore?";

    /**
     * 课程表
     */
    public static final String MY_COURSE_TABLE = HOST + "/user/timetable?";

    /**
     * 当前周次
     */
    public static final String MY_CURRENT_WEEK = HOST + "/user/currentweek?";

    /**
     * 考试安排
     */
    public static final String MY_EXAM_PLAN = HOST + "/user/examsinfo?";

    /**
     * 密码修改
     */
    public static final String MY_PASSWORD_REPAIR = HOST + "/user/password?";

    /**
     * 新闻动态
     */
    public static final String NEWS = HOST + "/news/currentEvents";
    /**
     * 通知公告
     */
    public static final String NOTICE = HOST + "/news/notice";
    /**
     * 教务文件
     */
    public static final String EDUCATION_FILE = HOST + "/news/teachingFiles";

    /**
     * 意见反馈
     */
    public static final String FEEDBACK = "https://mydlpu.xu42.cn/api/mina/feedback";

    /**
     * 科学
     */
    public static String SCIENCE_URL = "http://www.guokr.com/apis/minisite/article.json?retrieve_type=by_channel&channel_key=hot";

    //简书基础list的url 电影
    public static final String JianShu_List_URL= "http://www.jianshu.com/c/1hjajt";
}
