package com.csapp.bp.bookpurple.mvp.model.response;

import com.csapp.bp.bookpurple.mvp.model.response.listing.ListingItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
 * Written by Gaurav Sharma on 2019-05-26.
 */
public class ListingResponseModel {

    @SerializedName("items")
    public List<ListingItem> listingItems;
}
