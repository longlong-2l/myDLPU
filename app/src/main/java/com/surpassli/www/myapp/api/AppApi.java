package com.surpassli.www.myapp.api;

/**
 * Created by dell on 2017/1/10.
 * AppApi
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
     * 意见反馈
     */
    public static final String FEEDBACK = "https://mydlpu.xu42.cn/api/mina/feedback";

    /**
     * 科学
     */
    public static String SCIENCE_URL = "http://www.guokr.com/apis/minisite/article.json?retrieve_type=by_channel&channel_key=hot";

    //简书基础list的url 电影
    public static final String JianShu_List_URL= "http://www.jianshu.com/c/1hjajt";
    //简书基础url
    public static final String JianShu_Base_URL="http://www.jianshu.com";

    /**
     * 校内基础
     */
    public  static final String SCHOOL = "http://www.dlpu.edu.cn";
    /**
     * 校内新闻_html
     */
    public  static final String SCHOOL_NEWS = "http://www.dlpu.edu.cn/more/1";

    /**
     * 校园通知_html
     */
    public  static final String SCHOOL_NOTICE = "http://www.dlpu.edu.cn/more/2";

    /**
     * 校园动态_html
     */
    public  static final String SCHOOL_NEWS_STATUS = "http://www.dlpu.edu.cn/more/22";

    /**
     * 校内头条新闻_html
     */
    public  static final String SCHOOL_NEWS_HOT = "http://www.dlpu.edu.cn/more/37";

    /**
     * 教务通知公告_html
     */
    public  static final String EDUCATION_NOTICE = "http://jiaowu.dlpu.edu.cn/more/3";

    /**
     * 教务文件_html
     */
    public  static final String EDUCATION_FILE = "http://jiaowu.dlpu.edu.cn/more/4";

    /**
     * 教务新闻_html
     */
    public  static final String EDUCATION_NEWS = "http://jiaowu.dlpu.edu.cn/more/2";
}
