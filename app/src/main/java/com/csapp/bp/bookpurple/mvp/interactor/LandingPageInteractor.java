package com.csapp.bp.bookpurple.mvp.interactor;

import com.csapp.bp.bookpurple.mvp.model.LandingPageRequestModel;
import com.csapp.bp.bookpurple.mvp.model.LandingPageResponseModel;
import com.csapp.bp.bookpurple.network.api.ServiceApi;

import io.reactivex.Observable;

/*
 * Created by gauravsharma on 2019-05-19.
 */
public class LandingPageInteractor {

    private ServiceApi serviceApi;

    public LandingPageInteractor(ServiceApi serviceApi) {
        this.serviceApi = serviceApi;
    }

    public Observable<LandingPageResponseModel> getLandingPageDate(LandingPageRequestModel requestModel) {
        return serviceApi.getLandingData(requestModel);
    }
}
