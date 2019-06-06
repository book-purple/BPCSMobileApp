package com.csapp.bp.bookpurple.mvp.model.request;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/*
 * Written by Gaurav Sharma on 2019-05-26.
 */
@Parcel
public class ListingRequestModel {

    @SerializedName("id")
    public String id;

    @SerializedName("requestType")
    public String requestType;
}
