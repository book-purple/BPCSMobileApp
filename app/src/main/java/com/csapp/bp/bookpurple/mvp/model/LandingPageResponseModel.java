package com.csapp.bp.bookpurple.mvp.model;

import com.google.gson.annotations.SerializedName;

/*
 * API response to contain Landing Page data
 * Created by gauravsharma on 2019-05-19.
 */
public class LandingPageResponseModel {

    @SerializedName("eventGrid")
    public EventGrid eventGrid;

    @SerializedName("serviceGrid")
    public ServiceGrid serviceGrid;

    public static class EventGrid {

    }

    public static class ServiceGrid {

    }
}
