package com.surpassli.www.myapp.ui.Education;

import com.surpassli.www.myapp.InitApp;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.event.EVENT;
import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.model.Education.EducationFileModel;
import com.surpassli.www.myapp.support.adapter.Education.EducationFileAdapter;
import com.surpassli.www.myapp.ui.Base.BaseListFragment;

import java.util.List;

/**
 * Created by SurpassLi on 2017/8/31.
 * EducationFileFragment
 */

public class EducationFileFragment extends BaseListFragment {

    EducationFileModel educationFileModel;

    @Override
    public void initView() {
        educationFileModel.loadFromCache();
    }

    @Override
    public void onDataRefresh() {
        educationFileModel.loadFromNet();
    }

    @Override
    public void bindAdapter() {
        educationFileModel = new EducationFileModel();
        adapter = new EducationFileAdapter(getMyActivity(), educationFileModel);
        recyclerView.setAdapter(adapter);
        displayLoading();
    }

    @Override
    public void addHeader() {

    }

    @Override
    public String getTitle() {
        return InitApp.getContext().getString(R.string.education_files);
    }

    @Override
    public void onEventComing(EventModel eventModel) {
        super.onEventComing(eventModel);
        switch (eventModel.getEventCode()) {
            case EVENT.EDUCATION_FILE_NET_SUCCESS:
                List list = eventModel.getDataList();
                adapter.newList(list);
                hideLoading();
                break;
            case EVENT.EDUCATION_FILE_NET_FAIL:
                hideLoading();
                break;
            case EVENT.EDUCATION_FILE_CACHE_SUCCESS:
                adapter.newList(eventModel.getDataList());
                hideLoading();
                break;
            case EVENT.EDUCATION_FILE_CACHE_FAIL:
                onDataRefresh();
                break;
        }
    }
}
