package com.surpassli.www.myapp.ui.life;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.surpassli.www.myapp.InitApp;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.ui.Base.BaseFragment;

public class LocationFragment extends BaseFragment implements View.OnClickListener {

    protected int getLayoutID() {
        return R.layout.fragment_location;
    }

    @Override
    protected void init() {
        TextView normalMap = (TextView) parentView.findViewById(R.id.tv_normal_map);
        TextView satellite_map = (TextView) parentView.findViewById(R.id.tv_satellite_map);
        TextView traffic = (TextView) parentView.findViewById(R.id.tv_traffic);
        TextView myLocation = (TextView) parentView.findViewById(R.id.tv_my_location);
        normalMap.setOnClickListener(this);
        satellite_map.setOnClickListener(this);
        traffic.setOnClickListener(this);
        myLocation.setOnClickListener(this);
    }

    @Override
    public String getTitle() {
        return InitApp.getContext().getString(R.string.location);
    }

    @Override
    public void onEventComing(EventModel eventModel) {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.tv_normal_map:
                intent = new Intent(getMyActivity(), LocationActivity.class);
                intent.putExtra("mapStyle",LocationActivity.NORMAL_MAP);
                startActivity(intent);
                break;
            case R.id.tv_satellite_map:
                intent = new Intent(getMyActivity(), LocationActivity.class);
                intent.putExtra("mapStyle",LocationActivity.SATELLITE_MAP);
                startActivity(intent);
                break;
            case R.id.tv_traffic:
                intent = new Intent(getMyActivity(), LocationActivity.class);
                intent.putExtra("mapStyle",LocationActivity.TRAFFIC_MAP);
                startActivity(intent);
                break;
            case R.id.tv_my_location:
                intent = new Intent(getMyActivity(), LocationActivity.class);
                intent.putExtra("mapStyle",LocationActivity.MY_LOCATION);
                startActivity(intent);
                break;
        }
    }
}
