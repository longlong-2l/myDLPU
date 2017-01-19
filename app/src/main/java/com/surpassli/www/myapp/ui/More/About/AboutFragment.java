package com.surpassli.www.myapp.ui.More.About;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.surpassli.www.myapp.R;

/**
 * Created by SurpassLi on 2017/1/19.
 */
public class AboutFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.more_fragment_about);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {

        return false;
    }
}
