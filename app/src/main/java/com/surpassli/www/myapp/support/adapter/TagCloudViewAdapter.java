package com.surpassli.www.myapp.support.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moxun.tagcloudlib.view.TagsAdapter;
import com.surpassli.www.myapp.model.ExerciseYard;
import com.surpassli.www.myapp.ui.More.ExerciseYard.ExerciseActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by SurpassLi on 2017/2/17.
 * 云标签的3D大球球滚动实现
 */

public class TagCloudViewAdapter extends TagsAdapter{
    private List<String> tags_string;
    private List<ExerciseYard> tags;
    private Context mContext;
    private String flag;

    public TagCloudViewAdapter(Context mContext, ArrayList<String> data,String flag) {
        this.mContext = mContext;
        this.flag = flag;
        tags_string = new ArrayList<String>();
        tags_string.clear();
        this.tags_string = data;
    }

    public TagCloudViewAdapter(Context mContext, ArrayList<ExerciseYard> data) {
        tags = new ArrayList<ExerciseYard>();
        this.mContext = mContext;
        this.tags.clear();
        this.tags = data;
    }

    @Override
    public int getCount() {
        if(flag!= null && "String".equals(flag))
           return tags_string.size();
        else{
            return tags.size();
        }
    }

    @Override
    public View getView(final Context context, final int position, ViewGroup parent) {
        TextView tv = new TextView(context);
        if (tags != null)
            tv.setText(tags.get(position).getName());
        else if (tags_string != null)
            tv.setText(tags_string.get(position));
            tv.setGravity(Gravity.CENTER);
            tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ExerciseActivity.class);
                if (tags != null) {
                    intent.putExtra("exercise_yard", tags.get(position).getName());
                    intent.putExtra("content",tags.get(position).getContent());
                    intent.putExtra("position", position);
                }
                else if (tags_string != null) {
                    intent.putExtra("exercise_yard", tags_string.get(position));
                    intent.putExtra("content",tags.get(position).getContent());
                    intent.putExtra("position", position);
                }
                mContext.startActivity(intent);
            }
        });
        tv.setTextColor(Color.WHITE);
        return tv;
    }

    @Override
    public Object getItem(int position) {
        if(flag!= null && "String".equals(flag))
            return tags_string.get(position);
        else{
            return tags.get(position);
        }
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
