package com.surpassli.www.myapp.ui.More.Scenery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.support.utils.MySelfView.FlowLayout;
import com.surpassli.www.myapp.ui.Base.BaseToolBarActivity;

import java.util.ArrayList;
import java.util.List;

public class SceneryActivity extends BaseToolBarActivity {
    FlowLayout layout;
    int i = 0;
    List<String> textList;
    List<String> leftextList;
    List<String> righttextList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenery);
        initToolBar();
        setToolbarTitle(getString(R.string.school_scenery));
        layout = (FlowLayout) findViewById(R.id.tagcloudview);
        layout.setBackgroundResource(R.color.white);
        textList = new ArrayList<String>();
        leftextList = new ArrayList<String>();
        righttextList = new ArrayList<String>();
        addTexts();
        for (int i = 0; i < textList.size(); i++)
        {
            layout.addText(textList.get(i));
        }
        for (int i = 0; i < leftextList.size(); i++)
        {
            layout.addLeftText(leftextList.get(i));
        }
        for (int i = 0; i < righttextList.size(); i++)
        {
            layout.addRightText(righttextList.get(i));
        }
        layout.setonTagClickListener(new FlowLayout.OnTagClickListener() {
            @Override
            public void onTagClick(View view, String text) {
                Intent intent = new Intent(SceneryActivity.this,SceneryContentActivity.class);
                startActivity(intent);
            }

            @Override
            public void onTagLongClick(View view, String text) {

            }
        });
    }

    private void addTexts()
    {
//        for (int j = 0; j < 8; j++) {
//            textList.add("景色1");
//        }
        textList.add("景色1");
        textList.add("景色1");
        textList.add("景色1");
        textList.add("景色1");
        textList.add("景色1");
        textList.add("景色1");
        textList.add("景色1");
        textList.add("景色1");
        textList.add("景色1");

        leftextList.add("景色2");
        leftextList.add("景色2");
        leftextList.add("景色2");
        leftextList.add("景色2");
        leftextList.add("景色2");
        leftextList.add("景色2");
        leftextList.add("景色2");
        leftextList.add("景色2");

        righttextList.add("景色3");
        righttextList.add("景色3");
        righttextList.add("景色3");
        righttextList.add("景色3");
        righttextList.add("景色3");
        righttextList.add("景色3");
        righttextList.add("景色3");
        righttextList.add("景色3");
    }

    @Override
    protected void onDestroy() {
        layout.stop();
        super.onDestroy();
    }
}
