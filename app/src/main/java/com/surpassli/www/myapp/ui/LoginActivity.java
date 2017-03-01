package com.surpassli.www.myapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.surpassli.www.myapp.AppVariables;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.support.utils.HttpUtil;
import com.surpassli.www.myapp.support.utils.ProgressDialog.MyProgressDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by SurpassLi on 2017/1/9.
 */
public class LoginActivity extends Activity implements View.OnClickListener {
    private String name;
    private String password;
    private ImageView iv_see;
    private static final String TAG = "LoginActivity";
    private boolean flag = true;
    private EditText et_name;
    private EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        iv_see = (ImageView) findViewById(R.id.iv_see);
        et_name = (EditText) findViewById(R.id.et_name);
        et_password = (EditText) findViewById(R.id.et_password);

        iv_see.setOnClickListener(this);
        findViewById(R.id.Bt_Login).setOnClickListener(this);
    }

    public void login(String name,String password) {
        HttpUtil.sendGetOkhttp(AppApi.LOGIN + "username=" + name + "&password=" + password, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: " + "获取数据失败");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MyProgressDialog.closeDialog();
                        Toast.makeText(LoginActivity.this,"网络出显问题，请检查网络设置~~",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "onResponse: " + "获取数据成功");
                String result = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if ("Success".equals(jsonObject.getString("message"))) {
                        AppVariables.isLogin = true;
                        AppVariables.userId = jsonObject.getInt("userId");
                        AppVariables.username = jsonObject.getString("username");
                        AppVariables.token = jsonObject.getString("token");
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this).edit();
                        editor.clear();
                        editor.apply();
                        editor.putBoolean("isLogin",AppVariables.isLogin);
                        editor.putInt("userId",AppVariables.userId);
                        editor.putString("username",AppVariables.username);
                        editor.putString("token",AppVariables.token);
                        editor.apply();
                        MyProgressDialog.closeDialog();
                        Intent intent = new Intent();
                        intent.putExtra("status", "Success");
                        LoginActivity.this.setResult(1, intent);
                        LoginActivity.this.finish();
                    }else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this,"用户名或密码错误~~~",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_see:
                if (flag) {
                    iv_see.setImageResource(R.mipmap.show);
                    et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    flag = false;
                } else {
                    iv_see.setImageResource(R.mipmap.hide);
                    et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    flag = true;
                }
                break;
            case R.id.Bt_Login:
                name = et_name.getText().toString();
                password = et_password.getText().toString();
                login(name,password);
                MyProgressDialog.showProgressDialog(LoginActivity.this);
                break;
        }
    }
}
