package com.surpassli.www.myapp.presenter.Home;

import android.app.Activity;
import android.support.v7.widget.Toolbar;

import com.surpassli.www.myapp.presenter.IPresenter;

/**
 * Created by SurpassLi on 2017/8/4.
 * Home页的Presenter
 */

public interface HomePresenter extends IPresenter{
    void clearAllFragments();
    boolean isDrawerOpen();
    void closeDrawer();
    int getCurrentSelectedID();
    void updateSelectedToHome();
}
