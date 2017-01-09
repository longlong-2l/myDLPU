package com.surpassli.www.myapp.ui;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.databinding.ActivityLoginBinding;
import com.surpassli.www.myapp.databinding.ActivityMainBinding;

/**
 * Created by dell on 2017/1/9.
 */
public class LoginActivity extends Activity{
    private ActivityLoginBinding loginBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_login);
    }
}
