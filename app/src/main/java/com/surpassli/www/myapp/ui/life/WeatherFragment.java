package com.surpassli.www.myapp.ui.life;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.surpassli.www.myapp.InitApp;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.event.EVENT;
import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.gson.Weather;
import com.surpassli.www.myapp.gson.Weather_Forecast;
import com.surpassli.www.myapp.support.utils.HttpUtil;
import com.surpassli.www.myapp.support.utils.Utility;
import com.surpassli.www.myapp.ui.Base.BaseFragment;
import com.surpassli.www.myapp.ui.More.Weather.WeatherActivity;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class WeatherFragment extends BaseFragment {

    private LinearLayout forecastLayout;
    private TextView country_weather;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_weather;
    }

    @Override
    protected void init() {
        forecastLayout = (LinearLayout) parentView.findViewById(R.id.first_forecast_layout);
        country_weather = (TextView) parentView.findViewById(R.id.tv_country_weather);
        country_weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), WeatherActivity.class));
            }
        });
        getDataFromCache();
    }

    @Override
    public String getTitle() {
        return InitApp.getContext().getString(R.string.weather);
    }

    public void getDataFromCache() {
        SharedPreferences pres = PreferenceManager.getDefaultSharedPreferences(InitApp.getContext());
        String firstString = pres.getString("weather", null);
        if (firstString != null) {
            Weather weather = Utility.handleWeatherResponse(firstString);
            showWeatherInfo(weather);
        } else {
            getDataFromNet("CN101070201");
        }
    }

    public void getDataFromNet(String weatherId) {
        final Handler handler = new Handler(Looper.getMainLooper());
        String weatherUrl = "http://guolin.tech/api/weather?cityid=" + weatherId + "&key=6f7170e464174f8fa99142a2c57a0cb3";
        HttpUtil.sendGetOkhttp(weatherUrl, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        EventBus.getDefault().post(new EventModel<String>(EVENT.FIRSTWEATHER_NET_FAIL));
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final Weather weather = Utility.handleWeatherResponse(responseText);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (weather != null && "ok".equals(weather.status)) {
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getMyActivity()).edit();
                            editor.putString("weather", responseText);
                            editor.apply();
                            EventBus.getDefault().post(new EventModel<Weather>(EVENT.FIRSTWEATHER_NET_SUCCESS, weather));
                        } else {
                            EventBus.getDefault().post(new EventModel<Weather_Forecast>(EVENT.FIRSTWEATHER_NET_FAIL));
                        }
                    }
                });
            }
        });
    }

    /**
     * 处理并展示Weather实体类中的数据
     */
    private void showWeatherInfo(Weather weather) {
        forecastLayout.removeAllViews();
        for (Weather_Forecast foeForecast : weather.forecastList) {
            View view = LayoutInflater.from(getMyActivity()).inflate(R.layout.item_weather, forecastLayout, false);
            TextView dateText = (TextView) view.findViewById(R.id.weather_date);
            TextView infoText = (TextView) view.findViewById(R.id.weather_status);
            TextView maxText = (TextView) view.findViewById(R.id.weather_max_temperature);
            TextView minText = (TextView) view.findViewById(R.id.weather_min_temperature);
            TextView tv_aqi_content = (TextView) view.findViewById(R.id.tv_aqi_content);
            TextView tv_PM2_5_content = (TextView) view.findViewById(R.id.tv_PM2_5_content);
            if (weather.aqi != null) {
                tv_aqi_content.setText(weather.aqi.city.aqi);
                tv_PM2_5_content.setText(weather.aqi.city.pm25);
            }
            dateText.setText(foeForecast.date);
            infoText.setText(foeForecast.more.info);
            maxText.setText(foeForecast.temperature.max);
            minText.setText(foeForecast.temperature.min);
            forecastLayout.addView(view);//找到每一个view放到list里面
        }
    }

    @Override
    public void onEventComing(EventModel eventModel) {
        switch (eventModel.getEventCode()) {
            case EVENT.FIRSTWEATHER_NET_SUCCESS:
                showWeatherInfo((Weather) eventModel.getData());
                break;
            case EVENT.FIRSTWEATHER_NET_FAIL:
                break;
        }
    }
}
