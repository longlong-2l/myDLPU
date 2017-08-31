package com.surpassli.www.myapp.ui.About;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.widget.Toast;

import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.ui.Base.BaseWebViewActivity;

/**
 * Created by SurpassLi on 2017/1/19.
 * AboutFragment
 */
public class AboutFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {
    private Preference mAppIntroduce;
    private Preference mAppUpdate;
    //    private Preference mAppDemoVideo;
    private Preference mAppSuggestion;
    private Preference mAppShare;
    private Preference mLicense;
    private Preference author_android;
    private Preference author_php;
    private Preference author_web;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.more_fragment_about);
        initView();
    }

    private void initView() {
        mAppIntroduce = findPreference("id_app_intro");
        mAppUpdate = findPreference("id_check_update");
//        mAppDemoVideo = findPreference("id_demo_video");
        mAppSuggestion = findPreference("id_feedback");
        mAppShare = findPreference("id_share");

        author_android = findPreference("author_android");
        author_php = findPreference("author_php");
        author_web = findPreference("author_web");

        mLicense = findPreference("id_license");

        mAppIntroduce.setOnPreferenceClickListener(this);
        mAppUpdate.setOnPreferenceClickListener(this);
//        mAppDemoVideo.setOnPreferenceClickListener(this);
        mAppSuggestion.setOnPreferenceClickListener(this);
        mAppShare.setOnPreferenceClickListener(this);
        author_android.setOnPreferenceClickListener(this);
        author_php.setOnPreferenceClickListener(this);
        author_web.setOnPreferenceClickListener(this);
        mLicense.setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        if (mAppIntroduce == preference) {
            Intent intent = new Intent(getActivity(), MyAppIntroduceActivity.class);
            startActivity(intent);
        } else if (mAppUpdate == preference) {
            Toast.makeText(getActivity(), "当前已是最新版本", Toast.LENGTH_LONG).show();
//        } else if (mAppDemoVideo == preference) {
//            Toast.makeText(getActivity(), "正在玩命完善功能，请期待...", Toast.LENGTH_SHORT).show();
        } else if (mAppSuggestion == preference) {
            Intent intent = new Intent(getActivity(), FeedBackActivity.class);
            startActivity(intent);
        } else if (mAppShare == preference) {
            Toast.makeText(getActivity(), "该功能正在开发中，请期待", Toast.LENGTH_LONG).show();
        } else if (mLicense == preference) {
            startActivity(new Intent(getActivity(), LicenseActivity.class));
        } else if (author_android == preference) {
            Intent intent = new Intent(getActivity(), PeopleIntroduceActivity.class);
            intent.putExtra("url",author_android.getSummary());
            startActivity(intent);
        } else if (author_php == preference) {
            Intent intent = new Intent(getActivity(), PeopleIntroduceActivity.class);
            intent.putExtra("url",author_php.getSummary());
            startActivity(intent);
        } else if (author_web == preference) {
            Intent intent = new Intent(getActivity(), PeopleIntroduceActivity.class);
            intent.putExtra("url",author_web.getSummary());
            startActivity(intent);
        }
        return false;
    }
}
