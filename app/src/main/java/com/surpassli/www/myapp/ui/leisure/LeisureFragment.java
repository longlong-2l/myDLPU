package com.surpassli.www.myapp.ui.leisure;

import com.surpassli.www.myapp.ui.Base.TopNavigationFragment;

import java.util.ArrayList;

/**
 * Created by SurpassLi on 2017/8/9.
 * LeisureFragment
 */

public class LeisureFragment extends TopNavigationFragment{
    protected static void addChildFragments() {
        if(fragments == null) {
            fragments = new ArrayList<>();
        }else if(fragments.size()>0){
            fragments.clear();
        }
//        暂时注释掉侵权的日报部分
//        fragments.add(new DailyFragment());
        fragments.add(new ScienceFragment());
        fragments.add(new FilmFragment());
    }

    public static LeisureFragment newInstance(){
        addChildFragments();
        return new LeisureFragment();
    }
}
