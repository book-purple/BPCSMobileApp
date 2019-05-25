package com.csapp.bp.bookpurple.mvp.model;

import com.google.gson.annotations.SerializedName;

/*
 * Written by Gaurav Sharma on 2019-05-22.
 */
public abstract class Tile {
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
