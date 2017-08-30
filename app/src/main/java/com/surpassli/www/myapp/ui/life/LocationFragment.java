package com.surpassli.www.myapp.ui.life;

import android.os.Bundle;

import com.surpassli.www.myapp.InitApp;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.ui.Base.BaseFragment;

public class LocationFragment extends BaseFragment {

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_location;
    }

    @Override
    protected void init() { }

    @Override
    public String getTitle() {
        return InitApp.getContext().getString(R.string.location);
    }

    @Override
    public void onEventComing(EventModel eventModel) { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
