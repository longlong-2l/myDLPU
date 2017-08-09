package com.surpassli.www.myapp.view.base;

/**
 * Created by SurpassLi on 2017/8/9.
 * BaseRecyclerView
 */

public interface BaseRecyclerView extends BaseView<BaseRecyclerView>{
    boolean trySetupRefreshLayout();
    void onDataRefresh();
    void bindAdapter();
    void addHeader();
}
