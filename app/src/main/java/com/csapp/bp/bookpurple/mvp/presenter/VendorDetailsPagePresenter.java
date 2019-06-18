package com.csapp.bp.bookpurple.mvp.presenter;

import android.content.Context;

import com.csapp.bp.bookpurple.mvp.interactor.VendorDetailsPageInteractor;
import com.csapp.bp.bookpurple.mvp.interfaces.VendorDetailsViewPresenterContract;
import com.csapp.bp.bookpurple.mvp.model.response.VendorDetailsPageResponseModel;
import com.csapp.bp.bookpurple.util.rx.RxSchedulersAbstractBase;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/*
 * Written by Gaurav Sharma on 2019-06-13.
 */
public class VendorDetailsPagePresenter extends VendorDetailsViewPresenterContract.Presenter {

    private Context context;
    private CompositeDisposable lifecycle;
    private VendorDetailsPageInteractor interactor;
    private RxSchedulersAbstractBase rxSchedulers;

    public VendorDetailsPagePresenter(VendorDetailsViewPresenterContract.View view,
                                      CompositeDisposable lifecycle,
                                      VendorDetailsPageInteractor vendorDetailsPageInteractor,
                                      RxSchedulersAbstractBase rxSchedulers) {
        attachView(view);
        this.lifecycle = lifecycle;
        this.interactor = vendorDetailsPageInteractor;
        this.rxSchedulers = rxSchedulers;

    }

    @Override
    public void fetchVendorDetailsPageData(String vendorId) {
        Disposable disposable = interactor.getVendorDetailsPageData(vendorId)
                .subscribeOn(rxSchedulers.getIOScheduler())
                .observeOn(rxSchedulers.getMainThreadScheduler())
                .subscribe(vendorDetailsPageResponseModel -> {
                    if (inNoContent(vendorDetailsPageResponseModel)) {
                        if (isViewAttached()) {
                            getView().onDataFetchFailure(new Throwable("Data is Null"));
                        }
                    } else {
                        if (isViewAttached()) {
                            getView().onVendorDetailsPageDataFetched(vendorDetailsPageResponseModel);
                        }
                    }
                }, throwable -> {
                    if (isViewAttached()) {
                        getView().onDataFetchFailure(throwable);
                    }
                });
        this.lifecycle.add(disposable);
    }

    @Override
    public boolean inNoContent(VendorDetailsPageResponseModel vendorDetailsPageResponseModel) {
        return false;
    }

    public String getProvidedServices(List<String> providedServices) {
        StringBuilder services = new StringBuilder();
        for(String service: providedServices) {
            services.append(service).append(",");
        }
        return services.toString();
    }
}
