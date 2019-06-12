package com.csapp.bp.bookpurple.mvp.model.grid;

import com.google.gson.annotations.SerializedName;

/*
 * Written by Gaurav Sharma on 2019-05-22.
 */
public class LandingGridDto {

    @SerializedName("eventGrid")
    public EventGrid eventGrid;

    @SerializedName("serviceGrid")
    public ServiceGrid serviceGrid;
}
