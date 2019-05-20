package com.csapp.bp.bookpurple.dagger.module;

import com.csapp.bp.bookpurple.BuildConfig;
import com.csapp.bp.bookpurple.InternalApplication;
import com.csapp.bp.bookpurple.network.NetworkWrapper;
import com.csapp.bp.bookpurple.network.RequestHeaders;
import com.csapp.bp.bookpurple.network.api.ServiceApi;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/*
 * Created by gauravsharma on 2019-05-19.
 */
@Module
public class NetworkModule {

    @Named("baseRetrofit")
    @Provides
    @Singleton
    public Retrofit provideBaseRetrofit() {
        NetworkWrapper wrapper = new NetworkWrapper
                .Builder(InternalApplication.getApplication().getApplicationContext())
                .setBaseUrl(BuildConfig.PRODUCTION_URL)
                .setCacheSize(1024 * 1024 * 1024)
                .setHeadersBuilder(RequestHeaders.getInstance().getHeaders())
                .setCertificates(RequestHeaders.getInstance().getCertificateList())
                .setConnectTimeout(7, TimeUnit.SECONDS)
                .setReadTimeout(120, TimeUnit.SECONDS)
                .setWriteTimeout(10, TimeUnit.SECONDS)
                .build();

        return wrapper.getRetrofit();
    }

    @Named("serviceApi")
    @Provides
    @Singleton
    public ServiceApi provideServiceApi(@Named("baseRetrofit") Retrofit retrofit) {
        return retrofit.create(ServiceApi.class);
    }
}
