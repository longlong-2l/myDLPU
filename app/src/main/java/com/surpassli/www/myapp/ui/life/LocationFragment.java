package com.surpassli.www.myapp.ui.life;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.surpassli.www.myapp.InitApp;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.ui.Base.BaseFragment;
import com.surpassli.www.myapp.ui.More.BaiduMap.LocationActivity;

public class LocationFragment extends BaseFragment implements View.OnClickListener {
    //    private TextureMapView mTextureMapView;
    private TextView test;
    private TextView normalMap;
    private TextView satellite_map;
    private TextView traffic;
    private TextView myLocation;
    private BaiduMap mBaiduMap;
    //    private LocationClient mLocationClient;
    private myLocationListener mLocationListener;
    private boolean isFirstIn = true;
    private double myLatitude;
    private double myLongitude;

    protected int getLayoutID() {
        return R.layout.activity_location;
    }

    @Override
    protected void init() {
//        mTextureMapView = (TextureMapView) parentView.findViewById(R.id.mv_baidu);
        test = (TextView) parentView.findViewById(R.id.test);
        normalMap = (TextView) parentView.findViewById(R.id.tv_normal_map);
        satellite_map = (TextView) parentView.findViewById(R.id.tv_satellite_map);
        traffic = (TextView) parentView.findViewById(R.id.tv_traffic);
        myLocation = (TextView) parentView.findViewById(R.id.tv_my_location);
        normalMap.setOnClickListener(this);
        satellite_map.setOnClickListener(this);
        traffic.setOnClickListener(this);
        myLocation.setOnClickListener(this);
        test.setOnClickListener(this);
        //设置打开Activity时直接显示500米左右
//        mBaiduMap = mTextureMapView.getMap();
//        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);
//        mBaiduMap.setMapStatus(msu);
//        initLocation();
    }

    @Override
    public String getTitle() {
        return InitApp.getContext().getString(R.string.location);
    }

    @Override
    public void onEventComing(EventModel eventModel) {
    }

    private void initLocation() {
//        mLocationClient = new LocationClient(InitApp.AppContext);
        mLocationListener = new myLocationListener();
//        mLocationClient.registerLocationListener(mLocationListener);//Client注册监听器
        //为mLocationClient设置一些必要的配置
        LocationClientOption Option = new LocationClientOption();
        Option.setCoorType("bd09ll");//必须为bd09ll，原因暂时不知道
        Option.setIsNeedAddress(true);//设置可以得到地址，方便用地址做其他操作
        Option.setOpenGps(true);
        Option.setScanSpan(5000);//每隔5秒钟进行一次定位请求
//        mLocationClient.setLocOption(Option);//可以解决定位隔几个街区的问题
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
//        mBaiduMap.setMyLocationEnabled(true);//开启允许
//        if (!mLocationClient.isStarted()) {
//            mLocationClient.start();//开启定位
//        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        mTextureMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
//        mTextureMapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
//        mBaiduMap.setMyLocationEnabled(false);
//        mLocationClient.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        mTextureMapView.onDestroy();
    }

    /**
     * 定位到我的位置
     *
     * @param myLatitude  myLatitude
     * @param myLongitude myLongitude
     */
    private void centerToMyLocation(double myLatitude, double myLongitude) {
        LatLng latLng = new LatLng(myLatitude, myLongitude);
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);//参数为经纬度，移动地图的位置
        mBaiduMap.animateMapStatus(msu);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_normal_map:
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                break;
            case R.id.tv_satellite_map:
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.tv_traffic:
                if (mBaiduMap.isTrafficEnabled()) {
                    mBaiduMap.setTrafficEnabled(false);
                    traffic.setText("实时交通（off）");
                } else {
                    mBaiduMap.setTrafficEnabled(true);
                    traffic.setText("实时交通（on）");
                }
                break;
            case R.id.tv_my_location:
                centerToMyLocation(myLatitude, myLongitude);
                break;
            case R.id.test:
                startActivity(new Intent(getMyActivity(), LocationActivity.class));
                break;
        }
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
                centerToMyLocation(bdLocation.getLatitude(), bdLocation.getLongitude());
                isFirstIn = false;
                Toast.makeText(InitApp.AppContext, bdLocation.getAddrStr(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    }
}
