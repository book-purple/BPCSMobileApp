package com.csapp.bp.bookpurple.mvp.presenter;

import com.csapp.bp.bookpurple.mvp.interactor.LandingPageInteractor;
import com.csapp.bp.bookpurple.mvp.interfaces.LandingViewPresenterContract;
import com.csapp.bp.bookpurple.mvp.model.request.LandingPageRequestModel;
import com.csapp.bp.bookpurple.mvp.model.response.LandingPageResponseModel;
import com.csapp.bp.bookpurple.util.rx.RxSchedulersAbstractBase;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/*
 * Written by Gaurav Sharma on 2019-05-19.
 */
public class LandingPagePresenter extends LandingViewPresenterContract.Presenter {

    private LandingPageInteractor interactor;
    private CompositeDisposable compositeDisposable;
    private RxSchedulersAbstractBase rxSchedulers;

    public LandingPagePresenter(LandingViewPresenterContract.View view,
                                LandingPageInteractor landingPageInteractor,
                                CompositeDisposable compositeDisposable,
                                RxSchedulersAbstractBase rxSchedulers) {
        attachView(view);
        this.interactor = landingPageInteractor;
        this.compositeDisposable = compositeDisposable;
        this.rxSchedulers = rxSchedulers;
    }

    /**
     * Function to get Landing Page Data from API
     * @param landingPageRequestModel landingPageResponseModel
     */
    @Override
    public void getLandingData(LandingPageRequestModel landingPageRequestModel) {
        Disposable subscription = interactor.getLandingPageDate(landingPageRequestModel)
                .subscribeOn(rxSchedulers.getIOScheduler())
                .observeOn(rxSchedulers.getMainThreadScheduler())
                .subscribe(landingPageResponseModel -> {
                    if (isViewAttached()) {
                        if (!isNoContent(landingPageResponseModel)) {
                            getView().onLandingDataFetched(landingPageResponseModel);
                        } else {
                            getView().dataFetchFailure(new Throwable("Invalid Response"));
                        }
                    }
                }, error -> {
                    if (isViewAttached()) {
                        getView().dataFetchFailure(error);
                    }
                });
        compositeDisposable.add(subscription);
    }

    @Override
    public boolean isNoContent(LandingPageResponseModel landingPageResponseModel) {
        return null == landingPageResponseModel;
    }
}
