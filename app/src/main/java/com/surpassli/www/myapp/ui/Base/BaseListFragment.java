package com.surpassli.www.myapp.ui.Base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.surpassli.www.myapp.InitApp;
import com.surpassli.www.myapp.R;
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

    @Override
    protected int getLayoutID() {
        return R.layout.layout_common_list;
    }

    @Override
    protected void init() {
        layoutManager = new LinearLayoutManager(InitApp.AppContext);
        list_empty = (ImageView) parentView.findViewById(R.id.common_list_empty);
        recyclerView = (RecyclerView) parentView.findViewById(R.id.common_list_recycler);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        bindAdapter();
        trySetupRefreshLayout();
        initView();
    }

    @Override
    public String getTitle() {
        return null;
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
            return;
        }
    }

    @Override
    public void displayLoading() {

    }

    @Override
    public void hideLoading() {
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
}
