package com.surpassli.www.myapp.presenter.Home;

import android.app.Activity;
import android.support.v7.widget.Toolbar;

import com.surpassli.www.myapp.View.home.HomeView;

/**
 * Created by SurpassLi on 2017/8/4.
 * Home的具体Presenter
 */

public class HomePresenterImpl implements HomePresenter {

    private HomeView homeView;

    public HomePresenterImpl(HomeView view) {
        this.homeView = view;
    }

    @Override
    public void initLization() {
        homeView.initView();
    }

    @Override
    public void clearAllFragments() {

    }

    @Override
    public boolean isDrawerOpen() {
        return false;
    }

    @Override
    public void closeDrawer() {

    }

    @Override
    public int getCurrentSelectedID() {
        return 0;
    }

    @Override
    public void updateSelectedToHome() {

    }
}
