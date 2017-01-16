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
import com.surpassli.www.myapp.support.utils.MD5.getMD5;
import com.surpassli.www.myapp.support.utils.net.callback.CommonHttpClient.CommonOkHttpClient;
import com.surpassli.www.myapp.support.utils.request.CommonRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by dell on 2017/1/9.
 */
public class LoginActivity extends Activity {
    private ActivityLoginBinding loginBinding;
    OkHttpClient client = new OkHttpClient();
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


//    public void login(){
//        CommonOkHttpClient.Get(CommonRequest.createGetRequest(AppApi.LOGIN + "username=" + name + "&password=" + password,null),new DisposeDataHandle(new DisposeDataListener() {
//            @Override
//            public void onSuccess(Object responseObj) {
//                Log.i(TAG, "onResponse: " + responseObj.toString());
//            }
//
//            @Override
//            public void onFailure(Object reasonObj) {
//                Log.i(TAG, "onFailure: " + reasonObj.toString());
//            }
//        }));
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
                Person person = gson.fromJson(result,Person.class);
                if ("Success".equals(person.getMessage())) {
                    AppVariables.isLogin = true;
                    Log.i(TAG, "username: "+person.getUsername());
                    Log.i(TAG, "userId: "+person.getUserId());
                }
//                try {
//                    JSONObject jsonObject = new JSONObject(result);
//                    String message = jsonObject.getString("message");
//                    if ("Success".equals(message)) {
//                        AppVariables.isLogin = true;
//                        Log.i(TAG, "username: "+person.getUsername());
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }
        });
        try {
//            public String getTime(){

                long time = System.currentTimeMillis()/1000;//获取系统时间的10位的时间戳

                String  str=String.valueOf(time);

//                return str;

//            }
            String md5 = getMD5.getMD5("6e6809937b013e5522232329a816989e"+"b533672c7836d5dc72583130a8d6f813"+"1484535245");
            Log.i(TAG, "login: "+md5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent();
        intent.putExtra("status", "Success");
        LoginActivity.this.setResult(1, intent);
        LoginActivity.this.finish();
    }
}
