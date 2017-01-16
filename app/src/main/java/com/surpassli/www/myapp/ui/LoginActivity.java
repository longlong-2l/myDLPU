package com.surpassli.www.myapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.surpassli.www.myapp.AppVariables;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.databinding.ActivityLoginBinding;
import com.surpassli.www.myapp.support.utils.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by dell on 2017/1/9.
 */
public class LoginActivity extends Activity {
    private ActivityLoginBinding loginBinding;
    private String name;
    private String password;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_login);
        loginBinding.BtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = loginBinding.etName.getText().toString();
                password = loginBinding.etPassword.getText().toString();
                login();
            }
        });
    }

//    public class EventClickListener{
//        public void OnLoginClick(View view){
//           Toast.makeText(LoginActivity.this,"dada",Toast.LENGTH_SHORT).show();
//            xuehao = loginBinding.tilXuehao.getEditText().toString();
//            password = loginBinding.tilPassword.getEditText().toString();
//        }
//    }

    //    public void login() {
//        Request.Builder builder = new Request.Builder();
//        Request request = builder.get().url(AppApi.LOGIN + "username=" + name + "&password=" + password).build();
//        Call call = client.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//                Log.i(TAG, "onFailure: " + e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
//                final String result = response.body().string();
//                Log.i(TAG, "onResponse: " + result);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                    }
//                });
//            }
//        });
//    }
    public void login() {
        HttpUtil.sendGetOkhttp(AppApi.LOGIN + "username=" + name + "&password=" + password, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: " + "获取数据失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "onResponse: " + "获取数据成功");
                String result = response.body().string();
                try {
                    JSONObject jsonObject = new  JSONObject(result);
                    if ("Success".equals(jsonObject.getString("message"))) {
                        AppVariables.isLogin = true;
                        AppVariables.userId = jsonObject.getInt("userId");
                        AppVariables.username = jsonObject.getString("username");
                        AppVariables.token = jsonObject.getString("token");
                        Log.i(TAG, "AppVariables.isLogin: " + AppVariables.isLogin);
                        Log.i(TAG, "AppVariables.userId: " + AppVariables.userId);
                        Log.i(TAG, "AppVariables.username: " + AppVariables.username);
                        Log.i(TAG, "AppVariables.token: " + AppVariables.token);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Intent intent = new Intent();
        intent.putExtra("status", "Success");
        LoginActivity.this.setResult(1,intent);
        LoginActivity.this.finish();
    }
}
