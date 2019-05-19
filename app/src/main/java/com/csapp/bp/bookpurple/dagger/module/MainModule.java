package com.csapp.bp.bookpurple.dagger.module;

import com.csapp.bp.bookpurple.mvp.interactor.LandingPageInteractor;
import com.csapp.bp.bookpurple.network.api.ServiceApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/*
 * Created by gauravsharma on 2019-05-19.
 */
@Module
public class MainModule {

    @Provides
    @Singleton
    public LandingPageInteractor getLandingPageInteractor(ServiceApi serviceApi) {
        return new LandingPageInteractor(serviceApi);
    }
}
