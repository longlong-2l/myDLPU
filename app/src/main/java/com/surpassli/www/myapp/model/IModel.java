package com.surpassli.www.myapp.model;

import java.util.List;

/**
 * Created by SurpassLi on 2017/1/9.
 * IModel
 */
public interface IModel<M extends IModel> {

    /**
     *将数据写入到数据库中
     * @param list 数据源，需要写入的数据
     */
    boolean cacheAll(List<M> list);

    /**
     * 清除缓存
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
