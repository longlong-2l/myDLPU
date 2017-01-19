package com.surpassli.www.myapp.ui.More.About;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.surpassli.www.myapp.R;

/**
 * Created by SurpassLi on 2017/1/19.
 */
public class AboutFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener{
    private Preference mAppIntroduce;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.more_fragment_about);//测试我的码云
        initView();
    }

    private void initView() {
        mAppIntroduce = findPreference("id_app_intro");
        mAppIntroduce.setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        if(mAppIntroduce == preference){
//            Intent intent = new Intent(getActivity(),);

        }

        return false;
    }
}
