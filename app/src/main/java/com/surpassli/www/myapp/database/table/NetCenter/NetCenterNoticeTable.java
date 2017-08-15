package com.surpassli.www.myapp.database.table.NetCenter;

/**
 * Created by SurpassLi on 2017/8/15.
 * NetCenterNoticeTable 网络中心通知公告
 */

public class NetCenterNoticeTable {
    public static final String NAME = "NetCenterNoticeTable";

    public static final String NET_CENTER_NOTICE_URL = "url";

    public static final String NET_CENTER_NOTICE_TITLE = "title";

    public static final String MARK_ID = "mark_id";

    public static final String NET_CENTER_NOTICE_TIME = "time";

    public static final String CREATE_TABLE = "create table " + NAME + "(" +
            MARK_ID + " text, " +
            NET_CENTER_NOTICE_TITLE + " text, " +
            NET_CENTER_NOTICE_TIME + " text, " +
            NET_CENTER_NOTICE_URL + " text)";
}
