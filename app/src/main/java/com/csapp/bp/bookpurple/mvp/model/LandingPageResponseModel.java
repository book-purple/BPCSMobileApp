package com.csapp.bp.bookpurple.mvp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
 * API response to contain Landing Page data
 * Created by gauravsharma on 2019-05-19.
 */
public class LandingPageResponseModel {

    @SerializedName("landingGrid")
    public LandingGridDto landingGridDto;

    private static class LandingGridDto {
        @SerializedName("eventGrid")
        public EventGrid eventGrid;

        @SerializedName("serviceGrid")
        public ServiceGrid serviceGrid;
    }

    public static class EventGrid {

        @SerializedName("eventTiles")
        public List<EventTiles> eventTiles;
    }

    public static class ServiceGrid {

        @SerializedName("serviceTiles")
        public List<ServiceTiles> serviceTiles;
    }

    public static abstract class Tiles {

        @SerializedName("id")
        public String id;
        @SerializedName("name")
        public String name;
        @SerializedName("icon")
        public String icon;
        @SerializedName("isLocationSpecific")
        public String isLocationSpecific;
        @SerializedName("isActive")
        public String isActive;
    }

    public static class EventTiles extends Tiles{
    }

    public static class ServiceTiles extends Tiles {

    }
}
