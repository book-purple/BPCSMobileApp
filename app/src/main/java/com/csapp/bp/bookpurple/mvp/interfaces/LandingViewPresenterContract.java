package com.csapp.bp.bookpurple.mvp.interfaces;

import com.csapp.bp.bookpurple.mvp.core.MvpBasePresenterV2;
import com.csapp.bp.bookpurple.mvp.model.request.LandingPageRequestModel;
import com.csapp.bp.bookpurple.mvp.model.response.LandingPageResponseModel;

/*
 * Written by gauravsharma on 2019-05-19.
 */
public interface LandingViewPresenterContract {

    interface View {

        /**
         * Callback for landing page API call success
         *
         * @param landingPageResponseModel landingPageResponseModel
         */
        void onLandingDataFetched(LandingPageResponseModel landingPageResponseModel);

        /**
         * Function to load the data from backend
         */
        void loadData();

        void dataFetchFailure(Throwable error);
    }

    abstract class Presenter extends MvpBasePresenterV2<View> {

        /**
         * Function to fetch Landing Page Data
         *
         * @param landingPageRequestModel landingPageResponseModel
         */
        public abstract void getLandingData(LandingPageRequestModel landingPageRequestModel);

        public abstract boolean isNoContent(LandingPageResponseModel landingPageResponseModel);
    }
}
