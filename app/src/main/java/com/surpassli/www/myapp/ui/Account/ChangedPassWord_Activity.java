package com.surpassli.www.myapp.ui.Account;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.surpassli.www.myapp.AppVariables;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.support.utils.HttpUtil;
import com.surpassli.www.myapp.support.utils.MD5.MD5;
import com.surpassli.www.myapp.support.utils.ProgressDialog.MyProgressDialog;
import com.surpassli.www.myapp.support.utils.Utility;
import com.surpassli.www.myapp.ui.LoginActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class ChangedPassWord_Activity extends AppCompatActivity {
    private EditText et_newpasswd;
    private Button bt_sure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changed_pass_word);
        et_newpasswd = (EditText) findViewById(R.id.et_newpasswd);
        bt_sure = (Button) findViewById(R.id.bt_sure);
        bt_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newpasswd = et_newpasswd.getText().toString();
                if (newpasswd != null | "".equals(newpasswd)) {
                    post(newpasswd);
                }else{
                    Toast.makeText(ChangedPassWord_Activity.this,"请输入新密码",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void post(String newpassword) {
        MyProgressDialog.showProgressDialog(ChangedPassWord_Activity.this);
        long mytime = System.currentTimeMillis() / 1000;//获取系统时间的10位的时间戳
        String timestamp = String.valueOf(mytime + AppVariables.time_cha);
        String sign = MD5.getMd5(AppVariables.key + AppVariables.token + timestamp);

        HttpUtil.postChangePassWd(AppApi.MY_PASSWORD_REPAIR,sign,String.valueOf(mytime),newpassword, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(ChangedPassWord_Activity.this,"网络出现问题,请检查网络设置...",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                MyProgressDialog.closeDialog();
                String res = response.body().string();
                boolean result = Utility.handChangePassWord(res);
                if (result) {
                    AppVariables.clear();
                    Intent intent = new Intent();
                    intent.putExtra("status", "Success");
                    ChangedPassWord_Activity.this.setResult(2, intent);
                    ChangedPassWord_Activity.this.finish();
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ChangedPassWord_Activity.this,"对不起,密码修改失败",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
