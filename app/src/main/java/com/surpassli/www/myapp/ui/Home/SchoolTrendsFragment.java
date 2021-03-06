package com.surpassli.www.myapp.ui.Home;

import android.os.Handler;
import android.os.Looper;

import com.surpassli.www.myapp.InitApp;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.event.EVENT;
import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.model.Home.Trends_Model;
import com.surpassli.www.myapp.support.adapter.Home.TrendsAdapter;
import com.surpassli.www.myapp.ui.Base.BaseListFragment;

import java.util.List;

/**
 * Created by SurpassLi on 2017/8/28.
 * SchoolTrendsFragment
 */

public class SchoolTrendsFragment extends BaseListFragment {

    private Trends_Model trends_model;

    @Override
    public String getTitle() {
        return InitApp.getContext().getString(R.string.school_trends);
    }

    @Override
    public void onDataRefresh() {
        trends_model.loadFromNet();
    }

    @Override
    public void initView() {
        trends_model.loadFromCache();
    }

    @Override
    public void bindAdapter() {
        trends_model = new Trends_Model();
        adapter = new TrendsAdapter(getMyActivity(),trends_model);
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
            case EVENT.SCHOOL_TRENDS_NET_SUCCESS:
                List list = eventModel.getDataList();
                adapter.newList(list);
                hideLoading();
                break;
            case EVENT.SCHOOL_TRENDS_NET_FAIL:
                hideLoading();
//                displayNetworkError();
                break;
            case EVENT.SCHOOL_TRENDS_CACHE_SUCCESS:
                adapter.newList(eventModel.getDataList());
                hideLoading();
                break;
            case EVENT.SCHOOL_TRENDS_CACHE_FAIL:
                onDataRefresh();
                break;
        }
    }
}
