package com.surpassli.www.myapp.ui;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.databinding.ActivityLoginBinding;
import com.surpassli.www.myapp.databinding.ActivityMainBinding;
import com.surpassli.www.myapp.support.listener.DisposeDataHandle;
import com.surpassli.www.myapp.support.listener.DisposeDataListener;
import com.surpassli.www.myapp.support.utils.net.callback.CommonHttpClient.CommonOkHttpClient;
import com.surpassli.www.myapp.support.utils.request.CommonRequest;

import java.io.IOException;

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
    public void login(){
        CommonOkHttpClient.Get(CommonRequest.createGetRequest(AppApi.LOGIN + "username=" + name + "&password=" + password,null),new DisposeDataHandle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                Log.i(TAG, "onResponse: " + responseObj.toString());
            }

            @Override
            public void onFailure(Object reasonObj) {
                Log.i(TAG, "onFailure: " + reasonObj.toString());
            }
        }));
    }
}
