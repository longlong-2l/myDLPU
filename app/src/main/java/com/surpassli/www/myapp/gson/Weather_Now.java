package com.surpassli.www.myapp.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SurpassLi on 2017/1/26.
 */
public class Weather_Now {
    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond")
    public More more;

    public class More {
        @SerializedName("txt")
        public String info;
    }
}
