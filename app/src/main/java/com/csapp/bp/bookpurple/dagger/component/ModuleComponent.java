package com.csapp.bp.bookpurple.dagger.component;

import com.csapp.bp.bookpurple.activity.LandingActivity;
import com.csapp.bp.bookpurple.activity.ListingActivity;
import com.csapp.bp.bookpurple.activity.VendorDetailsActivity;

/*
 * Written by Gaurav Sharma on 2019-05-19.
 */
public interface ModuleComponent {

    void inject(LandingActivity landingActivity);
    void inject(ListingActivity listingActivity);
    void inject(VendorDetailsActivity vendorDetailsActivity);
}
