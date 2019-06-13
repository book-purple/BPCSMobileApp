package com.csapp.bp.bookpurple.mvp.interfaces;

import com.csapp.bp.bookpurple.mvp.core.MvpBasePresenterV2;
import com.csapp.bp.bookpurple.mvp.model.response.VendorDetailsPageResponseModel;

/*
 * Written by Gaurav Sharma on 2019-06-12.
 */
public interface VendorDetailsViewPresenterContract {

    interface View {

        /**
         * Function to set Vendor Details Page data in view.
         *
         * @param vendorDetailsPageResponseModel vendorDetailsPageResponseModel
         */
        void onVendorDetailsPageDataFetched(VendorDetailsPageResponseModel vendorDetailsPageResponseModel);

        /**
         * Function to load data from backend.
         */
        void onLoad();

        /**
         * Function to handle error view.
         *
         * @param throwable error
         */
        void onDataFetchFailure(Throwable throwable);
    }

    abstract class Presenter extends MvpBasePresenterV2<View> {

        /**
         * Function to fetch data from backend to get Vendor Details Page Details.
         *
         * @param vendorId vendorId
         */
        public abstract void fetchVendorDetailsPageData(String vendorId);

        /**
         * Function to check is data is null or empty.
         *
         * @param vendorDetailsPageResponseModel vendorDetailsPageResponseModel
         * @return true if data is null or empty.
         */
        public abstract boolean inNoContent(VendorDetailsPageResponseModel vendorDetailsPageResponseModel);
    }
}
