package com.surpassli.www.myapp.ui.More.BaiduMap;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.TextureMapView;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.ui.Base.BaseToolBarActivity;

/**
 * Created by SurpassLi on 2017/2/14.
 */
public class LocationActivity extends AppCompatActivity {
    private TextureMapView mTextureMapView;
    private BaiduMap mBaiduMap;
    private Toolbar mToolBar;
    //定位相关
    private LocationClient mLocationClient;
    private myLocationListener mLocationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化Context全局变量，在setContentView之前调用
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_location);
        initView();
        initLocation();
    }

    private void initLocation() {
        mLocationClient = new LocationClient(this);
        mLocationListener = new myLocationListener();
        mLocationClient.registerLocationListener(mLocationListener);//Client注册监听器
        //为mLocationClient设置一些必要的配置
        
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTextureMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mTextureMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTextureMapView.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_normalMap:
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                break;
            case R.id.id_WeiXingMap:
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.id_traffic:
                if (mBaiduMap.isTrafficEnabled()) {
                    mBaiduMap.setTrafficEnabled(false);
                    item.setTitle("实时交通（off）");
                } else {
                    mBaiduMap.setTrafficEnabled(true);
                    item.setTitle("实时交通（on）");
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        mTextureMapView = (TextureMapView) findViewById(R.id.mv_baidu);
        mToolBar = (Toolbar) findViewById(R.id.tb_location);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("地图");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//给左上角图标的左边加上一个返回的图标 。对应ActionBar.DISPLAY_HOME_AS_UP
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //设置打开Activity时直接显示500米左右
        mBaiduMap = mTextureMapView.getMap();
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);
        mBaiduMap.setMapStatus(msu);
    }

    private class myLocationListener implements BDLocationListener{

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {

        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    }
}
