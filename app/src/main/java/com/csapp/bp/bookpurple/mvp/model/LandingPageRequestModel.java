package com.csapp.bp.bookpurple.mvp.model;

import com.google.gson.annotations.SerializedName;

/*
 * Created by gauravsharma on 2019-05-19.
 */
public class LandingPageRequestModel {

    @SerializedName("lat")
    public Long lat;

    @SerializedName("lang")
    public Long lang;
}
