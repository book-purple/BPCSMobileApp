package com.csapp.bp.bookpurple.mvp.interactor;

import com.csapp.bp.bookpurple.mvp.model.grid.Tile;
import com.csapp.bp.bookpurple.mvp.model.request.ListingRequestModel;
import com.csapp.bp.bookpurple.mvp.model.response.ListingResponseModel;
import com.csapp.bp.bookpurple.network.api.ServiceApi;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/*
 * Written by Gaurav Sharma on 2019-05-26.
 */
public class ListingInteractor {

    private ServiceApi serviceApi;

    public ListingInteractor(ServiceApi serviceApi) {
        this.serviceApi = serviceApi;
    }

    public Observable<ListingResponseModel> getListingData(ListingRequestModel listingRequestModel) {
        return serviceApi.getListingData(listingRequestModel);
    }

    public ListingRequestModel createListingRequest(Tile tile, String requestType) {
        ListingRequestModel listingRequestModel = new ListingRequestModel();
        listingRequestModel.id = tile.id;
        listingRequestModel.requestType = requestType;
        return listingRequestModel;
    }
}
