package com.surpassli.www.myapp.support.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.moxun.tagcloudlib.view.TagsAdapter;
import com.surpassli.www.myapp.model.ExerciseYard;
import com.surpassli.www.myapp.ui.More.ExerciseYard.ExerciseActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static u.aly.x.T;

/**
 * Created by SurpassLi on 2017/2/17.
 */

public class TagCloudViewAdapter extends TagsAdapter{
    private List<String> tags = new ArrayList<String>();
    private Context mContext;

    public TagCloudViewAdapter(Context mContext, @NonNull String... data) {
        this.mContext = mContext;
        tags.clear();
        Collections.addAll(tags, data);
    }

    @Override
    public int getCount() {
        return tags.size();
    }

    @Override
    public View getView(final Context context, final int position, ViewGroup parent) {
        TextView tv = new TextView(context);
        tv.setText(tags.get(position).toString());
        tv.setGravity(Gravity.CENTER);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,ExerciseActivity.class);
                intent.putExtra("exercise_yard",tags.get(position).toString());
                mContext.startActivity(intent);
            }
        });
        tv.setTextColor(Color.WHITE);
        return tv;
    }

    @Override
    public Object getItem(int position) {
        return tags.get(position);
    }

    @Override
    public int getPopularity(int position) {
        return position%7;
    }

    @Override
    public void onThemeColorChanged(View view, int themeColor) {
        ((TextView)view).setTextColor(themeColor);
    }
}
