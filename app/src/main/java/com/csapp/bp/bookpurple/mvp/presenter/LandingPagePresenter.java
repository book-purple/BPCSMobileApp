package com.csapp.bp.bookpurple.mvp.presenter;

import com.csapp.bp.bookpurple.mvp.interactor.LandingPageInteractor;
import com.csapp.bp.bookpurple.mvp.interfaces.LandingViewPresenterContract;
import com.csapp.bp.bookpurple.mvp.model.LandingPageResponseModel;

/*
 * Created by Gaurav Sharma on 2019-05-19.
 */
public class LandingPagePresenter extends LandingViewPresenterContract.Presenter {

    private LandingPageInteractor interactor;

    @Override
    public void getLandingData(LandingPageResponseModel landingPageResponseModel) {

    }
}
