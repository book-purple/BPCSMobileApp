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
        private String id;

        @SerializedName("vendorId")
        private String vendorId;

        @SerializedName("vendorName")
        private String vendorName;

        @SerializedName("vendorDesc")
        private String vendorDesc;

        @SerializedName("reviewCount")
        private int reviewCount;

        @SerializedName("location")
        private String location;

        @SerializedName("providedServices")
        private List<String> providedServices;

        @SerializedName("tags")
        private List<String> tags;

        @SerializedName("images")
        private List<String> images;
    }
}
