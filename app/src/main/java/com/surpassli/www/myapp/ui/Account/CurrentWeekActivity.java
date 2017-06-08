package com.surpassli.www.myapp.ui.Account;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.surpassli.www.myapp.AppVariables;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.support.utils.HttpUtil;
import com.surpassli.www.myapp.support.utils.MD5.MD5;
import com.surpassli.www.myapp.support.utils.ProgressDialog.MyProgressDialog;
import com.surpassli.www.myapp.support.utils.Utility;
import com.surpassli.www.myapp.ui.Base.BaseToolBarActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class CurrentWeekActivity extends BaseToolBarActivity {

    private TextView current_week_num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current);
        initToolBar();
        setToolbarTitle("当前周次");
        current_week_num = (TextView) findViewById(R.id.tv_current_week_num);
        handCurrentWeek();
    }

    public void handCurrentWeek(){
        MyProgressDialog.showProgressDialog(CurrentWeekActivity.this);
        long mytime = System.currentTimeMillis()/1000;
        String timestamp = String.valueOf(mytime + AppVariables.time_cha);
        String sign = MD5.getMd5(AppVariables.key + AppVariables.token + timestamp);
        HttpUtil.sendGetOkHttp_header(AppApi.MY_CURRENT_WEEK+"userId="+AppVariables.userId + "&sign="+ sign +"&timestamp=" + timestamp, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
              runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                      MyProgressDialog.closeDialog();
                      Toast.makeText(CurrentWeekActivity.this,"网络出现问题，请检查网络设置...",Toast.LENGTH_SHORT).show();
                  }
              });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("CurrentWeekActivity", "onResponse: "+"成功访问接口");
                final String res = response.body().string();
                final String result = Utility.handCurrentweek(res,CurrentWeekActivity.this);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MyProgressDialog.closeDialog();
                        StringBuilder sb = new StringBuilder();
                        sb.append("<font color=\"#000000\"><small>第</small></font>");
                        sb.append("<font color=\"#ff772d\"><big>%s</big></font>");
                        sb.append("<font color=\"#000000\"><small>周</small></font><br>");
                        String str = String.format(sb.toString(), result);
                        current_week_num.setText(Html.fromHtml(str));
                    }
                });
            }
        });
    }
}
