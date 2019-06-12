package com.csapp.bp.bookpurple.mvp.model.response.listing;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/*
 * Written by Gaurav Sharma on 2019-05-26.
 */
@Parcel
public class VendorListingData extends ListingItemData {

    @SerializedName("vendor_id")
    public String id;

    @SerializedName("vendor_name")
    public String name;

    @SerializedName("vendor_image")
    public String image;

    @SerializedName("vendor_rating")
    public double rating;

    @SerializedName("vendor_desc")
    public String desc;
}
