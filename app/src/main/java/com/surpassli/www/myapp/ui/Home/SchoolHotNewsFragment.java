package com.surpassli.www.myapp.ui.Home;

import android.os.Handler;
import android.os.Looper;

import com.surpassli.www.myapp.InitApp;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.event.EVENT;
import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.model.Home.Hot_News_Model;
import com.surpassli.www.myapp.support.adapter.Home.HotNewsAdapter;
import com.surpassli.www.myapp.ui.Base.BaseListFragment;

import java.util.List;

/**
 * Created by SurpassLi on 2017/8/28.
 * SchoolHotNewsFragment
 */

public class SchoolHotNewsFragment extends BaseListFragment {

    private Hot_News_Model hot_news_model;

    @Override
    public String getTitle() {
        return InitApp.getContext().getString(R.string.school_hot_news);
    }

    @Override
    public void onDataRefresh() {
        hot_news_model.loadFromNet();
    }

    @Override
    public void initView() {
        hot_news_model.loadFromCache();
    }

    @Override
    public void bindAdapter() {
        hot_news_model = new Hot_News_Model();
        adapter = new HotNewsAdapter(getMyActivity(),hot_news_model);
        recyclerView.setAdapter(adapter);
        displayLoading();
    }

    @Override
    public void addHeader() {

    }

    private static Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void onEventComing(EventModel eventModel) {
        super.onEventComing(eventModel);
        switch (eventModel.getEventCode()) {
            case EVENT.SCHOOL_HOT_NEWS_NET_SUCCESS:
                List list = eventModel.getDataList();
                adapter.newList(list);
                hideLoading();
                break;
            case EVENT.SCHOOL_HOT_NEWS_NET_FAIL:
                hideLoading();
//                displayNetworkError();
                break;
            case EVENT.SCHOOL_HOT_NEWS_CACHE_SUCCESS:
                adapter.newList(eventModel.getDataList());
                hideLoading();
                break;
            case EVENT.SCHOOL_HOT_NEWS_CACHE_FAIL:
                onDataRefresh();
                break;
        }
    }
}
