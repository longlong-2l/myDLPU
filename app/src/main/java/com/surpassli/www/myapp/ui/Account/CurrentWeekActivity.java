package com.surpassli.www.myapp.ui.Account;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.surpassli.www.myapp.AppVariables;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.support.utils.HttpUtil;
import com.surpassli.www.myapp.support.utils.MD5.MD5;
import com.surpassli.www.myapp.support.utils.Utility;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class CurrentWeekActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current);
        handCurrentWeek();
    }

    public void handCurrentWeek(){
        long mytime = System.currentTimeMillis()/1000;
        String timestamp = String.valueOf(mytime + AppVariables.time_cha);
        String sign = MD5.getMd5(AppVariables.key + AppVariables.token + timestamp);
        HttpUtil.sendGetOkHttp_header(AppApi.MY_CURRENT_WEEK+"userId="+AppVariables.userId + "&sign="+ sign +"&timestamp=" + timestamp, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
              runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                      Toast.makeText(CurrentWeekActivity.this,"网络出现问题，请检查网络设置...",Toast.LENGTH_SHORT).show();
                  }
              });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("CurrentWeekActivity", "onResponse: "+"成功访问接口");
                String res = response.body().string();
                boolean result = Utility.handCurrentweek(res,CurrentWeekActivity.this);
            }
        });
    }
}
