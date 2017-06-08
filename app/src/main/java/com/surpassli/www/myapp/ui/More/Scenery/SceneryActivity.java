package com.surpassli.www.myapp.ui.More.Scenery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.support.utils.MySelfView.FlowLayout;
import com.surpassli.www.myapp.ui.Base.BaseToolBarActivity;

import java.util.ArrayList;
import java.util.List;

public class SceneryActivity extends BaseToolBarActivity {
    private FlowLayout layout;
    private int i = 0;
    private List<String> textList;
    private List<String> leftextList;
    private List<String> righttextList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenery);
        initToolBar();
        setToolbarTitle(getString(R.string.school_scenery));
        layout = (FlowLayout) findViewById(R.id.tagcloudview);
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
                intent.putExtra("scenery_name",text);
                startActivity(intent);
            }

            @Override
            public void onTagLongClick(View view, String text) {

            }
        });
    }

    private void addTexts()
    {
        textList.add("水电管理部");
        textList.add("食品院后草坪");
        textList.add("二食堂林荫道");
        textList.add("雕塑室前雕塑");
        textList.add("体育场旁小道");
        textList.add("工大水池");

        leftextList.add("工大苗圃");
        leftextList.add("造纸楼旁雕塑");
        leftextList.add("服院旁花坛");
        leftextList.add("艺院前花坛");
        leftextList.add("Life Spring");
        leftextList.add("原球场旁林荫道");

        righttextList.add("林荫走廊");
        righttextList.add("东山小红房");
        righttextList.add("门诊部小道");
        righttextList.add("服院前林荫道");
        righttextList.add("艺院侧面雕塑");
        righttextList.add("体育场爬山虎");
    }

    @Override
    protected void onDestroy() {
        layout.stop();
        super.onDestroy();
    }
}
