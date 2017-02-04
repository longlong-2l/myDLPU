package com.surpassli.www.myapp.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SurpassLi on 2017/1/26.
 */
public class Weather_Suggestion {
    @SerializedName("comf")
    public Comfort comfort;

    @SerializedName("cw")
    public CarWash carWash;


    public Sport sport;

    public class Comfort {
        @SerializedName("txt")
        public String info;
    }

    public class CarWash {
        @SerializedName("txt")
        public String info;
    }

    public class Sport {
        @SerializedName("txt")
        public String info;
    }
}
