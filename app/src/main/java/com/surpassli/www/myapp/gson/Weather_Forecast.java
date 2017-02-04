package com.surpassli.www.myapp.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SurpassLi on 2017/1/26.
 * 未来几天的天气
 */
public class Weather_Forecast {
    public String date;

    @SerializedName("tmp")
    public Temperature temperature;

    @SerializedName("cond")
    public More more;

    public class Temperature {
        public String max;

        public String min;
    }

    public class More {
        @SerializedName("txt_d")
        public String info;
    }
}
