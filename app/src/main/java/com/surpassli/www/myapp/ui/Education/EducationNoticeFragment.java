package com.surpassli.www.myapp.ui.Education;

import com.surpassli.www.myapp.InitApp;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.event.EVENT;
import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.model.Education.EducationNoticesModel;
import com.surpassli.www.myapp.support.adapter.Education.EducationNoticesAdapter;
import com.surpassli.www.myapp.ui.Base.BaseListFragment;

import java.util.List;

/**
 * Created by SurpassLi on 2017/8/31.
 * EducationNoticeFragment
 */

public class EducationNoticeFragment extends BaseListFragment{

    EducationNoticesModel educationNoticesModel;
    @Override
    public void initView() {
        educationNoticesModel.loadFromCache();
    }

    @Override
    public void onDataRefresh() {
        educationNoticesModel.loadFromNet();
    }

    @Override
    public void bindAdapter() {
        educationNoticesModel = new EducationNoticesModel();
        adapter = new EducationNoticesAdapter(getMyActivity(), educationNoticesModel);
        recyclerView.setAdapter(adapter);
        displayLoading();
    }

    @Override
    public void addHeader() {

    }

    @Override
    public String getTitle() {
        return InitApp.getContext().getString(R.string.education_notices);
    }

    @Override
    public void onEventComing(EventModel eventModel) {
        super.onEventComing(eventModel);
        switch (eventModel.getEventCode()){
            case EVENT.EDUCATION_NOTICES_NET_SUCCESS:
                List list = eventModel.getDataList();
                adapter.newList(list);
                hideLoading();
                break;
            case EVENT.EDUCATION_NOTICES_NET_FAIL:
                hideLoading();
                break;
            case EVENT.EDUCATION_NOTICES_CACHE_SUCCESS:
                adapter.newList(eventModel.getDataList());
                hideLoading();
                break;
            case EVENT.EDUCATION_NOTICES_CACHE_FAIL:
                onDataRefresh();
                break;
        }
    }
}
