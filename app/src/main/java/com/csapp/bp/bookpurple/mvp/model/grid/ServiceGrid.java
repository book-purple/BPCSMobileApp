package com.csapp.bp.bookpurple.mvp.model.grid;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
 * Written by Gaurav Sharma on 2019-05-22.
 */
public class ServiceGrid {

    @SerializedName("serviceTiles")
    public List<ServiceTile> serviceTileList;

}
