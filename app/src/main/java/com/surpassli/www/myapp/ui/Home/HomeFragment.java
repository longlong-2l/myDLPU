package com.surpassli.www.myapp.ui.Home;

import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.ui.Base.TopNavigationFragment;

import java.util.ArrayList;

/**
 * Created by SurpassLi on 2017/8/15.
 * HomeFragment
 */

public class HomeFragment extends TopNavigationFragment {

    protected static void addChildFragment() {
        if (fragments == null) {
            fragments = new ArrayList<>();
        } else if (fragments.size() > 0) {
            fragments.clear();
        }
        fragments.add(new SchoolNewsFragment());
        fragments.add(new NoticeFragment());
    }

    public static HomeFragment newInstance(){
        addChildFragment();
        return new HomeFragment();
    }

    @Override
    public void onEventComing(EventModel eventModel) {

    }
}
