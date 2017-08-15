package com.surpassli.www.myapp.ui.leisure;

import android.os.Handler;
import android.os.Looper;

import com.surpassli.www.myapp.InitApp;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.event.EVENT;
import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.model.leisure.ScienceModel;
import com.surpassli.www.myapp.support.Setting;
import com.surpassli.www.myapp.support.adapter.leisure.ScienceAdapter;
import com.surpassli.www.myapp.ui.Base.BaseListFragment;

import java.util.List;

/**
 * Created by SurpassLi on 2017/8/9.
 * ScienceFragment
 */

public class ScienceFragment extends BaseListFragment {
    private ScienceModel model;

    @Override
    public String getTitle() {
        return InitApp.AppContext.getString(R.string.science);
    }

    @Override
    public void onDataRefresh() {
        model.loadFromNet();
    }

    @Override
    public void initView() {
        model.loadFromCache();
    }

    @Override
    public void bindAdapter() {
        model = new ScienceModel();
        adapter = new ScienceAdapter(getActivity(), model);
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
        switch (eventModel.getEventCode()){
            case EVENT.SCIENCE_LOAD_CACHE_SUCCESS:
                if (Setting.autoRefresh){
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            onDataRefresh();
                        }
                    },1500);
                }
                break;
            case EVENT.SCIENCE_LOAD_CACHE_FAILURE:
                break;
            case EVENT.SCIENCE_REFRESH_SUCCESS: //从网络获取数据成功
                List list = eventModel.getDataList();
                adapter.newList(list);
                hideLoading();
                break;
            case EVENT.SCIENCE_REFRESH_FAILURE:  //从网络获取数据失败
                hideLoading();
                displayNetworkError();
                break;
        }
    }
}
