package com.surpassli.www.myapp.ui.life;

import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.ui.Base.TopNavigationFragment;

import java.util.ArrayList;

/**
 * Created by SurpassLi on 2017/8/9.
 * LifeFragment
 */

public class LifeFragment extends TopNavigationFragment{

    protected static void addChildFragments(){
        if (fragments == null) {
            fragments = new ArrayList<>();
        }else if(fragments.size() > 0){
            fragments.clear();
        }
//        fragments.add();
//        fragments.add();
//        fragments.add();
    }

    public static LifeFragment getInstance(){
        addChildFragments();
      return new LifeFragment();
    }

    @Override
    public void onEventComing(EventModel eventModel) {

    }
}
