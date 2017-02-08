package com.surpassli.www.myapp.ui.More.About;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.widget.Toast;

import com.surpassli.www.myapp.R;

/**
 * Created by SurpassLi on 2017/1/19.
 */
public class AboutFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {
    private Preference mAppIntroduce;
    private Preference mAppUpdate;
    private Preference mAppDemoVideo;
    private Preference mAppSuggestion;
    private Preference mAppShare;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.more_fragment_about);
        initView();
    }

    private void initView() {
        mAppIntroduce = findPreference("id_app_intro");
        mAppUpdate = findPreference("id_check_update");
        mAppDemoVideo = findPreference("id_demo_video");
        mAppSuggestion = findPreference("id_feedback");
        mAppShare = findPreference("id_share");
        mAppIntroduce.setOnPreferenceClickListener(this);
        mAppUpdate.setOnPreferenceClickListener(this);
        mAppDemoVideo.setOnPreferenceClickListener(this);
        mAppSuggestion.setOnPreferenceClickListener(this);
        mAppShare.setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        if (mAppIntroduce == preference) {
            Intent intent = new Intent(getActivity(), MyAppIntroduceActivity.class);
            startActivity(intent);
        } else if (mAppUpdate == preference) {
            Toast.makeText(getActivity(), "正在玩命完善功能，请期待...", Toast.LENGTH_SHORT).show();
        } else if (mAppDemoVideo == preference) {
            Toast.makeText(getActivity(), "正在玩命完善功能，请期待...", Toast.LENGTH_SHORT).show();
        } else if (mAppSuggestion == preference) {
            Toast.makeText(getActivity(), "正在玩命完善功能，请期待...", Toast.LENGTH_SHORT).show();
        } else if (mAppShare == preference) {
            Toast.makeText(getActivity(), "正在玩命完善功能，请期待...", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
