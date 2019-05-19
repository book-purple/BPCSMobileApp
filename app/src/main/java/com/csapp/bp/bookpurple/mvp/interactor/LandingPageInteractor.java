package com.csapp.bp.bookpurple.mvp.interactor;

import com.csapp.bp.bookpurple.network.api.ServiceApi;

/*
 * Created by gauravsharma on 2019-05-19.
 */
public class LandingPageInteractor {

    private ServiceApi serviceApi;

    public LandingPageInteractor(ServiceApi serviceApi) {
        this.serviceApi = serviceApi;
    }
}
