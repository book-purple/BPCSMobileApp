package com.csapp.bp.bookpurple.mvp.interactor;

import com.csapp.bp.bookpurple.mvp.model.response.VendorDetailsPageResponseModel;
import com.csapp.bp.bookpurple.network.api.ServiceApi;

import io.reactivex.Observable;

/*
 * Written by Gaurav Sharma on 2019-06-13.
 */
public class VendorDetailsPageInteractor {

    private ServiceApi serviceApi;

    public VendorDetailsPageInteractor(ServiceApi serviceApi) {
        this.serviceApi = serviceApi;
    }

    public Observable<VendorDetailsPageResponseModel> getVendorDetailsPageData(String vendorId) {
        return serviceApi.getVendorDetailsData(vendorId);
    }
}
