package com.surpassli.www.myapp.ui;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.databinding.ActivityLoginBinding;
import com.surpassli.www.myapp.gson.login.Person;
import com.surpassli.www.myapp.support.utils.HttpUtil.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by SurpassLi on 2017/1/9.
 */
public class LoginActivity extends Activity {
    private ActivityLoginBinding loginBinding;
    OkHttpClient client = new OkHttpClient();
    private String name;
    private String password;
    private static final String TAG = "LoginActivity";
    private String status;
    private int userid;
    private String username;
    private String token;

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

    public void login(){
        HttpUtil.sendOkHttpRequest(AppApi.LOGIN + "username=" + name + "&password=" + password,new okhttp3.Callback(){

            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: "+e.getMessage().toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String jsonData = response.body().string();
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(jsonData);
                    if("Success".equals(jsonObject.getString("message"))) {
                        Gson gson = new Gson();
                        Person person = gson.fromJson(jsonData, Person.class);
                        Log.i(TAG, "username: " + person.getUsername());
                        Log.i(TAG, "token: " + person.getUserid());
                        Log.i(TAG, "userid: " + person.getUserid());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
