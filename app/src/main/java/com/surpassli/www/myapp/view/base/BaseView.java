package com.surpassli.www.myapp.view.base;

import com.surpassli.www.myapp.view.IView;

/**
 * Created by SurpassLi on 2017/8/9.
 * BaseView
 */

public interface BaseView<V> extends IView<V>{
    void displayLoading();
    void hideLoading();
    void displayNetworkError();
}
