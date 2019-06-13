package com.csapp.bp.bookpurple.network.api;

import com.csapp.bp.bookpurple.mvp.model.request.LandingPageRequestModel;
import com.csapp.bp.bookpurple.mvp.model.request.ListingRequestModel;
import com.csapp.bp.bookpurple.mvp.model.response.LandingPageResponseModel;
import com.csapp.bp.bookpurple.mvp.model.response.ListingResponseModel;
import com.csapp.bp.bookpurple.mvp.model.response.VendorDetailsPageResponseModel;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/*
 * Retrofit API interface to hold all the URLs related to Service API
 * Written by gauravsharma on 2019-05-19.
 */
public interface ServiceApi {

    @POST("/catalog/v1/landing")
    Observable<LandingPageResponseModel> getLandingData(@Body LandingPageRequestModel landingPageRequestModel);

    @POST("/catalog/v1/listing")
    Observable<ListingResponseModel> getListingData(@Body ListingRequestModel listingRequestModel);

    @GET("https://bp-vendor-service.herokuapp.com/vendor/v1/vendor/details/{vendorId}")
    Observable<VendorDetailsPageResponseModel> getVendorDetailsData(@Path("vendorId") String vendorId);
}
