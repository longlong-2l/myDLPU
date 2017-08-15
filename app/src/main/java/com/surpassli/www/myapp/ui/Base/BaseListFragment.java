package com.surpassli.www.myapp.ui.Base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.surpassli.www.myapp.InitApp;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.support.adapter.BaseListAdapter;
import com.surpassli.www.myapp.view.base.BaseRecyclerView;

/**
 * Created by SurpassLi on 2017/8/9.
 * BaseListFragment
 */

public abstract class BaseListFragment extends BaseFragment implements BaseRecyclerView {
    protected RecyclerView.LayoutManager layoutManager;
    protected ImageView list_empty;
    protected SwipeRefreshLayout swipeRefreshLayout;
    protected RecyclerView recyclerView;
    protected BaseListAdapter adapter;
    protected ProgressBar progressBar;

    @Override
    protected int getLayoutID() {
        return R.layout.layout_common_list;
    }

    @Override
    protected void init() {
        layoutManager = new LinearLayoutManager(InitApp.AppContext);
        list_empty = (ImageView) parentView.findViewById(R.id.common_list_empty);
        progressBar = (ProgressBar) parentView.findViewById(R.id.progressBar);
        recyclerView = (RecyclerView) parentView.findViewById(R.id.common_list_recycler);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        bindAdapter();  //子类绑定Adapter
        trySetupRefreshLayout();  //绑定刷新控件
        addHeader();    //加载头部信息
        initView();     //子类去实现
    }

    @Override
    public boolean trySetupRefreshLayout() {
        swipeRefreshLayout = (SwipeRefreshLayout) parentView.findViewById(R.id.common_list_refresh);
        if (swipeRefreshLayout == null) {
            return false;
        }

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                list_empty.setVisibility(View.GONE);
                onDataRefresh();
            }
        });
        return true;
    }

    @Override
    public void onDataRefresh() {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void displayLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void displayNetworkError() {
        if (adapter.getItemCount() == 0) {
            list_empty.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onEventComing(EventModel eventModel) {

    }
}
