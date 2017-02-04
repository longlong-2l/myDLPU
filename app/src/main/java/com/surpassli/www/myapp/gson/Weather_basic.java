package com.surpassli.www.myapp.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SurpassLi on 2017/1/25.
 * 由于json中的一些字段可能不太适合直接作为java字段命名，
 * 因此使用@SerializedName注解方式来让json和java字段之间建立映射关系
 * 属性在序列化成Json的时候，需要将名字序列化成注解的value属性指定的值
 * cityame会被序列化为city
 */
public class Weather_basic {
    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public String weatherId;

    public Update update;

            public class Update{
                @SerializedName("loc")
                public String updateTime;
            }
}
