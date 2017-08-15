package com.surpassli.www.myapp.database.table.NetCenter;

/**
 * Created by SurpassLi on 2017/8/15.
 * NetCenterNewsTable 网络中心新闻动态
 */

public class NetCenterNewsTable {
    public static final String NAME = "NetCenterNewsTable";

    public static final String NET_CENTER_NEWS_TITLE = "";

    public static final String NET_CENTER_NEWS_TIME = "";

    public static final String NET_CENTER_NEWS_URL = "";

    public static final String MARK_ID = "";

    public static final String CREATE_TABLE = "create table " + NAME + "(" +
            NET_CENTER_NEWS_TITLE + " text, " +
            NET_CENTER_NEWS_URL + " text, " +
            NET_CENTER_NEWS_TIME + "text, " +
            MARK_ID + " text)";
}
