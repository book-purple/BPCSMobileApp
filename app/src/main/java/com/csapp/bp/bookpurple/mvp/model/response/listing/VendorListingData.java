package com.csapp.bp.bookpurple.mvp.model.response.listing;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

/*
 * Written by Gaurav Sharma on 2019-05-26.
 */
@Parcel
public class VendorListingData extends ListingItem {

    @SerializedName("name")
    public String name;

    @SerializedName("image")
    public String image;

    @SerializedName("providedServices")
    public List<String> providedServices;

    @SerializedName("rating")
    public Long rating;
}
