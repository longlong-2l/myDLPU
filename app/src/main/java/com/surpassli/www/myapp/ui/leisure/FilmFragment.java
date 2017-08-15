package com.surpassli.www.myapp.ui.leisure;

import android.os.Handler;
import android.os.Looper;

import com.surpassli.www.myapp.InitApp;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.event.EVENT;
import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.model.leisure.FilmModel;
import com.surpassli.www.myapp.support.Setting;
import com.surpassli.www.myapp.support.adapter.leisure.FilmAdapter;
import com.surpassli.www.myapp.ui.Base.BaseListFragment;

/**
 * Created by SurpassLi on 2017/8/9.
 * FilmFragment
 */

public class FilmFragment extends BaseListFragment {

    private FilmModel filmModel;

    @Override
    public String getTitle() {
        return InitApp.AppContext.getString(R.string.film);
    }

    @Override
    public void onDataRefresh() {
        filmModel.loadFromNet();
    }

    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void onEventComing(EventModel eventModel) {
        super.onEventComing(eventModel);
        switch (eventModel.getEventCode()){
            case EVENT.FILM_LOAD_CACHE_SUCCESS:
                if (Setting.autoRefresh){
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            onDataRefresh();
                        }
                    },1500);
                }
                break;
            case EVENT.FILM_LOAD_CACHE_FAILURE:
                onDataRefresh();
                break;
            case EVENT.FILM_REFRESH_SUCCESS:
                adapter.newList(eventModel.getDataList());
                hideLoading();
                break;
            case EVENT.FILM_REFRESH_FAILURE:
                hideLoading();
                break;
        }
    }

    @Override
    public void initView() {
        filmModel.loadFromCache();
    }

    @Override
    public void bindAdapter() {
        filmModel = new FilmModel();
        adapter = new FilmAdapter(getActivity(), filmModel);
        recyclerView.setAdapter(adapter);
        displayLoading();
    }

    @Override
    public void addHeader() {
    }
}
