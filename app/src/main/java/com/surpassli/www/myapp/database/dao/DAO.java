package com.surpassli.www.myapp.database.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SurpassLi on 2017/1/9.
 */
public interface DAO<M> {

    /**
     *将数据写入到数据库中
     * @param list 数据源，需要写入的数据
     * @return
     */
    boolean cacheall(ArrayList<M> list);

    /**
     * 清除缓存
     * @return
     */
    boolean clearCache();

    /**
     * 从数据库获取数据，
     * 若获取成功则添加到list,发送成功消息，失败则发送失败消息(EventBus)
     */
    void loadFromCache();

    /**
     * 从网络获取数据
     * 若拉取成功则将拉取得数据放入list中并发送成功消息，并缓存数据，失败则发送失败消息(EventBus)
     */
    void loadFromNet();
}
