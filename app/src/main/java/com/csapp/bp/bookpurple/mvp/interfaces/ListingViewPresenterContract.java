package com.csapp.bp.bookpurple.mvp.interfaces;

import com.csapp.bp.bookpurple.mvp.core.MvpBasePresenterV2;
import com.csapp.bp.bookpurple.mvp.model.request.ListingRequestModel;
import com.csapp.bp.bookpurple.mvp.model.response.LandingPageResponseModel;
import com.csapp.bp.bookpurple.mvp.model.response.ListingResponseModel;

/*
 * Written by Gaurav Sharma on 2019-05-26.
 */
public interface ListingViewPresenterContract {

    interface View {

        void onListingDataFetched(ListingResponseModel listingResponseModel);

        /**
         * Function to load the data from backend
         */
        void loadData();

        void dataFetchFailure(Throwable error);
    }

    abstract class Presenter extends MvpBasePresenterV2<View> {

        /**
         * Function to fetch listing page data
         */
        public abstract void getListingData(ListingRequestModel listingRequestModel);

        public abstract boolean isNoContent(ListingResponseModel listingResponseModel);
    }
}
