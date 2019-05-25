package com.csapp.bp.bookpurple.mvp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
 * API response to contain Landing Page data
 * Written by gauravsharma on 2019-05-19.
 */
public class LandingPageResponseModel {

    @SerializedName("landingGrid")
    public LandingGridDto landingGridDto;
}
