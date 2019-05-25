package com.csapp.bp.bookpurple.mvp.model;

import com.google.gson.annotations.SerializedName;

/*
 * Written by gauravsharma on 2019-05-19.
 */
public class LandingPageRequestModel {

    public LandingPageRequestModel(Long lat, Long lang) {
        this.lat = lat;
        this.lang = lang;
    }

    @SerializedName("lat")
    public Long lat;

    @SerializedName("lang")
    public Long lang;
}
