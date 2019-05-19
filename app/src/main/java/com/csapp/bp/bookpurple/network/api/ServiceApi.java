package com.csapp.bp.bookpurple.network.api;

import com.csapp.bp.bookpurple.mvp.model.LandingPageRequestModel;
import com.csapp.bp.bookpurple.mvp.model.LandingPageResponseModel;

import io.reactivex.Observable;
import retrofit2.http.POST;

/*
 * Retrofit API interface to hold all the URLs related to Service API
 * Created by gauravsharma on 2019-05-19.
 */
public interface ServiceApi {

    @POST("/catalog/v1/landing")
    Observable<LandingPageResponseModel> getLandingData(LandingPageRequestModel landingPageRequestModel);
}