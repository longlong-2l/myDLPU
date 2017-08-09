package com.surpassli.www.myapp.ui.leisure;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.surpassli.www.myapp.InitApp;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.ui.Base.BaseListFragment;

/**
 * Created by SurpassLi on 2017/8/9.
 * ScienceFragment
 */

public class ScienceFragment extends BaseListFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_noname, container, false);
        return view;
    }

    @Override
    public String getTitle() {
        return InitApp.AppContext.getString(R.string.science);
    }
}
