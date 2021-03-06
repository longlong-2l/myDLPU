package com.surpassli.www.myapp.ui.Home;

import com.surpassli.www.myapp.InitApp;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.event.EVENT;
import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.model.Home.Notice_Model;
import com.surpassli.www.myapp.support.adapter.Home.NoticeAdapter;
import com.surpassli.www.myapp.ui.Base.BaseListFragment;

import java.util.List;

/**
 * Created by SurpassLi on 2017/8/15.
 * NoticeFragment
 */

public class NoticeFragment extends BaseListFragment {

    private Notice_Model notice_model;

    @Override
    public void initView() {
        notice_model.loadFromCache();
    }

    @Override
    public void bindAdapter() {
        notice_model = new Notice_Model();
        adapter = new NoticeAdapter(getMyActivity(), notice_model);
        recyclerView.setAdapter(adapter);
        displayLoading();
    }

    @Override
    public void addHeader() {

    }

    @Override
    public String getTitle() {
        return InitApp.getContext().getString(R.string.school_notice);
    }

    @Override
    public void onDataRefresh() {
        notice_model.loadFromNet();
    }

    @Override
    public void onEventComing(EventModel eventModel) {
        super.onEventComing(eventModel);
        switch (eventModel.getEventCode()) {
            case EVENT.SCHOOL_NOTICES_NET_SUCCESS:
                List list = eventModel.getDataList();
                adapter.newList(list);
                hideLoading();
                break;
            case EVENT.SCHOOL_NOTICES_NET_FAIL:
                hideLoading();
//                displayNetworkError();
                break;
            case EVENT.SCHOOL_NOTICES_CACHE_SUCCESS:
                adapter.newList(eventModel.getDataList());
                hideLoading();
                break;
            case EVENT.SCHOOL_NOTICES_CACHE_FAIL:
                onDataRefresh();
                break;
        }
    }
}
