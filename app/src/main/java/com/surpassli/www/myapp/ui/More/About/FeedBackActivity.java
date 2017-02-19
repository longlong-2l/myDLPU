package com.surpassli.www.myapp.ui.More.About;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.ui.Base.BaseToolBarActivity;

/**
 * Created by SurpassLi on 2017/2/17.
 */
public class FeedBackActivity extends BaseToolBarActivity{
    private EditText respondentET;
    private EditText emailET;
    private EditText bodyET;
    private MenuItem sendMenu;
    private Button mButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        initToolBar();
        setToolbarTitle(getString(R.string.feedback));
        initView();
    }

    private void initView() {
        respondentET = (EditText) findViewById(R.id.respondent);
        emailET = (EditText) findViewById(R.id.email);
        bodyET = (EditText) findViewById(R.id.respondent);
        mButton = (Button) findViewById(R.id.bt_submit);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FeedBackActivity.this,"此功能尚未完善,敬请期待",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
