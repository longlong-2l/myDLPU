package com.surpassli.www.myapp.support.adapter.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.surpassli.www.myapp.ui.Base.BaseFragment;

import java.util.List;

/**
 * Created by SurpassLi on 2017/1/9.
 * FragmentAdapter
 */
public class FragmentAdapter extends FragmentStatePagerAdapter {
    private List<BaseFragment> mDatas;

    public FragmentAdapter(FragmentManager fm, List<BaseFragment> mDatas) {
        super(fm);
        this.mDatas = mDatas;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mDatas.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }
}
