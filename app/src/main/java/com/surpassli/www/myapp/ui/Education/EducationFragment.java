package com.surpassli.www.myapp.ui.Education;

import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.ui.Base.TopNavigationFragment;

import java.util.ArrayList;

/**
 * Created by SurpassLi on 2017/8/31.
 * EducationFragment
 */

public class EducationFragment extends TopNavigationFragment {

    protected static void addChildFragment() {
        if (fragments == null) {
            fragments = new ArrayList<>();
        } else {
            if (fragments.size() > 0) {
                fragments.clear();
            }
        }
        fragments.add(new EducationNoticeFragment());
        fragments.add(new EducationFileFragment());
        fragments.add(new EducationTrendsFragment());

    }

    public static EducationFragment newInstance() {
        addChildFragment();
        return new EducationFragment();
    }

    @Override
    public void onEventComing(EventModel eventModel) {

    }
}
