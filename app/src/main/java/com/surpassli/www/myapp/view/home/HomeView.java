package com.surpassli.www.myapp.view.home;

import com.surpassli.www.myapp.view.IView;

/**
 * Created by SurpassLi on 2017/8/4.
 * Home页的View
 */

public interface HomeView extends IView<HomeView> {
    void setTitle(String title);
    void switchToLogin();
}
