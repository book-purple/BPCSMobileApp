package com.csapp.bp.bookpurple.mvp.model.response;

import com.csapp.bp.bookpurple.mvp.model.grid.LandingGridDto;
import com.google.gson.annotations.SerializedName;

/*
 * API response to contain Landing Page data
 * Written by gauravsharma on 2019-05-19.
 */
public class LandingPageResponseModel {

    @SerializedName("landingGrid")
    public LandingGridDto landingGridDto;
}
