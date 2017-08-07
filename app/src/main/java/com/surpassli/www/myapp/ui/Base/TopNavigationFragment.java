package com.surpassli.www.myapp.ui.Base;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.support.adapter.Fragment.FragmentAdapter;

import java.util.List;

/**
 * Created by SurpassLi on 2017/8/7.
 *
 */

public class TopNavigationFragment extends BaseFragment {

    protected static List<BaseFragment> fragments;
    protected FragmentAdapter fragmentAdapter;
    protected ViewPager viewPager;
    protected SmartTabLayout smartTabLayout;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_top_navigation;
    }

    @Override
    protected void init() {
        fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), fragments);
        viewPager = (ViewPager) parentView.findViewById(R.id.inner_viewpager);
        viewPager.setAdapter(fragmentAdapter);
        smartTabLayout = (SmartTabLayout) getActivity().findViewById(R.id.tab_layout);
        smartTabLayout.setVisibility(View.VISIBLE);
        smartTabLayout.setViewPager(viewPager);
    }

    public static void clearChildFragment() {
        if (fragments != null) {
            fragments.clear();
        }
    }
}
