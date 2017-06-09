package com.surpassli.www.myapp.ui.More.About;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.surpassli.www.myapp.AppVariables;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.support.utils.HttpUtil;
import com.surpassli.www.myapp.ui.Base.BaseToolBarActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

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
                String respondent = respondentET.getText().toString();
                String email = emailET.getText().toString();
                String body = bodyET.getText().toString();
                HttpUtil.postFeedBack(AppApi.FEEDBACK, respondent + email + body, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(FeedBackActivity.this,"网络问题，请检查网络",Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.code() == 200) {
                            Toast.makeText(FeedBackActivity.this, "信息已提交，感谢您的反馈！", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
