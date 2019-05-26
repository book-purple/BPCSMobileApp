package com.csapp.bp.bookpurple.mvp.presenter;

import com.csapp.bp.bookpurple.mvp.interactor.ListingInteractor;
import com.csapp.bp.bookpurple.mvp.interfaces.ListingViewPresenterContract;
import com.csapp.bp.bookpurple.mvp.model.request.ListingRequestModel;
import com.csapp.bp.bookpurple.mvp.model.response.ListingResponseModel;
import com.csapp.bp.bookpurple.util.rx.RxSchedulersAbstractBase;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/*
 * Written by Gaurav Sharma on 2019-05-26.
 */
public class ListingPresenter extends ListingViewPresenterContract.Presenter {


    private ListingInteractor listingInteractor;
    private RxSchedulersAbstractBase rxSchedulers;
    private CompositeDisposable compositeDisposable;

    public ListingPresenter(ListingViewPresenterContract.View view,
                            ListingInteractor interactor,
                            RxSchedulersAbstractBase rxSchedulers,
                            CompositeDisposable compositeDisposable) {
        attachView(view);
        this.listingInteractor = interactor;
        this.rxSchedulers = rxSchedulers;
        this.compositeDisposable = compositeDisposable;
    }


    @Override
    public void getListingData(ListingRequestModel listingRequestModel) {
        Disposable disposable = listingInteractor.getListingData()
                .subscribeOn(rxSchedulers.getIOScheduler())
                .observeOn(rxSchedulers.getMainThreadScheduler())
                .subscribe(listingResponseModel -> {
                    if (isViewAttached()) {
                        if (isNoContent(listingResponseModel)) {
                            getView().dataFetchFailure(new Throwable("Data is NULL"));
                        } else {
                            getView().onListingDataFetched(listingResponseModel);
                        }
                    }
                }, throwable -> {

                });

    }

    @Override
    public boolean isNoContent(ListingResponseModel listingResponseModel) {
        return null == listingResponseModel;
    }
}
