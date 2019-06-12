package com.csapp.bp.bookpurple.mvp.model.response.listing;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/*
 * Written by Gaurav Sharma on 2019-05-26.
 */
@Parcel
public class ListingItemData {

    @SerializedName("viewType")
    public int viewType;
}
