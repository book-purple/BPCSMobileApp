package com.csapp.bp.bookpurple.dagger.module;

import com.csapp.bp.bookpurple.mvp.interactor.LandingPageInteractor;
import com.csapp.bp.bookpurple.network.api.ServiceApi;
import com.csapp.bp.bookpurple.util.rx.RxSchedulers;
import com.csapp.bp.bookpurple.util.rx.RxSchedulersAbstractBase;

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

    @Provides
    @Singleton
    public RxSchedulersAbstractBase provideRxSchedulers() {
        return new RxSchedulers();
    }
}
