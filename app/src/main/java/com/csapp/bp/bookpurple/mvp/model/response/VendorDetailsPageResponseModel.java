package com.csapp.bp.bookpurple.mvp.model.response;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

/*
 * Written by Gaurav Sharma on 2019-06-12.
 */
@Parcel
public class VendorDetailsPageResponseModel {

    @SerializedName("vendorDetails")
    public VendorDetails vendorDetails;

    @Parcel
    public static class VendorDetails {

        @SerializedName("id")
        public String id;

        @SerializedName("vendorId")
        public String vendorId;

        @SerializedName("vendorName")
        public String vendorName;

        @SerializedName("vendorDesc")
        public String vendorDesc;

        @SerializedName("reviewCount")
        public int reviewCount;

        @SerializedName("location")
        public String location;

        @SerializedName("providedServices")
        public List<String> providedServices;

        @SerializedName("tags")
        public List<String> tags;

        @SerializedName("images")
        public List<String> images;
    }
}
