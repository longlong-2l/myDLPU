package com.surpassli.www.myapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.surpassli.www.myapp.view.home.HomeView;
import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.presenter.Home.HomePresenter;
import com.surpassli.www.myapp.presenter.Home.HomePresenterImpl;
import com.surpassli.www.myapp.support.utils.HttpUtil;
import com.surpassli.www.myapp.ui.Base.BaseActivity;
import com.surpassli.www.myapp.ui.Base.TopNavigationFragment;
import com.surpassli.www.myapp.ui.EducationFragment;
import com.surpassli.www.myapp.ui.LifeFragment;
import com.surpassli.www.myapp.ui.More.About.About_us_activity;
import com.surpassli.www.myapp.ui.MoreFragment;
import com.surpassli.www.myapp.ui.MyFragment;
import com.surpassli.www.myapp.ui.Setting.SettingActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends BaseActivity implements HomeView, NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";
    public static HomePresenter presenter;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentTransaction fragmentTransaction;
    private long lastPressTime = 0;
    private DrawerLayout drawer;
    private CoordinatorLayout cl_main;

    @Override
    public android.app.FragmentManager getFragmentManager() {
        return super.getFragmentManager();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        boolean isLogin = prefs.getBoolean("isLogin", false);
        int userId = prefs.getInt("userId", 0);
        String username = prefs.getString("username", null);
        String token = prefs.getString("token", null);
        if (isLogin && !TextUtils.isEmpty(username) && !TextUtils.isEmpty(token) && userId != 0) {
            AppVariables.isLogin = true;
            AppVariables.token = token;
            AppVariables.userId = userId;
            AppVariables.username = username;
        }
        presenter = new HomePresenterImpl(this);
        presenter.initLization();
        switchFragment(LifeFragment.getInstance(), "life");
        getData();
    }

    private void getData() {
        HttpUtil.sendGetOkhttp(AppApi.TIME, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "网络出现问题，请检查网络设置~~", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if ("Success".equals(jsonObject.getString("message"))) {
                        AppVariables.time = jsonObject.getInt("time");
                        AppVariables.time_cha = AppVariables.time - (System.currentTimeMillis() / 1000);
                    } else {
                        Log.i(TAG, "onResponse: " + "系统授时失败");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        cl_main = (CoordinatorLayout) findViewById(R.id.main_content);
//        List<BaseFragment> fragmentList = new ArrayList<>();
//        LifeFragment lifeFragment = new LifeFragment();
//        EducationFragment educationFragment = new EducationFragment();
//        MoreFragment moreFragment = new MoreFragment();
//        MyFragment myFragment = new MyFragment();
//
//        fragmentList.add(lifeFragment);
//        fragmentList.add(educationFragment);
//        fragmentList.add(moreFragment);
//        fragmentList.add(myFragment);
//
//        FragmentAdapter fragmentadapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1) {
            String status = data.getStringExtra("status");
            if (status.equals("Success")) {
            }
        } else if (requestCode == 1 && resultCode == 2) {
            String status = data.getStringExtra("status");
            if (status.equals("Success")) {
            }
        }
    }

    @Override
    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void switchToLogin() {

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        switch (id) {
            case R.id.home:
                presenter.clearAllFragments();
                drawer.closeDrawer(GravityCompat.START);
                switchFragment(LifeFragment.getInstance(), String.valueOf(item.getTitle()));
                break;
            case R.id.library:
                presenter.clearAllFragments();
                drawer.closeDrawer(GravityCompat.START);
                switchFragment(EducationFragment.getInstance(), String.valueOf(item.getTitle()));
                break;
            case R.id.leisure:
                presenter.clearAllFragments();
                drawer.closeDrawer(GravityCompat.START);
                switchFragment(MyFragment.getInstance(), String.valueOf(item.getTitle()));
                break;
            case R.id.life:
                presenter.clearAllFragments();
                drawer.closeDrawer(GravityCompat.START);
                switchFragment(MoreFragment.getInstance(), String.valueOf(item.getTitle()));
                break;
            case R.id.education:
                presenter.clearAllFragments();
                drawer.closeDrawer(GravityCompat.START);
                switchFragment(EducationFragment.getInstance(), String.valueOf(item.getTitle()));
                break;
            case R.id.theme:
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.setting:
                drawer.closeDrawer(GravityCompat.START);
                intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.about:
                drawer.closeDrawer(GravityCompat.START);
                intent = new Intent(MainActivity.this, About_us_activity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    private void switchFragment(TopNavigationFragment fragment, String title) {
        setTitle(title);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id._main_frame_layout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (canExit()) {
            super.onBackPressed();
        }
    }

    public boolean canExit() {
        if (System.currentTimeMillis() - lastPressTime > 2000) {
            lastPressTime = System.currentTimeMillis();
            Snackbar.make(cl_main, "再按一次退出程序", Snackbar.LENGTH_SHORT).show();
        }
        return false;
    }
}
