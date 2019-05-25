package com.csapp.bp.bookpurple.mvp.interfaces;

import com.csapp.bp.bookpurple.mvp.model.LandingPageRequestModel;
import com.csapp.bp.bookpurple.mvp.model.LandingPageResponseModel;
import com.csapp.bp.bookpurple.mvp.core.MvpBasePresenter;

/*
 * Written by gauravsharma on 2019-05-19.
 */
public interface LandingViewPresenterContract {

    interface View {

        /**
         * Callback for landing page API call success
         * @param landingPageResponseModel landingPageResponseModel
         */
        void onLandingDataFetched(LandingPageResponseModel landingPageResponseModel);

        /**
         * Function to load the data from backend
         */
        void loadData();

        void dataFetchFailure(Throwable error);
    }

    abstract class Presenter extends MvpBasePresenter<View> {

        /**
         * Function to fetch Landing Page Data
         * @param landingPageRequestModel landingPageResponseModel
         */
        public abstract void getLandingData(LandingPageRequestModel landingPageRequestModel);
    }
}
