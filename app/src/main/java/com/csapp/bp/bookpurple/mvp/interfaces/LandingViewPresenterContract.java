package com.csapp.bp.bookpurple.mvp.interfaces;

import com.csapp.bp.bookpurple.mvp.model.LandingPageResponseModel;
import com.csapp.bp.bookpurple.mvp.presenter.BasePresenter;

/*
 * Created by gauravsharma on 2019-05-19.
 */
public interface LandingViewPresenterContract {

    interface View {

        /**
         * Callback for landing page API call success
         * @param landingPageResponseModel landingPageResponseModel
         */
        void onLandingDataFetched(LandingPageResponseModel landingPageResponseModel);
    }

    abstract class Presenter extends BasePresenter<LandingViewPresenterContract.View> {

        /**
         * Function to fetch Landing Page Data
         * @param landingPageResponseModel landingPageResponseModel
         */
        public abstract void getLandingData(LandingPageResponseModel landingPageResponseModel);
    }
}
