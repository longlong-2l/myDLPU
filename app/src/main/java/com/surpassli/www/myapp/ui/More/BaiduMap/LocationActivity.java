package com.surpassli.www.myapp.ui.More.BaiduMap;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.surpassli.www.myapp.R;

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
    private boolean isFirstIn = true;
    private Context mContext;
    private double myLatitude;
    private double myLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化Context全局变量，在setContentView之前调用
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_location);
        this.mContext = this;
        initView();
        initLocation();
    }

    private void initLocation() {
        mLocationClient = new LocationClient(this);
        mLocationListener = new myLocationListener();
        mLocationClient.registerLocationListener(mLocationListener);//Client注册监听器
        //为mLocationClient设置一些必要的配置
        LocationClientOption Option = new LocationClientOption();
        Option.setCoorType("bd09ll");//必须为bd09ll，原因暂时不知道
        Option.setIsNeedAddress(true);//设置可以得到地址，方便用地址做其他操作
        Option.setOpenGps(true);
        Option.setScanSpan(5000);//每隔5秒钟进行一次定位请求
        mLocationClient.setLocOption(Option);//可以解决定位隔几个街区的问题
    }

    @Override
    protected void onStart() {
        super.onStart();
        mBaiduMap.setMyLocationEnabled(true);//开启允许
        if(!mLocationClient.isStarted()) {
            mLocationClient.start();//开启定位
        }
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
    protected void onStop() {
        super.onStop();
        mBaiduMap.setMyLocationEnabled(false);
        mLocationClient.stop();
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
            case R.id.id_myLocation:
                cneterToMyLocation(myLatitude, myLongitude);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 定位到我的位置
     * @param myLatitude
     * @param myLongitude
     */
    private void cneterToMyLocation(double myLatitude, double myLongitude) {
        LatLng latLng = new LatLng(myLatitude, myLongitude);
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);//参数为经纬度，移动地图的位置
        mBaiduMap.animateMapStatus(msu);
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

    private class myLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            MyLocationData data = new MyLocationData.Builder()//
                    .accuracy(bdLocation.getRadius())//精度
                    .latitude(bdLocation.getLatitude())//经度
                    .longitude(bdLocation.getLongitude()).build();//纬度
            mBaiduMap.setMyLocationData(data);
            //将定位的位置给予变量，确保其一直是最新的变量
            myLatitude = bdLocation.getLatitude();
            myLongitude = bdLocation.getLongitude();
            if (isFirstIn) {
                //若是第一次定位
                cneterToMyLocation(bdLocation.getLatitude(), bdLocation.getLongitude());
                isFirstIn = false;

                Toast.makeText(mContext,bdLocation.getAddrStr(),Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    }
}
