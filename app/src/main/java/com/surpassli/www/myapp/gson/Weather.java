package com.surpassli.www.myapp.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by SurpassLi on 2017/1/26.
 * 总的类用来引用各个实体类
 */
public class Weather {

    public String status;

    public Weather_basic basic;

    public Weather_AQI aqi;

    public Weather_Now now;

    public Weather_Suggestion suggestion;

    @SerializedName("daily_forecast")
    public List<Weather_Forecast> forecastList;
}
