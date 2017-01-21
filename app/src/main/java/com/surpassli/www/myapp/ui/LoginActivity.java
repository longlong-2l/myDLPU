package com.surpassli.www.myapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

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
public class LoginActivity extends Activity implements View.OnClickListener {
    private ActivityLoginBinding loginBinding;
    private String name;
    private String password;
    private ImageView iv_see;
    private Button bt_login;
    private static final String TAG = "LoginActivity";
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_login);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_see).setOnClickListener(this);
        findViewById(R.id.Bt_Login).setOnClickListener(this);
    }

//    public class EventClickListener{
//        public void OnLoginClick(View view){
//           Toast.makeText(LoginActivity.this,"dada",Toast.LENGTH_SHORT).show();
//            name = loginBinding.etName.getText().toString();
//            password = loginBinding.etPassword.getText().toString();
//            login();
//        }
//        public void OnSeePassWord(View view){
//            boolean flag = true;
//            if(flag) {
//                loginBinding.ivSee.setImageResource(R.mipmap.show);
//                loginBinding.etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                flag = false;
//            }else{
//                loginBinding.ivSee.setImageResource(R.mipmap.hide);
//                loginBinding.etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                flag = true;
//            }
//        }
//    }

    public void login() {
        HttpUtil.sendGetOkhttp(AppApi.LOGIN + "username=" + name + "&password=" + password, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: " + "获取数据失败");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this,"网络出先问题，请检查网络设置~~",Toast.LENGTH_SHORT).show();
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
                        Log.i(TAG, "AppVariables.isLogin: " + AppVariables.isLogin);
                        Log.i(TAG, "AppVariables.userId: " + AppVariables.userId);
                        Log.i(TAG, "AppVariables.username: " + AppVariables.username);
                        Log.i(TAG, "AppVariables.token: " + AppVariables.token);
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
                    loginBinding.ivSee.setImageResource(R.mipmap.show);
                    loginBinding.etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    flag = false;
                } else {
                    loginBinding.ivSee.setImageResource(R.mipmap.hide);
                    loginBinding.etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    flag = true;
                }
                break;
            case R.id.Bt_Login:
                name = loginBinding.etName.getText().toString();
                password = loginBinding.etPassword.getText().toString();
                login();
                break;
        }
    }
}
