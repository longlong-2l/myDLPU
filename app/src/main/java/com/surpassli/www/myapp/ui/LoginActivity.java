package com.surpassli.www.myapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.surpassli.www.myapp.AppVariables;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.databinding.ActivityLoginBinding;
import com.surpassli.www.myapp.databinding.ActivityMainBinding;
import com.surpassli.www.myapp.gson.Person;
import com.surpassli.www.myapp.support.listener.DisposeDataHandle;
import com.surpassli.www.myapp.support.listener.DisposeDataListener;
import com.surpassli.www.myapp.support.utils.HttpUtil;
import com.surpassli.www.myapp.support.utils.net.callback.CommonHttpClient.CommonOkHttpClient;
import com.surpassli.www.myapp.support.utils.request.CommonRequest;

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
                Gson gson = new Gson();
                Person person = gson.fromJson(result, Person.class);
                if ("Success".equals(person.getMessage())) {
                    Log.i(TAG, "getUsername: " + person.getUsername());
                    Log.i(TAG, "getUserId: " + person.getUserId());
                    Log.i(TAG, "getMessage: " + person.getMessage());
                    Log.i(TAG, "getToken: " + person.getToken());
                    AppVariables.isLogin = true;
                }
            }
        });

        Intent intent = new Intent();
        intent.putExtra("status", "Success");
        LoginActivity.this.startActivityForResult(intent, 1);
        LoginActivity.this.finish();
    }
}
