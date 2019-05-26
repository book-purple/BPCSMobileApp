package com.csapp.bp.bookpurple.mvp.model.grid;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/*
 * Written by Gaurav Sharma on 2019-05-22.
 */
@Parcel()
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
