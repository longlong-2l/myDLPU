package com.surpassli.www.myapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by dell on 2017/1/9.
 */
public class FragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mDatas;
    public FragmentAdapter(FragmentManager fm,List<Fragment> mDatas) {
        super(fm);
        this.mDatas = mDatas;
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
