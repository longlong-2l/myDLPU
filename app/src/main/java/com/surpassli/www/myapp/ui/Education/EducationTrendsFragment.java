package com.surpassli.www.myapp.ui.Education;

import com.surpassli.www.myapp.InitApp;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.event.EVENT;
import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.model.Education.EducationTrendsModel;
import com.surpassli.www.myapp.support.adapter.Education.EducationTrendsAdapter;
import com.surpassli.www.myapp.ui.Base.BaseListFragment;

import java.util.List;

/**
 * Created by SurpassLi on 2017/8/31.
 * EducationTrendsFragment
 */

public class EducationTrendsFragment extends BaseListFragment {

    EducationTrendsModel educationTrendsModel;

    @Override
    public void initView() {
        educationTrendsModel.loadFromCache();
    }

    @Override
    public void onDataRefresh() {
        educationTrendsModel.loadFromNet();
    }

    @Override
    public void bindAdapter() {
        educationTrendsModel = new EducationTrendsModel();
        adapter = new EducationTrendsAdapter(getMyActivity(), educationTrendsModel);
        recyclerView.setAdapter(adapter);
        displayLoading();
    }

    @Override
    public void addHeader() {

    }

    @Override
    public String getTitle() {
        return InitApp.getContext().getString(R.string.education_trends);
    }

    @Override
    public void onEventComing(EventModel eventModel) {
        super.onEventComing(eventModel);
        switch (eventModel.getEventCode()){
            case EVENT.EDUCATION_TRENDS_NET_SUCCESS:
                List list = eventModel.getDataList();
                adapter.newList(list);
                hideLoading();
                break;
            case EVENT.EDUCATION_TRENDS_NET_FAIL:
                hideLoading();
                break;
            case EVENT.EDUCATION_TRENDS_CACHE_SUCCESS:
                adapter.newList(eventModel.getDataList());
                hideLoading();
                break;
            case EVENT.EDUCATION_TRENDS_CACHE_FAIL:
                onDataRefresh();
                break;
        }
    }
}
