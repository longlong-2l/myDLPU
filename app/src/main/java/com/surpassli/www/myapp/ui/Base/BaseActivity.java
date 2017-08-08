package com.surpassli.www.myapp.ui.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.surpassli.www.myapp.Config;
import com.surpassli.www.myapp.InitApp;
import com.surpassli.www.myapp.support.theme.ThemeConfig;
import com.surpassli.www.myapp.support.utils.ActivityManager;
import com.surpassli.www.myapp.support.utils.common.SPUtil;
import com.surpassli.www.myapp.support.utils.common.SettingUtil;

/**
 * Created by SurpassLi on 2017/8/3.
 * Description: BaseActivity
 */

public class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadConfig();
        ActivityManager.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().finishOneActivity(this);
    }

    protected void loadConfig() {
        SPUtil sp = new SPUtil(InitApp.AppContext);
        Config.themeSelected = sp.getIntSP(Config.SP_FILE_NAME, Config.THEME_SELECTED);
        this.setTheme(ThemeConfig.themeStyle[Config.themeSelected]);
        // Language
        int mLang = SettingUtil.getCurrentLanguage();
        if (mLang > -1) {
            SettingUtil.changeLanguage(this, mLang);
        }
    }
}
