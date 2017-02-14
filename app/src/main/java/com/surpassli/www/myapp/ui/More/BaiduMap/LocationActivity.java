package com.surpassli.www.myapp.ui.More.BaiduMap;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.ui.Base.BaseToolBarActivity;

/**
 * Created by SurpassLi on 2017/2/14.
 */
public class LocationActivity extends AppCompatActivity{
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private Toolbar mToolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化Context全局变量，在setContentView之前调用
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_location);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
               break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        mMapView = (MapView) findViewById(R.id.mv_baidu);
        mToolBar = (Toolbar) findViewById(R.id.tb_location);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("地图");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//给左上角图标的左边加上一个返回的图标 。对应ActionBar.DISPLAY_HOME_AS_UP

        //设置打开Activity时显示500米左右
        mBaiduMap = mMapView.getMap();
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);
        mBaiduMap.setMapStatus(msu);
    }
}
