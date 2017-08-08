package com.surpassli.www.myapp.ui.Setting;

import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;

import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.support.Setting;
import com.surpassli.www.myapp.support.utils.common.SettingUtil;

/**
 * Created by SurpassLi on 2017/8/7.
 */

public class SettingFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {

    private final static String LANGUAGE_KEY = "id_setting_language";
    private final static String AVATAR_KEY = "id_setting_avatar";
    private final static String AUTO_REFRESH = "id_setting_auto_refresh";
    private final static String EXIT_CONFIRM = "id_setting_confirm_exit";
    private final static String CLEAR_CACHE = "id_setting_clear_cache";
    private final static String LIGHT_MODE = "id_setting_light_mode";
    private Preference mAppLanguage;        //语言
    private Preference mAppAvatar;          //头像
    private SwitchPreference mAutoRefresh;  //刷新
    private SwitchPreference mExitConfirm;  //退出
    private SwitchPreference mLightMode;    //夜间模式
    private Preference mClearCache;         //清缓存

    private Setting mSetting;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);
        initView();
    }

    private void initView() {
        mSetting = Setting.getInstance();

        mAppLanguage = findPreference(LANGUAGE_KEY);
        mAppAvatar = findPreference(AVATAR_KEY);
        mAutoRefresh = (SwitchPreference) findPreference(AUTO_REFRESH);
        mExitConfirm = (SwitchPreference) findPreference(EXIT_CONFIRM);
        mClearCache = findPreference(CLEAR_CACHE);
        mLightMode = (SwitchPreference) findPreference(LIGHT_MODE);

        mAppLanguage.setSummary(this.getResources().getStringArray(R.array.language)[SettingUtil.getCurrentLanguage()]);
        mAppAvatar.setSummary(this.getResources().getStringArray(R.array.avatars)[0]);

        mAppLanguage.setOnPreferenceClickListener(this);
        mAppAvatar.setOnPreferenceClickListener(this);
        mAutoRefresh.setOnPreferenceClickListener(this);
        mExitConfirm.setOnPreferenceClickListener(this);
        mClearCache.setOnPreferenceClickListener(this);
        mLightMode.setOnPreferenceClickListener(this);
    }


    @Override
    public boolean onPreferenceClick(Preference preference) {
        if (preference == mAppLanguage) {
            showLanguageDialog();
        } else if (preference == mAppAvatar) {
            showAvatarSettingsDialog();
        } else if (preference == mClearCache) {
            Snackbar.make(getView(), "清除缓存成功！ ", Snackbar.LENGTH_SHORT).show();
        }
        return false;
    }

    private void showAvatarSettingsDialog() {
    }

    private void showLanguageDialog() {
        new AlertDialog.Builder(getActivity())
                .setTitle(getString(R.string.language))
                .setSingleChoiceItems(
                        getResources().getStringArray(R.array.language), SettingUtil.getCurrentLanguage(),
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (i != SettingUtil.getCurrentLanguage()) {
                                    mSetting.putInt(Setting.LANGUAGE, i);
                                    Setting.needRecreate = true;
                                }
                                dialogInterface.dismiss();
                                if (Setting.needRecreate) {
                                    getActivity().recreate();
                                }
                            }
                        }).show();
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        if (preference == mAutoRefresh) {
            Setting.isAutoRefresh = Boolean.valueOf(o.toString());
            mSetting.PutBoolean(Setting.AUTO_REFRESH, Setting.isAutoRefresh);
        } else if (preference == mExitConfirm) {
            Setting.isExitConfirm = Boolean.valueOf(o.toString());
            mSetting.PutBoolean(Setting.EXIT_CONFIRM, Setting.isExitConfirm);
        }else if(preference == mLightMode){
            Setting.isLightMode = Boolean.valueOf(o.toString());
            mSetting.PutBoolean(Setting.LIGHT_MODE, Setting.isLightMode);
        }
        return false;
    }
}
