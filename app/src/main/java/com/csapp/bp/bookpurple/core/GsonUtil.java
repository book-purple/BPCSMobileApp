package com.csapp.bp.bookpurple.core;

import com.csapp.bp.bookpurple.enums.ListingItemViewType;
import com.csapp.bp.bookpurple.mvp.model.response.listing.ListingItemData;
import com.csapp.bp.bookpurple.mvp.model.response.listing.VendorListingData;
import com.csapp.bp.bookpurple.network.parser.RuntimeTypeAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/*
 * Written by Gaurav Sharma on 2019-06-07.
 */
public class GsonUtil {

    private static GsonUtil gsonUtil;

    private final Gson gson;

    private GsonUtil() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        // register different class to make parsing easy.
        gsonBuilder.registerTypeAdapterFactory(getListingItemFactory());

        gsonBuilder.serializeSpecialFloatingPointValues();
        gson = gsonBuilder.create();
    }

    public static synchronized GsonUtil getInstance() {
        if (gsonUtil == null) {
            gsonUtil = new GsonUtil();
        }
        return gsonUtil;
    }

    private RuntimeTypeAdapterFactory<ListingItemData> getListingItemFactory() {
        return RuntimeTypeAdapterFactory
                .of(ListingItemData.class, "viewType")
                .registerSubtype(VendorListingData.class, String.valueOf(ListingItemViewType.VIEW_TYPE_VENDOR));
    }

    public <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    public <T> T fromJson(String json, Type clazz) {
        return gson.fromJson(json, clazz);
    }

    public Gson getGson() {
        return gson;
    }
}
