package com.csapp.bp.bookpurple.mvp.model.response;

import com.csapp.bp.bookpurple.mvp.model.response.listing.ListingItemData;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

/*
 * Written by Gaurav Sharma on 2019-05-26.
 */
@Parcel
public class ListingResponseModel {

    @SerializedName("vendors")
    public List<ListingItemData> listingItems;

    @SerializedName("error")
    public Error error;

    @Parcel
    public static class Error {

        @SerializedName("error_message")
        public String error;
    }
}
