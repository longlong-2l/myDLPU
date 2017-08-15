package com.surpassli.www.myapp.ui.Home;

import android.os.Handler;
import android.os.Looper;

import com.surpassli.www.myapp.InitApp;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.event.EVENT;
import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.model.Home.School_News_Model;
import com.surpassli.www.myapp.support.Setting;
import com.surpassli.www.myapp.support.adapter.Home.SchoolNewsAdapter;
import com.surpassli.www.myapp.ui.Base.BaseListFragment;

import java.util.List;

/**
 * Created by SurpassLi on 2017/8/15.
 * SchoolNewsFragment
 */

public class SchoolNewsFragment extends BaseListFragment {

    private School_News_Model school_news_model;

    @Override
    public String getTitle() {
        return InitApp.getContext().getString(R.string.school_news);
    }

    @Override
    public void onDataRefresh() {
        school_news_model.loadFromNet();
    }

    @Override
    public void initView() {
        school_news_model.loadFromCache();
    }

    @Override
    public void bindAdapter() {
        school_news_model = new School_News_Model();
        adapter = new SchoolNewsAdapter(getMyActivity(),school_news_model);
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
            case EVENT.SCHOOL_NEWS_NET_SUCCESS:
                List list = eventModel.getDataList();
                adapter.newList(list);
                hideLoading();
                break;
            case EVENT.SCHOOL_NEWS_NET_FAIL:
                hideLoading();
//                displayNetworkError();
                break;
            case EVENT.SCHOOL_NEWS_CACHE_SUCCESS:
                if (Setting.autoRefresh) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            onDataRefresh();
                        }
                    }, 1500);
                }
                break;
            case EVENT.SCHOOL_NEWS_CACHE_FAIL:
                onDataRefresh();
                break;
        }
    }
}
