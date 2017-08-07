package com.surpassli.www.myapp.ui.Base;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by SurpassLi on 2017/8/7.
 *
 */

public abstract class BaseFragment extends Fragment {
    public Activity activity;
    protected View parentView = null;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
    }

    public Activity getMyActivity() {
        return activity;
    }

    private void loadConfig(){

    }

    protected abstract int getLayoutID();
    protected  abstract void init();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parentView = View.inflate(getContext(),getLayoutID(),null);
        init();
        loadConfig();
        return parentView;
    }
}
